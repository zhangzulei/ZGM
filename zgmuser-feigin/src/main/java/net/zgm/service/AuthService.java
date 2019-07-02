package net.zgm.service;

import net.zgm.model.ResultMessage;
import net.zgm.model.ZgmEnteAuth;
import net.zgm.model.ZgmSelfAuth;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wangxiansheng
 * @create 2019-06-05 8:59
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
public interface AuthService {

    /**
     * 申请个人身份认证
     * @param zgmSelfAuth 个人身份认证
     * @return 插入是否成功
     */
    @RequestMapping("/zgmauth/insertSelfAuth")
    ResultMessage insertSelfAuth(@RequestBody ZgmSelfAuth zgmSelfAuth);

    /**
     * 申请企业身份认证
     * @param zgmEnteAuth 企业身份认证对象
     * @return 插入是否成功
     */
    @RequestMapping("/zgmauth/insertEnteAuth")
    ResultMessage insertEnteAuth(@RequestBody ZgmEnteAuth zgmEnteAuth);

    /**
     * 查询用户的认证状态
     * @param userId 用户Id
     * @return 0-未认证 1-个人认证 2-企业认证 3-全部认证
     */
    @RequestMapping("/zgmauth/findAuthState")
    ResultMessage findAuthState(@RequestParam(value = "userId",required = true) Integer userId);

}
