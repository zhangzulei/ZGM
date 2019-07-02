package net.zgm.controller;

import net.zgm.model.PageResult;
import net.zgm.model.ResultMessage;
import net.zgm.model.ZgmArticleComment;
import net.zgm.model.ZgmWriteLeaveMess;
import net.zgm.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;
import java.util.List;

/**
 * @author wangxiansheng
 * @create 2019-06-12 9:48
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
@RestController
public class CommentController {

    @Autowired
    ArticleService articleService;

    // 文章评论
    @RequestMapping("/createArticleComment")
    public ResultMessage createArticleComment(@RequestBody ZgmArticleComment zgmArticleComment) {
        ZgmArticleComment articleComment = articleService.createArticleComment(zgmArticleComment);
        if (articleComment != null) {
            return new ResultMessage("200", "留言成功~", articleComment);
        } else {
            return new ResultMessage("500", "服务器打了个盹~", null);
        }
    }


    // 查看单独一条评论
    @RequestMapping("/findOneComments")
    public ResultMessage findOneComments(@RequestParam(value = "commentId", required = true) Integer commentId,
                                         @RequestParam(value = "currPage", required = true) Integer currPage) {
        try {
            PageResult commentInfo = articleService.findCommentInfo(commentId, currPage);
            return new ResultMessage("200", "SUCCESS", commentInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage("500", "服务器打了个盹~", null);
        }
    }


    // 回复评论
    @RequestMapping("/replyComment")
    public ResultMessage replyComment(@RequestBody ZgmArticleComment zgmArticommReply) {
        ZgmArticleComment zgmArticleComment = articleService.replyComment(zgmArticommReply);
        if (zgmArticleComment != null) {
            return new ResultMessage("200", "SUCCESS", zgmArticleComment);
        } else {
            return new ResultMessage("500", "服务器打了个盹", null);
        }
    }

    // 举报
    @RequestMapping("/reportComment")
    public ResultMessage reportComment(@RequestParam(value = "userId", required = true) Integer userId,
                                       @RequestParam(value = "commentId", required = true) Integer commentId,
                                       @RequestParam(value = "content", required = true) String content) {
        int result = articleService.reportComment(userId, commentId, content);
        if (result > 0) {
            return new ResultMessage("200", "回复成功~", null);
        } else {
            return new ResultMessage("500", "服务器打了个盹", null);
        }
    }

    // 点赞评论
    @RequestMapping("/commentFabulous")
    public ResultMessage commentFabulous(@RequestParam("commentId") Integer commentId,
                                         @RequestParam("userId") Integer userId) {
        int result = articleService.commentFabulous(commentId, userId);
        if (result == 1) {
            return new ResultMessage("200", "点赞成功~", null);
        } else if (result == -1) {
            return new ResultMessage("500", "您已经点过赞了", null);
        } else {
            return new ResultMessage("500", "服务器打了个盹~", "");
        }

    }


    // 留言
    @RequestMapping("/createWriteMessage")
    public ResultMessage createWriteMessage(@RequestBody ZgmWriteLeaveMess zgmWriteLeaveMess) {
        int result = articleService.createWriteMessage(zgmWriteLeaveMess);
        if (result > 0) {
            return new ResultMessage("200", "SUCCESS", null);
        } else {
            return new ResultMessage("500", "服务器打了个盹", null);
        }
    }

    // 留言列表
    @RequestMapping("/findWriteMessageList")
    public ResultMessage findWriteMessageList(@RequestParam("writeId") Integer writeId) {
        try {
            List<ZgmWriteLeaveMess> list = articleService.findWriteMessageList(writeId);
            System.out.println(list);
            return new ResultMessage("200", "SUCCESS", list);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage("500", "服务器打了个盹", null);
        }
    }

}
