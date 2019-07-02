package net.zgm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.zgm.model.ResultMessage;
import net.zgm.model.ZgmArticleComment;
import net.zgm.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangxiansheng
 * @create 2019-06-18 17:34
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
@RequestMapping("/article")
@Api("新闻模块")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    // 文章列表(根据国门点和全部,时间排序)
    @RequestMapping(value = "/findArticleList", method = RequestMethod.GET)
    @ApiOperation(value = "文章列表", notes = "根据国门点或者全部,时间倒序")
    @ApiImplicitParam(paramType = "query", name = "gatewayId", dataType = "int", required = false, value = "国门点ID")
    public ResultMessage findArticleList(@RequestParam(value = "gatewayId", required = false) Integer gatewayId,
                                         @RequestParam(value = "currPage", required = false, defaultValue = "1") Integer currPage) {
        return articleService.findArticleList(gatewayId, currPage);
    }

    // 文章详情 + 评论列表
    @RequestMapping(value = "/findArticleOne", method = RequestMethod.GET)
    @ApiOperation(value = "文章详情", notes = "根据文章ID查询文章详情和所有评论")
    @ApiImplicitParam(paramType = "query", name = "articleId", dataType = "int", required = true, value = "需要查看详情的文章ID")
    public ResultMessage findArticleOne(@RequestParam(value = "articleId", required = true) Integer articleId,
                                        @RequestParam(value = "currPage", required = false, defaultValue = "1") Integer currPage) {
        return articleService.findArticleOne(articleId, currPage);
    }

    // 文章评论
    @RequestMapping(value = "/createArticleComment", method = RequestMethod.POST)
    @ApiOperation(value = "创建一级评论", notes = "文章列表+两条评论")
    @ApiImplicitParam(paramType = "body", name = "zgmArticleComment", dataType = "ZgmArticleComment", required = true, value = "创建一级评论的评论对象")
    public ResultMessage createArticleComment(@RequestBody ZgmArticleComment zgmArticleComment) {
        return articleService.createArticleComment(zgmArticleComment);
    }

    // 回复评论
    @RequestMapping(value = "/replyComment", method = RequestMethod.POST)
    @ApiOperation(value = "回复评论", notes = "回复评论")
    @ApiImplicitParam(paramType = "body", name = "zgmArticleComment", dataType = "ZgmArticleComment", required = true, value = "回复评论的评论对象")
    public ResultMessage replyComment(@RequestBody ZgmArticleComment zgmArticommReply) {
        return articleService.replyComment(zgmArticommReply);
    }

    // 查看单独一条评论
    @RequestMapping(value = "/findOneComments", method = RequestMethod.GET)
    @ApiOperation(value = "查看单独一条评论", notes = "单独查看一条评论")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "articleId", dataType = "int", required = true, value = "需要查看的评论ID"),
            @ApiImplicitParam(paramType = "query", name = "currPage", dataType = "int", required = true, value = "当前页数")
    })
    public ResultMessage findOneComments(@RequestParam(value = "commentId", required = true) Integer commentId,
                                         @RequestParam(value = "currPage", required = false, defaultValue = "1") Integer currPage) {
        return articleService.findOneComments(commentId, currPage);
    }

    @RequestMapping(value = "/fabulous",method = RequestMethod.GET)
    @ApiOperation(value = "新闻点赞",notes = "新闻点赞")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "userId",dataType = "int",required = true,value = "当前用户ID"),
            @ApiImplicitParam(paramType = "query",name = "articleId",dataType = "int",required = true,value = "文章ID")
    })
    public ResultMessage fabulous(
            @RequestParam(value = "userId", required = true) Integer userId,
            @RequestParam(value = "articleId", required = true) Integer articleId) {
        return articleService.fabulous(userId, articleId);
    }

    @RequestMapping(value = "/commentFabulous",method = RequestMethod.GET)
    @ApiOperation(value = "评论点赞",notes = "点赞评论")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "commentId",dataType = "int",required = true,value = "评论ID"),
            @ApiImplicitParam(paramType = "query",name = "userId",dataType = "int",required = true,value = "点赞用户ID")
    })
    public ResultMessage commentFabulous(@RequestParam("commentId") Integer commentId,
                                         @RequestParam("userId") Integer userId) {
        return articleService.commentFabulous(commentId, userId);
    }

}
