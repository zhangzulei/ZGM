package net.zgm.service.impl;

import net.zgm.mapper.GuildlogMapper;
import net.zgm.model.ZgmGuildLog;
import net.zgm.service.GuildlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
public class GuildlogServiceImpl implements GuildlogService {

    @Autowired
    GuildlogMapper guildlogMapper;

    @Override
    public int insertLog(ZgmGuildLog guildLog) {
        return guildlogMapper.insertLog(guildLog);
    }

    @Override
    public int deleteLog(Integer id) {
        int result = guildlogMapper.deleteLog(id);
        if (result > 0) {
            return -1;
        }
        return 0;
    }

    @Override
    public List<ZgmGuildLog> findByGuildId(String guildId) {
        return guildlogMapper.findByGuildId(guildId);
    }

    @Override
    public ZgmGuildLog findById(Integer id) {
        return guildlogMapper.findById(id);
    }
}
