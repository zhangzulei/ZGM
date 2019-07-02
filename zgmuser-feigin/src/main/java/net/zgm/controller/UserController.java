package net.zgm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.zgm.model.ResultMessage;
import net.zgm.model.ZgmUser;
import net.zgm.model.ZgmUserMailList;
import net.zgm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wangxiansheng
 * @create 2019-06-03 15:57
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
@RequestMapping("/user")
@Api("用户模块")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/findRelaByUserId",method = RequestMethod.GET)
    @ApiOperation(value = "查找亲属成员",notes = "根据用户ID查找出所有建立亲属管理的成员")
    @ApiImplicitParam(paramType = "query",name = "userId",dataType = "int",required = true,value = "当前登录的用户ID")
    public ResultMessage findRelaByUserId(@RequestParam Integer userId) {
        return userService.findRelaByUserId(userId);
    }

    @PostMapping("/insertUser")
    @ApiOperation(value = "新增亲属关系",notes = "新增当前用户的亲属关系")
    @ApiImplicitParam(paramType = "body", name = "user", dataType = "ZgmUser", required = true, value = "亲属关系对象")
    public ResultMessage insertUser(@RequestBody ZgmUser user) {
        System.out.println(user);
        return userService.insertUser(user);
    }

    @GetMapping("/isabout")
    public ResultMessage isabout(@RequestParam(value = "userId", required = true) Integer userId) {
        return userService.isabout(userId);
    }

    @GetMapping("/createWrite")
    public ResultMessage createWrite(@RequestParam(value = "userId", required = true) Integer userId) {
        return userService.createWrite(userId);
    }

    @PostMapping("/addMailList")
    public ResultMessage addMailList(@RequestBody List<ZgmUserMailList> list) {
        return userService.addMailList(list);
    }

}
