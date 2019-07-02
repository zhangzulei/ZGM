package net.zgm.service.impl;

import net.zgm.mapper.GuildLeavMessMapper;
import net.zgm.model.ZgmGuildLeavmess;
import net.zgm.service.GuildLeavMessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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
public class GuildLeavMessServiceImpl implements GuildLeavMessService {

    @Autowired
    GuildLeavMessMapper guildLeavMessMapper;

    @Override
    public int insertLeavMess(ZgmGuildLeavmess leavmess) {
        leavmess.setDate(new Date());
        leavmess.setIsdelete(0);
        return guildLeavMessMapper.insert(leavmess);
    }

    @Override
    public int deleteleavMess(Integer id) {
        return guildLeavMessMapper.deleteleavMess(id);
    }

    @Override
    public List<ZgmGuildLeavmess> findByGuildId(String guildId) {
        return guildLeavMessMapper.findByGuildId(guildId);
    }

    @Override
    public ZgmGuildLeavmess findById(Integer id) {
        return guildLeavMessMapper.findById(id);
    }
}
