package net.zgm.service;

import net.zgm.model.ResultMessage;
import net.zgm.model.ZgmArticleComment;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wangxiansheng
 * @create 2019-06-18 18:05
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
@FeignClient("zgmnews-server")
public interface ArticleService {

    @RequestMapping("/findArticleList")
    ResultMessage findArticleList(@RequestParam(value = "gatewayId", required = false) Integer gatewayId,
                                  @RequestParam(value = "currPage", required = false, defaultValue = "1") Integer currPage);

    @RequestMapping("/findArticleOne")
    ResultMessage findArticleOne(@RequestParam(value = "articleId", required = true) Integer articleId,
                                 @RequestParam(value = "currPage", required = false, defaultValue = "1") Integer currPage);

    @RequestMapping("/createArticleComment")
    ResultMessage createArticleComment(@RequestBody ZgmArticleComment zgmArticleComment);

    @RequestMapping("/replyComment")
    ResultMessage replyComment(@RequestBody ZgmArticleComment zgmArticommReply);

    @RequestMapping("/findOneComments")
    ResultMessage findOneComments(@RequestParam(value = "commentId", required = true) Integer commentId,
                                  @RequestParam(value = "currPage", required = true) Integer currPage);

    @RequestMapping("/fabulous")
    ResultMessage fabulous(
            @RequestParam(value = "userId", required = true) Integer userId,
            @RequestParam(value = "articleId", required = true) Integer articleId);

    @RequestMapping("/commentFabulous")
    ResultMessage commentFabulous(@RequestParam("commentId") Integer commentId,
                                  @RequestParam("userId") Integer userId);

}
