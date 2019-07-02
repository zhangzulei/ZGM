package net.zgm.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import net.zgm.model.ZgmGuild;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author wangxiansheng
 * @create 2019-06-04 9:19
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
public interface GuildMapper extends BaseMapper<ZgmGuild> {

    /**
     * 申请创建公会
     *
     * @param zgmGuild 公会对象
     * @return 新增数量
     */
    int insertGuild(ZgmGuild zgmGuild);

    /**
     * 根据状态查询列表
     *
     * @param state 状态类型
     * @return 结果集合
     */
    List<ZgmGuild> findAllByState(Integer state);

    /**
     * 修改公会信息
     *
     * @param zgmGuild 公会对象
     * @return 是否成功
     */
    int updateGuild(ZgmGuild zgmGuild);

    /**
     * 修改公会状态
     *
     * @param state 修改后的状态值
     * @return 结果
     */
    int updateGuildState(@Param("state") Integer state, @Param("id") String id, @Param("updateDate") Date updateDate);

    /**
     * 根据公会ID查找公会对象
     *
     * @param guildId 公会ID
     * @return 公会对象
     */
    ZgmGuild findById(String guildId);

    /**
     * 根据县/区 ID 和 公会名称 模糊查询
     * @param areaId 县/区 ID
     * @param guildName 公会名称
     * @return 结果集
     */
    List<ZgmGuild> findByLike(@Param("areaId") Integer areaId,
                              @Param("guildName") String guildName);

}
