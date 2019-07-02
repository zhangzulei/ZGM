package net.zgm.controller;

import net.zgm.model.PageResult;
import net.zgm.model.ResultMessage;
import net.zgm.model.ZgmArticle;
import net.zgm.service.ArticleService;
import net.zgm.service.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangxiansheng
 * @create 2019-06-12 9:47
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
public class ArticleController {

    @Autowired
    ArticleService articleService;

    // 关注-取消关注
    @RequestMapping("/followAndNoFollow")
    public ResultMessage followAndNoFollow(@RequestParam(value = "currUserId", required = true) Integer currUserId,
                                           @RequestParam(value = "writeUserId", required = true) Integer writeUserId,
                                           @RequestParam(value = "state", required = false, defaultValue = "0") Integer state) {
        try {
            int result = articleService.followAndNoFollow(currUserId, writeUserId, state);
            if (result > 0) {
                return new ResultMessage("200", "操作成功", "");
            }
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage("500", "服务器打了个盹~", "");
        }
    }

    // 文章收藏 / 取消收藏
    @RequestMapping("/collOrNoColl")
    public ResultMessage collOrNoColl(
            @RequestParam(value = "userId", required = true) Integer userId,
            @RequestParam(value = "articleId", required = true) Integer articleId,
            @RequestParam(value = "state", required = false, defaultValue = "0") Integer state) {
        try {
            int result = articleService.collOrNoColl(userId, articleId, state);
            if (result > 0) {
                return new ResultMessage("200", "操作成功", "");
            }
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage("500", "服务器打了个盹~", "");
        }
    }

    // 文章点赞
    @RequestMapping("/fabulous")
    public ResultMessage fabulous(
            @RequestParam(value = "userId", required = true) Integer userId,
            @RequestParam(value = "articleId", required = true) Integer articleId) {
        int result = articleService.fabulous(userId, articleId);
        if (result == 1) {
            return new ResultMessage("200", "点赞成功~", null);
        } else if (result == -1) {
            return new ResultMessage("500","您已经点过赞了",null);
        } else {
            return new ResultMessage("500", "服务器打了个盹~", "");
        }
    }

    // 文章分享

    // 文章列表(根据国门点和全部,时间排序)
    @RequestMapping("/findArticleList")
    public ResultMessage findArticleList(@RequestParam(value = "gatewayId", required = false) Integer gatewayId,
                                         @RequestParam(value = "currPage", required = false, defaultValue = "1") Integer currPage) {
        try {
            PageResult result = articleService.findArticleList(gatewayId, currPage);
            return new ResultMessage("200", "SUCCESS", result);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage("500", "服务器打了个盹~", null);
        }
    }

    // 文章详情 + 评论列表
    @RequestMapping("/findArticleOne")
    public ResultMessage findArticleOne(@RequestParam(value = "articleId", required = true) Integer articleId
            , @RequestParam(value = "currPage", required = false, defaultValue = "1") Integer currPage) {
        try {
            ZgmArticle articleOne = articleService.findArticleOne(articleId);
            Map<String, Object> info = new HashMap<>();
            if (articleOne != null) {
                info.put("article", articleOne);
                info.put("comments", articleService.findArticleComment(articleOne.getId(), currPage));
            }
            return new ResultMessage("200", "SUCCESS", info);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage("500", "服务器打了个盹~", null);
        }

    }

}
