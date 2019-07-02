package net.zgm.service;

import net.zgm.model.ResultMessage;
import net.zgm.model.ZgmGuild;
import net.zgm.model.ZgmGuildLeavmess;
import net.zgm.model.ZgmGuildPeo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wangxiansheng
 * @create 2019-06-10 13:38
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
@FeignClient("zgmguild-server")
public interface GuildService {

    // 查询用户角色
    @RequestMapping("/findUserRole")
    ResultMessage findUserRole(@RequestParam(value = "userId", required = true) Integer userId,
                                      @RequestParam(value = "guildId", required = true) String guildId);

    // 新建公会
    @RequestMapping("/applyCreateGuild")
    ResultMessage applyCreateGuild(@RequestBody ZgmGuild zgmGuild);

    // 公会列表(默认展示已通过审核)
    @RequestMapping("/guildListOk")
    ResultMessage guildListOk();

    // 申请加入公会(添加日志)
    @RequestMapping("/applyInsertGuild")
    ResultMessage applyInsertGuild(@RequestBody ZgmGuildPeo zgmGuildPeo);

    // 公会创建者审核公会申请者(添加日志)
    @RequestMapping("/examineMember")
    ResultMessage examineMember(
            @RequestParam(value = "userId", required = true) Integer userId,
            @RequestParam(value = "guildId", required = true) String guildId,
            @RequestParam(value = "state", required = true) Integer state);

    // 加入公会申请列表
    @RequestMapping("/joinGuildList")
    ResultMessage joinGuildList(@RequestParam(value = "guildId", required = true) String guildId);

    // 留言
    @RequestMapping("/createMessage")
    ResultMessage createMessage(@RequestBody ZgmGuildLeavmess message);

    // 留言列表
    @RequestMapping("/messageList")
    ResultMessage messageList(@RequestParam(value = "guildId", required = true) String guildId);

    // 管理员踢人(添加日志)
    @RequestMapping("/removePeo")
    ResultMessage removePeo(
            @RequestParam(value = "removeId", required = true) Integer removeId,
            @RequestParam(value = "userId", required = true) Integer userId,
            @RequestParam(value = "guildId", required = true) String guildId);

    // 查看成员信息
    @RequestMapping("/findOnePeo")
    ResultMessage findOnePeo(@RequestParam(value = "userId", required = true) Integer userId,
                                    @RequestParam(value = "guildId", required = true) String guildId);

    //公会人员列表(通过审核)
    @RequestMapping("/findListByGuild")
    ResultMessage findListByGuild(@RequestParam(value = "guildId", required = true) String guildId);

    // 退出公会 (添加日志)
    @RequestMapping("/outGuild")
    ResultMessage outGuild(
            @RequestParam(value = "userId", required = true) Integer userId,
            @RequestParam(value = "guildId", required = true) String guildId);

    // 管理员申请解散(日志)
    @RequestMapping("/dissolutionGuild")
    ResultMessage dissolutionGuild(@RequestParam(value = "userId", required = true) Integer userId,
                                          @RequestParam(value = "guildId", required = true) String guildId);

    // 模糊查询公会 公会状态!
    @RequestMapping("/likeFindGuild")
    ResultMessage likeFindGuild(
            @RequestParam(value = "areaId", required = true) Integer areaId,
            @RequestParam(value = "guildName", required = true) String guildName);

    // 模糊查询公会的人 人员状态!
    @RequestMapping("/likeFindPeo")
    ResultMessage likeFindPeo(
            @RequestParam("guildId") String guildId,
            @RequestParam("userName") String userName);

}
