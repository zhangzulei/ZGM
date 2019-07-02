package net.zgm.service.impl;

import net.zgm.mapper.GuildMapper;
import net.zgm.model.ZgmGuild;
import net.zgm.model.ZgmGuildLog;
import net.zgm.service.AuthService;
import net.zgm.service.GuildService;
import net.zgm.service.GuildlogService;
import net.zgm.util.IdCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangxiansheng
 * @create 2019-06-04 11:38
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
public class GuildServiceImpl implements GuildService {

    @Autowired
    GuildMapper guildMapper;

    @Autowired
    AuthService authService;

    @Autowired
    GuildlogService guildlogService;

    /**
     * 申请创建公会
     *
     * @param zgmGuild 公会对象
     * @return 新增数量 -1为新增失败,没有进行认证 0-新增失败 1-新增成功
     */
    @Override
    public int insertGuild(ZgmGuild zgmGuild) {
        int authState = authService.findAuthState(zgmGuild.getCreateUserid());
        // 不是未认证
        if (authState == 0) {
            return -1;
        }
        Lock lock = new ReentrantLock();
        lock.lock();
        String id = IdCreate.getUUID();
        zgmGuild.setId(id);
        // 当前人员为 0
        zgmGuild.setGuildCurrPeo(0);
        // 人数上限
        zgmGuild.setGuildMaxPeo(500);
        // 设置状态待审核
        zgmGuild.setGuildState(1);
        //创建时间
        zgmGuild.setCreateDate(new Date());
        zgmGuild.setUpdateDate(zgmGuild.getCreateDate());
        int result = guildMapper.insertGuild(zgmGuild);
        if (result > 0) {
            guildlogService.insertLog(new ZgmGuildLog(new Date(), zgmGuild.getId(), "创建公会", zgmGuild.getCreateUserid()));
            return 1;
        }
        lock.unlock();
        return 0;
    }

    /**
     * 根据状态查询列表
     *
     * @param state 状态类型
     * @return 结果集合
     */
    @Override
    public List<ZgmGuild> findAllByState(Integer state) {
        return guildMapper.findAllByState(state);
    }

    /**
     * 修改公会信息
     *
     * @param zgmGuild 公会对象
     * @return 是否成功
     */
    @Override
    public int updateGuild(ZgmGuild zgmGuild) {
        zgmGuild.setUpdateDate(new Date());
        try {
            int result = guildMapper.updateGuild(zgmGuild);
            if (result > 0) {
                guildlogService.insertLog(new ZgmGuildLog(new Date(), zgmGuild.getId(), "修改公会信息", zgmGuild.getCreateUserid()));
                return 1;
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }

    }

    /**
     * 修改公会状态
     *
     * @param state 修改后的状态值
     * @return 结果
     */
    @Override
    public int updateGuildState(Integer state, String id) {

        ZgmGuild zgmGuild = guildMapper.findById(id);

        zgmGuild.setUpdateDate(new Date());

        zgmGuild.setGuildState(state);

        Integer result = guildMapper.updateById(zgmGuild);

        if (result.intValue() > 0) {
            guildlogService.insertLog(new ZgmGuildLog(new Date(), zgmGuild.getId(), "修改公会信息", zgmGuild.getCreateUserid()));
            return 1;
        }

        return 0;
    }

    /**
     * 根据公会ID查找公会对象
     *
     * @param guildId 公会ID
     * @return 公会对象
     */
    @Override
    public ZgmGuild findById(String guildId) {
        return guildMapper.selectById(guildId);
    }


    @Override
    public List<ZgmGuild> findByLike(Integer areaId, String guildName) {
        return guildMapper.findByLike(areaId, guildName);
    }
}
