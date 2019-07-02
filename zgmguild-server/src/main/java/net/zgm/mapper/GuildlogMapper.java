package net.zgm.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import net.zgm.model.ZgmGuildLeavmess;
import net.zgm.model.ZgmGuildLog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangxiansheng
 * @create 2019-06-04 9:44
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
@Repository
public interface GuildlogMapper extends BaseMapper<ZgmGuildLog> {

    /**
     * 新增日志记录
     * @param guildLog 日志记录
     * @return 是否成功
     */
    int insertLog(ZgmGuildLog guildLog);

    /**
     * 删除日志记录(逻辑删除)
     * @param id 日志ID
     * @return 是否成功
     */
    int deleteLog(Integer id);


    /**
     * 根据公会ID查找日志记录
     * @param guildId 日志记录
     * @return 日志记录
     */
    List<ZgmGuildLog> findByGuildId(String guildId);

    /**
     * 根据ID查找日志对象
     * @param id ID
     * @return 日志对象
     */
    ZgmGuildLog findById(Integer id);

}
