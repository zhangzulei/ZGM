package net.zgm.service;

import net.zgm.model.ResultMessage;
import net.zgm.model.ZgmUser;
import net.zgm.model.ZgmUserMailList;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author wangxiansheng
 * @create 2019-06-03 15:58
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
@FeignClient("zgmuser-server")
public interface UserService {

    @RequestMapping("/findRelaByUserId")
    ResultMessage findRelaByUserId(@RequestParam(value = "userId",required = true) Integer userId);

    @RequestMapping(value = "/insertUser",method = RequestMethod.POST)
    ResultMessage insertUser(@RequestBody ZgmUser user);

    @RequestMapping("/isabout")
    ResultMessage isabout(@RequestParam(value = "userId", required = true) Integer userId);

    @RequestMapping("/createWrite")
    ResultMessage createWrite(@RequestParam(value = "userId", required = true) Integer userId);

    @RequestMapping("/addMailList")
    ResultMessage addMailList(@RequestBody List<ZgmUserMailList> list);

}
