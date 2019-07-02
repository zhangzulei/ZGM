package net.zgm.controller;

import net.zgm.model.ResultMessage;
import net.zgm.model.ZgmAdmin;
import net.zgm.model.ZgmUser;
import net.zgm.model.ZgmUserMailList;
import net.zgm.service.AuthService;
import net.zgm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangxiansheng
 * @create 2019-06-03 15:35
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
public class ZgmUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @RequestMapping("/findRelaByUserId")
    public ResultMessage findRelaByUserId(@RequestParam Integer userId) {
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setData(userService.findRelaByUserId(userId));
        resultMessage.setStatus("200");
        resultMessage.setMsg("查询成功");
        return resultMessage;
    }

    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    public ResultMessage insertUser(@RequestBody ZgmUser user) {
        ResultMessage resultMessage = new ResultMessage();
        int result = userService.insertUser(user);
        System.out.println(result);
        if (result > 0) {
            resultMessage.setStatus("200");
            resultMessage.setMsg("新增成功");
        } else {
            resultMessage.setStatus("500");
            resultMessage.setMsg("新增失败");

        }
        return resultMessage;
    }

    @RequestMapping("/isabout")
    public ResultMessage isabout(@RequestParam(value = "userId", required = true) Integer userId) {
        int state = authService.findAuthState(userId);
        if (state == 0) {
            return new ResultMessage("500", "您还未进行认证", null);
        }
        int result = userService.findIsAbout(userId);
        if (result <= 0) {
            return new ResultMessage("501", "请同意协议", null);
        }
        return new ResultMessage("200", "请根据您的手机号和密码进行登录", null);
    }

    @RequestMapping("/createWrite")
    public ResultMessage createWrite(@RequestParam(value = "userId", required = true) Integer userId) {
        try {
            int about = userService.createAbout(userId);
            ZgmAdmin adminById = userService.findAdminById(userId);
            int math = (int) (Math.random() * (999999 - 100000) + 100000);
            userService.createWrite(adminById.getTelephone(), DigestUtils.md5DigestAsHex((math + "").getBytes()));
            Map map = new HashMap();
            map.put("phone", adminById.getTelephone());
            map.put("pwd", math + "");
            return new ResultMessage("200", "申请成功~", map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage("500", "网络异常", null);
        }
    }

    @RequestMapping("/addMailList")
    public ResultMessage addMailList(@RequestBody List<ZgmUserMailList> list){
        userService.addMailList(list);
        return new ResultMessage("200","SUCCESS",null);
    }

}
