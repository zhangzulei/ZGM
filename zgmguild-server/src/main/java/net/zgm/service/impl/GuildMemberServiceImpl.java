package net.zgm.service.impl;

import net.zgm.mapper.GuildMemberMapper;
import net.zgm.model.ZgmGuild;
import net.zgm.model.ZgmGuildLog;
import net.zgm.model.ZgmGuildPeo;
import net.zgm.service.GuildMemberService;
import net.zgm.service.GuildlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wangxiansheng
 * @create 2019-06-04 11:37
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
@Transactional
@Service
public class GuildMemberServiceImpl implements GuildMemberService {

    @Autowired
    GuildMemberMapper guildMemberMapper;

    @Autowired
    GuildlogService guildlogService;

    @Override
    public List<ZgmGuildPeo> findByState(Integer state) {
        return guildMemberMapper.findByState(state);
    }

    @Override
    public List<ZgmGuildPeo> findByStateAndGuild(Integer state, String guildId) {
        return guildMemberMapper.findByStateAndGuild(state,guildId);
    }

    @Override
    public int insertMember(ZgmGuildPeo guildPeo) {
        guildPeo.setCreateDate(new Date());
        guildPeo.setIsadopt(3);
        guildPeo.setRole(1);
        int result = guildMemberMapper.insertMember(guildPeo);
        if(result > 0){
            guildlogService.insertLog(new ZgmGuildLog(new Date(),guildPeo.getGuildId(),"提交加入公会申请",guildPeo.getUserId()));
            return 1;
        }
        return 0;
    }

    @Override
    public int updateMember(ZgmGuildPeo zgmGuildPeo) {
        return guildMemberMapper.updateMember(zgmGuildPeo);
    }

    @Override
    public List<ZgmGuildPeo> findAll(String guildId) {
        return guildMemberMapper.findAll(guildId);
    }

    @Override
    public ZgmGuildPeo findById(Integer id,String guildId) {
        return guildMemberMapper.findById(id,guildId);
    }

    @Override
    public Map findInfoById(Integer id, String guildId) {
        return guildMemberMapper.findInfoById(id, guildId);
    }

    @Override
    public List<ZgmGuild> findByUserId(Integer userId) {
        return guildMemberMapper.findByUserId(userId);
    }

    @Override
    public List<ZgmGuild> likeFindPeo(String guildId, String userName) {
        return guildMemberMapper.likeFindPeo(guildId, userName);
    }
}
