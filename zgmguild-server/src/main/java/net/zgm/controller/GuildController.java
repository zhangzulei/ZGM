package net.zgm.controller;

import com.sun.org.apache.regexp.internal.RE;
import net.zgm.mapper.GuildMemberMapper;
import net.zgm.model.ResultMessage;
import net.zgm.model.ZgmGuild;
import net.zgm.model.ZgmGuildLeavmess;
import net.zgm.model.ZgmGuildLog;
import net.zgm.model.ZgmGuildPeo;
import net.zgm.service.GuildLeavMessService;
import net.zgm.service.GuildMemberService;
import net.zgm.service.GuildService;
import net.zgm.service.GuildlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartRequest;

import java.util.Date;
import java.util.List;

/**
 * @author wangxiansheng
 * @create 2019-06-04 11:41
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
public class GuildController {

    @Autowired
    GuildMemberService guildMemberService;

    @Autowired
    GuildService guildService;

    @Autowired
    GuildLeavMessService leavMessService;

    @Autowired
    GuildlogService guildlogService;

    // 查询用户角色 -
    @RequestMapping("/findUserRole")
    public ResultMessage findUserRole(@RequestParam(value = "userId", required = true) Integer userId,
                                      @RequestParam(value = "guildId", required = true) String guildId) {

        ZgmGuildPeo zgmGuildPeo = guildMemberService.findById(userId, guildId);

        if (zgmGuildPeo == null) {
            return new ResultMessage("500", "您未加入该公会,请先申请", null);
        }

        if (zgmGuildPeo.getIsadopt() == 0) {
            return new ResultMessage("501", "未通过审核", null);
        }

        if (zgmGuildPeo.getIsadopt() == 3) {
            return new ResultMessage("503", "管理员未查看你的申请,请等待", null);
        }

        if (zgmGuildPeo.getIsadopt() == 2) {
            return new ResultMessage("502", "您已退出该公会,请重新申请后查看", null);
        }
        Integer role = zgmGuildPeo.getRole();
        String data = role == 0 ? "管理员" : "普通成员";
        return new ResultMessage("200", role + "", data);
    }

    // 新建公会 -
    @RequestMapping("/applyCreateGuild")
    public ResultMessage applyCreateGuild(@RequestBody ZgmGuild zgmGuild) {
        int result = guildService.insertGuild(zgmGuild);
        if (result == -1) {
            return new ResultMessage("500", "未进行认证,请前往认证", null);
        } else if (result == 0) {
            return new ResultMessage("501", "当前人数较多,请过会重试", null);
        } else {
            return new ResultMessage("200", "申请成功,请等待管理员审核", null);
        }
    }

    // 公会列表(默认展示已通过审核)
    @RequestMapping("/guildListOk")
    public ResultMessage guildListOk() {
        List<ZgmGuild> list = guildService.findAllByState(0);
        List<ZgmGuild> list2 = guildService.findAllByState(4);
        list.addAll(list2);
        return new ResultMessage("200", "SUCCESS", list);
    }

    // 申请加入公会(添加日志) -
    @RequestMapping("/applyInsertGuild")
    public ResultMessage applyInsertGuild(@RequestBody ZgmGuildPeo zgmGuildPeo) {

        String guildId = zgmGuildPeo.getGuildId();

        //  判断公会人数是否已满
        ZgmGuild zgmGuild = guildService.findById(guildId);

        if(zgmGuild.getGuildState().intValue() != 0){
            return new ResultMessage("502","公会状态异常",null);
        }

        Integer guildMaxPeo = zgmGuild.getGuildMaxPeo();
        if (zgmGuild.getGuildCurrPeo().intValue() == guildMaxPeo.intValue()) {
            return new ResultMessage("501", "当前公会人员已满", null);
        }

        int result = guildMemberService.insertMember(zgmGuildPeo);

        if (result == 1) {
            return new ResultMessage("200", "申请成功,等待管理员审核", null);
        } else {
            return new ResultMessage("500", "当前人数较多,稍后再试", null);
        }
    }

    // 公会创建者审核公会申请者(添加日志) -
    @RequestMapping("/examineMember")
    public ResultMessage examineMember(
            @RequestParam(value = "userId", required = true) Integer userId,
            @RequestParam(value = "guildId", required = true) String guildId,
            @RequestParam(value = "state", required = true) Integer state) {

        ZgmGuildPeo guildPeo = guildMemberService.findById(userId, guildId);
        guildPeo.setIsadopt(state);
        int result = guildMemberService.updateMember(guildPeo);

        if (state.intValue() == 1) {
            // 人数加一
            ZgmGuild zgmGuild = guildService.findById(guildId);
            zgmGuild.setGuildCurrPeo(zgmGuild.getGuildCurrPeo() + 1);
            guildService.updateGuild(zgmGuild);
        }

        if (result > 0) {
            return new ResultMessage("200", "修改成功", guildPeo);
        } else {
            return new ResultMessage("500", "当前网络不稳定,稍后再试", null);
        }

    }

    // 加入公会申请列表 -
    @RequestMapping("/joinGuildList")
    public ResultMessage joinGuildList(@RequestParam(value = "guildId", required = true) String guildId) {
        try {
            List<ZgmGuildPeo> list = guildMemberService.findByStateAndGuild(3, guildId);
            return new ResultMessage("200", "加载成功~", list);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage("500", "当前网络不稳定", null);
        }
    }

    // 留言 -
    @RequestMapping("/createMessage")
    public ResultMessage createMessage(@RequestBody ZgmGuildLeavmess message) {
        try {
            int result = leavMessService.insertLeavMess(message);
            if (result > 0) {
                return new ResultMessage("200", "留言成功~", null);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage("500", "网络错误", null);
        }
    }

    // 留言列表 -
    @RequestMapping("/messageList")
    public ResultMessage messageList(@RequestParam(value = "guildId", required = true) String guildId) {
        try {
            List<ZgmGuildLeavmess> list = leavMessService.findByGuildId(guildId);
            return new ResultMessage("200", "SUCCESS", list);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage("500", "网络异常", null);
        }
    }

    // 管理员踢人(添加日志)-
    @RequestMapping("/removePeo")
    public ResultMessage removePeo(
            @RequestParam(value = "removeId", required = true) Integer removeId,
            @RequestParam(value = "userId", required = true) Integer userId,
            @RequestParam(value = "guildId", required = true) String guildId) {
        ZgmGuildPeo zgmGuildPeo = guildMemberService.findById(userId, guildId);
        if (zgmGuildPeo.getIsadopt().intValue() != 1) {
            return new ResultMessage("401", "账号状态异常", null);
        }
        if (zgmGuildPeo.getRole() != 0) {
            return new ResultMessage("402", "您没有这个权限", null);
        }
        ZgmGuildPeo guildPeo = guildMemberService.findById(removeId, guildId);
        guildPeo.setIsadopt(2);
        int updateMember = guildMemberService.updateMember(guildPeo);

        ZgmGuild zgmGuild = guildService.findById(guildId);
        zgmGuild.setGuildCurrPeo(zgmGuild.getGuildCurrPeo() - 1);

        try {
            guildService.updateGuild(zgmGuild);
        } catch (Exception e) {
            try {
                guildService.updateGuild(zgmGuild);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        if (updateMember > 0) {
            try {
                guildlogService.insertLog(new ZgmGuildLog(new Date(), guildId, "被管理员踢出公会", removeId));
            } catch (Exception e) {
                try {
                    guildlogService.insertLog(new ZgmGuildLog(new Date(), guildId, "被管理员踢出公会", removeId));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            return new ResultMessage("200", "SUCCESS", null);
        } else {
            return new ResultMessage("500", "网络异常", null);
        }
    }

    // 查看成员信息
   /* @RequestMapping("/findOnePeo")
    public ResultMessage findOnePeo(@RequestParam(value = "userId", required = true) Integer userId,
                                    @RequestParam(value = "guildId", required = true) String guildId) {
        ZgmGuildPeo user = guildMemberService.findById(userId, guildId);
        if (user == null || user.getIsadopt().intValue() != 1) {
            return new ResultMessage("500", "该成员不在本群", null);
        }
        return new ResultMessage("200", "SUCCESS", user);
    }*/

    //公会人员列表(通过审核) -
    @RequestMapping("/findListByGuild")
    public ResultMessage findListByGuild(@RequestParam(value = "guildId", required = true) String guildId) {
        try {
            List<ZgmGuildPeo> list = guildMemberService.findAll(guildId);
            return new ResultMessage("200", "SUCCESS", list);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage("500", "网络异常", null);
        }
    }

    // 退出公会 (添加日志) -
    @RequestMapping("/outGuild")
    public ResultMessage outGuild(
            @RequestParam(value = "userId", required = true) Integer userId,
            @RequestParam(value = "guildId", required = true) String guildId) {
        ZgmGuildPeo guildPeo = guildMemberService.findById(userId, guildId);
        guildPeo.setIsadopt(2);
        int updateMember = guildMemberService.updateMember(guildPeo);

        ZgmGuild zgmGuild = guildService.findById(guildId);
        zgmGuild.setGuildCurrPeo(zgmGuild.getGuildCurrPeo() - 1);

        try {
            guildService.updateGuild(zgmGuild);
        } catch (Exception e) {
            try {
                guildService.updateGuild(zgmGuild);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        if (updateMember > 0) {
            try {
                guildlogService.insertLog(new ZgmGuildLog(new Date(), guildId, "退出公会", userId));
            } catch (Exception e) {
                try {
                    guildlogService.insertLog(new ZgmGuildLog(new Date(), guildId, "退出公会", userId));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            return new ResultMessage("200", "SUCCESS", null);
        } else {
            return new ResultMessage("500", "网络异常", null);
        }
    }

    // 管理员申请解散(日志)
    @RequestMapping("/dissolutionGuild")
    public ResultMessage dissolutionGuild(@RequestParam(value = "userId", required = true) Integer userId,
                                          @RequestParam(value = "guildId", required = true) String guildId) {
        ZgmGuildPeo zgmGuildPeo = guildMemberService.findById(userId, guildId);
        if (zgmGuildPeo.getIsadopt().intValue() != 1) {
            return new ResultMessage("401", "账号状态异常", null);
        }
        if (zgmGuildPeo.getRole() != 0) {
            return new ResultMessage("402", "您没有这个权限", null);
        }
        int result = guildService.updateGuildState(4, guildId);
        if (result > 0) {
            try {
                guildlogService.insertLog(new ZgmGuildLog(new Date(), guildId, "申请解散公会", userId));
            } catch (Exception e) {
                try {
                    guildlogService.insertLog(new ZgmGuildLog(new Date(), guildId, "申请解散公会", userId));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            return new ResultMessage("200", "等待平台审核", null);
        }
        return new ResultMessage("500", "网络异常", null);
    }

    // 模糊查询公会 公会状态!
    @RequestMapping("/likeFindGuild")
    public ResultMessage likeFindGuild(
            @RequestParam(value = "areaId", required = true) Integer areaId,
            @RequestParam(value = "guildName", required = true) String guildName) {
        try {
            List<ZgmGuild> list = guildService.findByLike(areaId, guildName);
            return new ResultMessage("200", "SUCCESS", list);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage("500", "服务器打了会盹~", null);
        }
    }

    // 模糊查询公会的人 人员状态!
    /*@RequestMapping("/likeFindPeo")
    public ResultMessage likeFindPeo(
            @RequestParam("guildId") String guildId,
            @RequestParam("userName") String userName) {
        try {
            List<ZgmGuild> list = guildMemberService.likeFindPeo(guildId, userName);
            return new ResultMessage("200","SUCCESS",list);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage("500","ERROR",null);
        }
    }*/



}
