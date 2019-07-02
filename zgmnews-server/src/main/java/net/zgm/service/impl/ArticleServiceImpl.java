package net.zgm.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.zgm.mapper.*;
import net.zgm.model.*;
import net.zgm.service.ArticleService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

/**
 * @author wangxiansheng
 * @create 2019-06-12 10:00
 * <p>
 * |                   _oo8oo_
 * |                  o8888888o
 * |                  88" . "88
 * |                  (| -_- |)
 * |                  0\  =  /0
 * |                ___/'==='\___
 * |              .' \\|     |// '.
 * |             / \\|||  :  |||// \
 * |            / _||||| -:- |||||_ \
 * |           |   | \\\  -  /// |   |
 * |           | \_|  ''\---/''  |_/ |
 * |           \  .-\__  '-'  __/-.  /
 * |         ___'. .'  /--.--\  '. .'___
 * |      ."" '<  '.___\_<|>_/___.'  >' "".
 * |     | | :  `- \`.:`\ _ /`:.`/ -`  : | |
 * |     \  \ `-.   \_ __\ /__ _/   .-` /  /
 * | =====`-.____`.___ \_____/ ___.`____.-`=====
 * |                   `=---=`
 * | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * |         佛祖保佑         永不宕机/永无bug
 */
@Transactional
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    FollowWriteMapper followWriteMapper;

    @Autowired
    CollMapper collMapper;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    ArticleFabulousMapper articleFabulousMapper;

    @Override
    public int followAndNoFollow(Integer currUserId, Integer writeUserId, Integer state) {
        ZgmFollowWrite followWrite = new ZgmFollowWrite();
        followWrite.setUserId(currUserId);
        followWrite.setFollowId(writeUserId);
        int result = 0;
        ZgmFollowWrite followWrite1 = followWriteMapper.selectOne(followWrite);
        if (state == 1 || followWrite1 != null) {
            System.out.println(followWrite1);
            followWrite1.setIsDelete(state);
            result = followWriteMapper.updateById(followWrite1);
        } else {
            followWrite.setCreateDate(new Date());
            followWrite.setIsDelete(state);
            result = followWriteMapper.insert(followWrite);
        }
        return result;
    }

    @Override
    public int collOrNoColl(Integer userId, Integer articleId, Integer state) {
        ZgmArticlesColl zgmArticlesColl = new ZgmArticlesColl();
        zgmArticlesColl.setArticleId(articleId);
        zgmArticlesColl.setUserId(userId);

        int result = 0;
        ZgmArticlesColl coll = collMapper.selectOne(zgmArticlesColl);
        if (state == 1 || coll != null) {
            coll.setIsdelete(state);
            result = collMapper.updateById(coll);
        } else {
            zgmArticlesColl.setFlooDate(new Date());
            zgmArticlesColl.setIsdelete(state);
            result = collMapper.insert(zgmArticlesColl);
        }

        return result;
    }

    /**
     * 文章点赞
     *
     * @param userId    点赞用户ID
     * @param articleId 文章ID
     * @return 是否成功
     */
    @Override
    public int fabulous(Integer userId, Integer articleId) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            String jsonList = (String) redisTemplate.boundHashOps("articleFabulous").get(articleId.toString());

            if (jsonList == null) {
                jsonList = "[]";
            }

            List<Integer> list = objectMapper.readValue(jsonList, List.class);

            if (list.contains(userId)) {
                return -1;
            }

            list.add(userId);

            String json = objectMapper.writeValueAsString(list);
            redisTemplate.boundHashOps("articleFabulous").put(articleId.toString(), json);

            ZgmArticle zgmArticle = articleMapper.selectById(articleId);
            zgmArticle.setFabuCount(zgmArticle.getFabuCount() + 1);
            articleMapper.updateById(zgmArticle);

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -2;
        }
    }

    @Scheduled(cron = "0 30 1 * * ?")
    protected void synchFabulous() {
        // 拿出数据库数据
        List<zgmArticleFabulous> dbList = articleFabulousMapper.selectList(null);

        // 拿出缓存数据
        List<zgmArticleFabulous> redisList = new ArrayList<zgmArticleFabulous>();
        BoundHashOperations<String, Object, Object> redis = redisTemplate.boundHashOps("articleFabulous");
        if (redis == null) {
            return;
        }
        Set<Object> keys = redis.keys();

        if (keys.size() == 0) {
            return;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        keys.forEach(key -> {
            String json = (String) redis.get(key + "");
            if (json == null) {
                json = "[]";
            }
            try {
                List<Integer> userList = objectMapper.readValue(json, List.class);
                zgmArticleFabulous fabulous = new zgmArticleFabulous();
                fabulous.setArticleId(Integer.valueOf(key.toString()));
                fabulous.setUserNoList(userList);
                fabulous.setUserIdList(json);
                redisList.add(fabulous);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        List<zgmArticleFabulous> updateList = new ArrayList<zgmArticleFabulous>();

        List<zgmArticleFabulous> addList = new ArrayList<zgmArticleFabulous>();

        // 对比
        boolean flag = true;
        for (zgmArticleFabulous redisitem : redisList) {
            flag = true;
            for (zgmArticleFabulous dbitem : dbList) {
                // 已存在-修改
                if (redisitem.getArticleId().equals(dbitem.getArticleId())) {

                    // 对比点赞人是否相同
                    if (!redisitem.getUserIdList().equals(dbitem.getUserIdList())) {
                        // 修改
                        dbitem.setUserIdList(redisitem.getUserIdList());
                        articleFabulousMapper.updateById(dbitem);
                    }

                    flag = false;
                }
            }

            // 不存在-新增
            if (flag) {
                articleFabulousMapper.insert(redisitem);
            }
        }
    }

    @Autowired
    ArticleCommentMapper articleCommentMapper;

    @Override
    @Transactional
    public ZgmArticleComment createArticleComment(ZgmArticleComment zgmArticleComment) {
        try {


            zgmArticleComment.setCommentDate(new Date());
            zgmArticleComment.setCommentFabulous(0);
            zgmArticleComment.setCommentState(0);
            zgmArticleComment.setParentId(0);
            Integer result = articleCommentMapper.insert(zgmArticleComment);

            zgmArticleComment.setObjcommId(zgmArticleComment.getId());
            articleCommentMapper.updateById(zgmArticleComment);

            ZgmArticleComment zgmArticleComment2 = articleCommentMapper.selectById(zgmArticleComment.getParentId());
            if (zgmArticleComment2 != null) {
                zgmArticleComment.setReplyUserName(adminMapper.selectById(zgmArticleComment2.getCommentUserId()).getUserName());
            }

            ZgmAdmin zgmAdmin = adminMapper.selectById(zgmArticleComment.getCommentUserId());
            zgmArticleComment.setUserHeadPath(zgmAdmin.getWxPic());
            zgmArticleComment.setCommentUserName(zgmAdmin.getUserName());

            return zgmArticleComment;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ZgmArticleComment replyComment(ZgmArticleComment zgmArticleComment) {
        try {
            zgmArticleComment.setCommentDate(new Date());
            zgmArticleComment.setCommentFabulous(0);
            zgmArticleComment.setCommentState(0);

            ZgmArticleComment zgmArticleComment1 = new ZgmArticleComment();
            zgmArticleComment1.setId(zgmArticleComment.getParentId());
            ZgmArticleComment selectOne = articleCommentMapper.selectOne(zgmArticleComment1);
            zgmArticleComment.setObjcommId(selectOne.getObjcommId());
            zgmArticleComment.setArticleId(selectOne.getArticleId());
            articleCommentMapper.insert(zgmArticleComment);

            ZgmArticleComment zgmArticleComment2 = articleCommentMapper.selectById(zgmArticleComment.getParentId());
            zgmArticleComment.setCommentUserName(adminMapper.selectById(zgmArticleComment2.getCommentUserId()).getUserName());

            ZgmAdmin zgmAdmin = adminMapper.selectById(zgmArticleComment.getCommentUserId());
            zgmArticleComment.setUserHeadPath(zgmAdmin.getWxPic());

            zgmArticleComment.setReplyUserName(zgmAdmin.getUserName());

            return zgmArticleComment;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Autowired
    CommentReportMapper commentReportMapper;

    @Override
    public int reportComment(Integer userId, Integer commentId, String content) {
        try {
            ZgmCommentReport zgmCommentReport = new ZgmCommentReport();
            zgmCommentReport.setCommentId(commentId);
            zgmCommentReport.setReportDate(new Date());
            zgmCommentReport.setState(0);
            zgmCommentReport.setUserId(userId);
            zgmCommentReport.setReportReason(content);
            return commentReportMapper.insert(zgmCommentReport);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Autowired
    WriteLeaveMessMapper writeLeaveMessMapper;

    @Override
    public int createWriteMessage(ZgmWriteLeaveMess zgmWriteLeaveMess) {
        try {
            zgmWriteLeaveMess.setIsRead(1);
            zgmWriteLeaveMess.setMessDate(new Date());
            return writeLeaveMessMapper.insert(zgmWriteLeaveMess);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<ZgmWriteLeaveMess> findWriteMessageList(Integer writeId) {
        EntityWrapper<ZgmWriteLeaveMess> wrapper = new EntityWrapper<ZgmWriteLeaveMess>();
        wrapper.setEntity(new ZgmWriteLeaveMess());
        wrapper.where("user_id = {0}", writeId);
        return writeLeaveMessMapper.selectList(wrapper);
    }

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public PageResult findArticleList(Integer gatewayId, Integer currPage) {
        EntityWrapper<ZgmArticle> wrapper = new EntityWrapper<>();
        wrapper.orderBy("publishDate", false);
        System.out.println(gatewayId);
        if (gatewayId != null) {
            wrapper.where("gateway_id = {0}", gatewayId);
        }
        PageHelper.startPage(currPage, 100);
        List<ZgmArticle> list = articleMapper.selectList(wrapper);
        list.forEach(item -> {
            item.setImgUrlArr(item.getImgUrl().split(","));
        });
        Pagination pagination = PageHelper.getPagination();
        return new PageResult(pagination, list);
    }

    @Override
    public ZgmArticle findArticleOne(Integer articleId) {
        ZgmArticle zgmArticle = new ZgmArticle();
        zgmArticle.setId(articleId);
        ZgmArticle selectOne = articleMapper.selectOne(zgmArticle);

        EntityWrapper<ZgmArticleComment> wrapper = new EntityWrapper<>();
        wrapper.where("article_id = {0}", articleId);
        selectOne.setCommentsCount(articleCommentMapper.selectCount(wrapper));
        if (selectOne != null) {
            selectOne.setImgUrlArr(selectOne.getImgUrl().split(","));
        }
        return selectOne;
    }

    @Override
    public PageResult findArticleComment(Integer articleId, Integer currPage) {
        AtomicReference<EntityWrapper<ZgmArticleComment>> commentEntityWrapper = new AtomicReference<>(new EntityWrapper<>());
        commentEntityWrapper.get()
                .where("parent_id = 0")
                .where("comment_state != 2")
                .where("article_id = {0}", articleId)
                .orderBy("comment_date", true);

        PageHelper.startPage(currPage, 100);
        List<ZgmArticleComment> list = articleCommentMapper.selectList(commentEntityWrapper.get());
        final Pagination pagination = PageHelper.getPagination();
        PageResult result = new PageResult(pagination, list);
        list.forEach(item -> {
            item.setUserHeadPath(adminMapper.selectById(item.getCommentUserId()).getWxPic());
            ZgmAdmin zgmAdmin = new ZgmAdmin();
            zgmAdmin.setUserId(item.getCommentUserId());
            item.setCommentUserName(adminMapper.selectOne(zgmAdmin).getUserName());
            EntityWrapper<ZgmArticleComment> wrapper = new EntityWrapper<ZgmArticleComment>();

            wrapper.where("objcomm_id = {0}", item.getId())
                    .where("comment_state != 2")
                    .where("parent_id != 0")
                    .orderBy("comment_date", true);
            PageHelper.startPage(1, 2);
            item.setCommentList(articleCommentMapper.selectList(wrapper));

            item.setCommentsCount(articleCommentMapper.selectCount(wrapper));

            for (ZgmArticleComment comment : item.getCommentList()) {
                // 拿到回复人的姓名
                ZgmAdmin admin = new ZgmAdmin();
                admin.setUserId(comment.getCommentUserId());
                comment.setReplyUserName(adminMapper.selectOne(admin).getUserName());

                // 拿到父级留言
                ZgmArticleComment zgmArticleComment = new ZgmArticleComment();
                zgmArticleComment.setId(comment.getParentId());
                ZgmArticleComment one = articleCommentMapper.selectOne(zgmArticleComment);

                // 拿到留言人姓名
                admin.setUserId(one.getCommentUserId());
                comment.setCommentUserName(adminMapper.selectOne(admin).getUserName());

                // 拿到用户头像
                comment.setUserHeadPath(adminMapper.selectById(comment.getCommentUserId()).getWxPic());
            }
        });
        return result;
    }


    @Autowired
    AdminMapper adminMapper;

    @Override
    public PageResult findCommentInfo(Integer commentId, Integer currPage) {

        // 主评论
        ZgmArticleComment zgmArticleComment1 = new ZgmArticleComment();
        zgmArticleComment1.setId(commentId);
        ZgmArticleComment selectOne = articleCommentMapper.selectOne(zgmArticleComment1);

        EntityWrapper<ZgmArticleComment> wrapper1 = new EntityWrapper<>();
        wrapper1.where("objcomm_id = {0}", selectOne.getId());
        selectOne.setCommentsCount(articleCommentMapper.selectCount(wrapper1) - 1);

        // 拿到用户头像
        selectOne.setUserHeadPath(adminMapper.selectById(selectOne.getCommentUserId()).getWxPic());
        ZgmAdmin zgmAdmin = new ZgmAdmin();
        zgmAdmin.setUserId(selectOne.getCommentUserId());
        selectOne.setCommentUserName(adminMapper.selectOne(zgmAdmin).getUserName());

        EntityWrapper<ZgmArticleComment> wrapper = new EntityWrapper<ZgmArticleComment>();
        wrapper.where("objcomm_id = {0}", commentId)
                .where("comment_state != 2")
                .where("parent_id != 0")
                .orderBy("comment_date", true);

        PageHelper.startPage(currPage, 100);
        selectOne.setCommentList(articleCommentMapper.selectList(wrapper));

        for (ZgmArticleComment comment : selectOne.getCommentList()) {
            ZgmAdmin admin = new ZgmAdmin();
            admin.setUserId(comment.getCommentUserId());
            comment.setReplyUserName(adminMapper.selectOne(admin).getUserName());

            ZgmArticleComment zgmArticleComment = new ZgmArticleComment();
            zgmArticleComment.setId(comment.getParentId());
            ZgmArticleComment one = articleCommentMapper.selectOne(zgmArticleComment);

            admin.setUserId(one.getCommentUserId());
            comment.setCommentUserName(adminMapper.selectOne(admin).getUserName());

            comment.setUserHeadPath(adminMapper.selectById(comment.getCommentUserId()).getWxPic());
        }
        return new PageResult(PageHelper.getPagination(), selectOne);
    }

    @Autowired
    SysUserMapper userMapper;

    @Autowired
    CommentFabulousMapper commentFabulousMapper;

    @Override
    public int commentFabulous(Integer commentId, Integer userId) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            String jsonList = (String) redisTemplate.boundHashOps("commentsFabulous").get(commentId.toString());

            if (jsonList == null) {
                jsonList = "[]";
            }

            List<Integer> list = objectMapper.readValue(jsonList, List.class);

            if (list.contains(userId)) {
                return -1;
            }

            list.add(userId);

            String json = objectMapper.writeValueAsString(list);
            redisTemplate.boundHashOps("commentsFabulous").put(commentId.toString(), json);

            ZgmArticleComment zgmArticleComment = articleCommentMapper.selectById(commentId);
            zgmArticleComment.setCommentFabulous(zgmArticleComment.getCommentFabulous() == null ? 0 : zgmArticleComment.getCommentFabulous());
            zgmArticleComment.setCommentFabulous(zgmArticleComment.getCommentFabulous() + 1);
            articleCommentMapper.updateById(zgmArticleComment);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -2;
        }
    }

    @Scheduled(cron = "0 0 2 * * ?")
    protected void synchCommentFabulous() {
        // 拿出数据库数据
        List<ZgmCommentFabulous> dbList = commentFabulousMapper.selectList(null);

        // 拿出缓存数据
        List<ZgmCommentFabulous> redisList = new ArrayList<ZgmCommentFabulous>();
        BoundHashOperations<String, Object, Object> redis = redisTemplate.boundHashOps("commentsFabulous");
        if (redis == null) {
            return;
        }
        Set<Object> keys = redis.keys();

        if (keys.size() == 0) {
            return;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        keys.forEach(key -> {
            String json = (String) redis.get(key + "");
            if (json == null) {
                json = "[]";
            }
            try {
                List<Integer> userList = objectMapper.readValue(json, List.class);
                ZgmCommentFabulous fabulous = new ZgmCommentFabulous();
                fabulous.setCommentId(Integer.valueOf(key.toString()));
                fabulous.setUserNoList(userList);
                fabulous.setUseridList(json);
                redisList.add(fabulous);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // 对比
        boolean flag;
        for (ZgmCommentFabulous redisitem : redisList) {
            flag = true;
            for (ZgmCommentFabulous dbitem : dbList) {
                // 已存在-修改
                if (redisitem.getCommentId().equals(dbitem.getCommentId())) {
                    // 对比点赞人是否相同
                    if (!redisitem.getUseridList().equals(dbitem.getUseridList())) {
                        // 修改
                        dbitem.setUseridList(redisitem.getUseridList());
                        commentFabulousMapper.updateById(dbitem);
                    }
                    flag = false;
                }
            }
            // 不存在-新增
            if (flag) {
                commentFabulousMapper.insert(redisitem);
            }
        }
    }
}
