package net.zgm.controller;

import net.zgm.model.ResultMessage;
import net.zgm.model.ZgmGuild;
import net.zgm.model.ZgmGuildLeavmess;
import net.zgm.model.ZgmGuildLog;
import net.zgm.model.ZgmGuildPeo;
import net.zgm.service.GuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author wangxiansheng
 * @create 2019-06-10 13:24
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
@RequestMapping("/guild")
public class GuildController {

    @Autowired
    private GuildService guildService;

    // 查询用户角色
    @RequestMapping("/findUserRole")
    public ResultMessage findUserRole(@RequestParam(value = "userId", required = true) Integer userId,
                                      @RequestParam(value = "guildId", required = true) String guildId) {
        return guildService.findUserRole(userId, guildId);
    }

    // 新建公会
    @RequestMapping("/applyCreateGuild")
    public ResultMessage applyCreateGuild(@RequestBody ZgmGuild zgmGuild) {
        return guildService.applyCreateGuild(zgmGuild);
    }

    // 公会列表(默认展示已通过审核)
    @RequestMapping("/guildListOk")
    public ResultMessage guildListOk() {
        return guildService.guildListOk();
    }

    // 申请加入公会(添加日志)
    @RequestMapping("/applyInsertGuild")
    public ResultMessage applyInsertGuild(@RequestBody ZgmGuildPeo zgmGuildPeo) {
        return guildService.applyInsertGuild(zgmGuildPeo);
    }

    // 公会创建者审核公会申请者(添加日志)
    @RequestMapping("/examineMember")
    public ResultMessage examineMember(
            @RequestParam(value = "userId", required = true) Integer userId,
            @RequestParam(value = "guildId", required = true) String guildId,
            @RequestParam(value = "state", required = true) Integer state) {
        return guildService.examineMember(userId, guildId, state);
    }

    // 加入公会申请列表
    @RequestMapping("/joinGuildList")
    public ResultMessage joinGuildList(@RequestParam(value = "guildId", required = true) String guildId) {
        return guildService.joinGuildList(guildId);
    }

    // 留言
    @RequestMapping("/createMessage")
    public ResultMessage createMessage(@RequestBody ZgmGuildLeavmess message) {
        return guildService.createMessage(message);
    }

    // 留言列表
    @RequestMapping("/messageList")
    public ResultMessage messageList(@RequestParam(value = "guildId", required = true) String guildId) {
        return guildService.messageList(guildId);
    }

    // 管理员踢人(添加日志)
    @RequestMapping("/removePeo")
    public ResultMessage removePeo(
            @RequestParam(value = "removeId", required = true) Integer removeId,
            @RequestParam(value = "userId", required = true) Integer userId,
            @RequestParam(value = "guildId", required = true) String guildId) {
        return guildService.removePeo(removeId, userId, guildId);
    }

    // 查看成员信息
    /*@RequestMapping("/findOnePeo")
    public ResultMessage findOnePeo(@RequestParam(value = "userId", required = true) Integer userId,
                                    @RequestParam(value = "guildId", required = true) String guildId) {
        return guildService.findOnePeo(userId, guildId);
    }*/

    //公会人员列表(通过审核)
    @RequestMapping("/findListByGuild")
    public ResultMessage findListByGuild(@RequestParam(value = "guildId", required = true) String guildId) {
        return guildService.findListByGuild(guildId);
    }

    // 退出公会 (添加日志)
    @RequestMapping("/outGuild")
    public ResultMessage outGuild(
            @RequestParam(value = "userId", required = true) Integer userId,
            @RequestParam(value = "guildId", required = true) String guildId) {
        return guildService.outGuild(userId, guildId);
    }

    // 管理员申请解散(日志)
    @RequestMapping("/dissolutionGuild")
    public ResultMessage dissolutionGuild(@RequestParam(value = "userId", required = true) Integer userId,
                                          @RequestParam(value = "guildId", required = true) String guildId) {
        return guildService.dissolutionGuild(userId, guildId);
    }

    // 模糊查询公会 公会状态!
    @RequestMapping("/likeFindGuild")
    public ResultMessage likeFindGuild(
            @RequestParam(value = "areaId", required = true) Integer areaId,
            @RequestParam(value = "guildName", required = true) String guildName) {
        return guildService.likeFindGuild(areaId, guildName);
    }

    // 模糊查询公会的人 人员状态!
    /*@RequestMapping("/likeFindPeo")
    public ResultMessage likeFindPeo(
            @RequestParam("guildId") String guildId,
            @RequestParam("userName") String userName) {
        return guildService.likeFindPeo(guildId, userName);
    }*/

}
