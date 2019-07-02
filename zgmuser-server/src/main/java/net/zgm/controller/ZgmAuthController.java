package net.zgm.controller;

import net.zgm.model.ResultMessage;
import net.zgm.model.ZgmEnteAuth;
import net.zgm.model.ZgmSelfAuth;
import net.zgm.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangxiansheng
 * @create 2019-06-05 9:00
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
@RequestMapping("/zgmauth")
public class ZgmAuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping("/insertSelfAuth")
    public ResultMessage insertSelfAuth(@RequestBody ZgmSelfAuth zgmSelfAuth) {
        System.out.println(zgmSelfAuth);
        int result = authService.insertSelfAuth(zgmSelfAuth);
        ResultMessage resultMessage = null;
        resultMessage = result > 0 ? new ResultMessage("200","新增成功",null):new ResultMessage("500","新增失败",null);
        return resultMessage;
    }

    @RequestMapping("/insertEnteAuth")
    public ResultMessage insertEnteAuth(@RequestBody ZgmEnteAuth zgmEnteAuth) {
        int result = authService.insertEnteAuth(zgmEnteAuth);
        ResultMessage resultMessage = null;
        resultMessage = result > 0 ? new ResultMessage("200","新增成功",null):new ResultMessage("500","新增失败",null);
        return resultMessage;
    }

    @RequestMapping("/findAuthState")
    public ResultMessage findAuthState(@RequestParam(value = "userId",required = true) Integer userId) {
        int state = authService.findAuthState(userId);
        String message = null;
        if(state == 0){
            message = "未认证";
        }
        if(state == 1){
            message = "个人认证";
        }
        if(state == 2){
            message = "企业认证";
        }
        if(state == 3){
            message = "全部认证";
        }
        return new ResultMessage("200",state+"",message);
    }

}
