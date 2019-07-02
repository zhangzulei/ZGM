package net.zgm.service;

import net.zgm.model.PageResult;
import net.zgm.model.ZgmArticle;
import net.zgm.model.ZgmArticleComment;
import net.zgm.model.ZgmWriteLeaveMess;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
public interface ArticleService {

    /**
     * 关注和取消关注
     *
     * @param currUserId  当前用户ID
     * @param writeUserId 被关注的写手用户ID
     * @param state       0-关注 1-取消关注
     * @return 是否成功
     */
    int followAndNoFollow(Integer currUserId,
                          Integer writeUserId,
                          Integer state);

    /**
     * 文章收藏和取消收藏
     *
     * @param userId    用户ID
     * @param articleId 文章ID
     * @param state     收藏 / 取消
     * @return 是否成功
     */
    int collOrNoColl(Integer userId, Integer articleId, Integer state);

    /**
     * 文章点赞
     * @param userId 点赞用户ID
     * @param articleId 文章ID
     * @return 点赞是否成功
     */
    int fabulous(
            Integer userId,
            Integer articleId);

    /**
     * 创建一级评论
     * @param zgmArticleComment 评论对象
     * @return 是否新增
     */
    ZgmArticleComment createArticleComment(ZgmArticleComment zgmArticleComment);

    /**
     * 回复评论
     * @param zgmArticommReply 回复评论对象
     * @return 是否成功
     */
    ZgmArticleComment replyComment(ZgmArticleComment zgmArticommReply);

    /**
     * 举报评论
     * @param userId 举报的用户
     * @param commentId 举报的评论ID
     * @param content 举报原因
     * @return 是否执行成功
     */
    int reportComment(Integer userId,
                      Integer commentId,
                      String content);

    /**
     * 创建写手留言
     * @param zgmWriteLeaveMess 写手留言对象
     * @return 是否成功
     */
    int createWriteMessage(ZgmWriteLeaveMess zgmWriteLeaveMess);

    /**
     * 根据写手查出留言列表
     * @param writeId 写手ID
     * @return 列表信息
     */
    List<ZgmWriteLeaveMess> findWriteMessageList(Integer writeId);

    /**
     * 查询新闻列表
     * @param gatewayId 国门点ID
     * @return 新闻列表
     */
    PageResult findArticleList(Integer gatewayId, Integer currPage);

    /**
     * 查询单个新闻
     * @param articleId 新闻ID
     * @return 新闻对象
     */
    ZgmArticle findArticleOne(Integer articleId);

    /**
     * 查询新闻的评论列表
     * @param articleId 新闻ID
     * @return 列表
     */
    PageResult findArticleComment(Integer articleId,Integer currPage);

    /**
     * 查询评论详情
     * @param commentId 评论ID
     * @return 详情列表
     */
    PageResult findCommentInfo(Integer commentId,Integer currPage);

    /**
     * 评论点赞
     * @param commentId 评论ID
     * @param userId 点赞的用户ID
     * @return 是否成功
     */
    int commentFabulous(Integer commentId,Integer userId);

}
