package net.zgm.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import net.zgm.model.ZgmGuildLeavmess;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangxiansheng
 * @create 2019-06-04 9:41
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
public interface GuildLeavMessMapper extends BaseMapper<ZgmGuildLeavmess> {

    /**
     * 新增留言
     * @param leavmess 留言对象
     * @return 是否成功
     */
    int insertLeavMess(ZgmGuildLeavmess leavmess);

    /**
     * 删除留言记录(逻辑删除)
     * @param id 留言ID
     * @return 是否成功
     */
    int deleteleavMess(Integer id);

    /**
     * 根据公会ID查找公会留言
     * @param guildId 公会ID
     * @return 列表
     */
    List<ZgmGuildLeavmess> findByGuildId(String guildId);

    /**
     * 根据ID查找留言对象
     * @param id 留言id
     * @return 留言对象
     */
    ZgmGuildLeavmess findById(Integer id);

}
