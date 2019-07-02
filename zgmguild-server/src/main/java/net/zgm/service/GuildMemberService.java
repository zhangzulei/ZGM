package net.zgm.service;

import net.zgm.model.ZgmGuild;
import net.zgm.model.ZgmGuildPeo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author wangxiansheng
 * @create 2019-06-04 11:36
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
public interface GuildMemberService {

    /**
     * 根据成员状态查询成员列表
     * @param state 状态值
     * @return 成员列表
     */
    List<ZgmGuildPeo> findByState(Integer state);

    /**
     * 根据成员状态查询某公会成员列表
     * @param state 状态值
     * @return 成员列表
     */
    List<ZgmGuildPeo> findByStateAndGuild(Integer state,String guildId);

    /**
     * 新增成员
     * @param guildPeo 成员对象
     * @return 是否成功
     */
    int insertMember(ZgmGuildPeo guildPeo);

    /**
     * 更改会员信息
     * @param zgmGuildPeo 会员信息
     * @return 执行结果
     */
    int updateMember(ZgmGuildPeo zgmGuildPeo);

    /**
     * 根据公会ID查找成员列表
     * @param guildId 公会ID
     * @return 成员列表
     */
    List<ZgmGuildPeo> findAll(String guildId);

    /**
     * 根据ID查找成员信息
     * @param id 用户ID
     * @param guildId 公会ID
     * @return 成员信息
     */
    ZgmGuildPeo findById(Integer id,String guildId);

    /**
     * 根据用户ID查找加入的公会列表
     * @param userId 用户ID
     * @return 公会列表
     */
    List<ZgmGuild> findByUserId(Integer userId);


    /**
     * 根据ID查找成员详细信息
     * @param id userId
     * @param guildId 公会ID
     * @return 结果集
     */
    Map findInfoById(@Param("id") Integer id, @Param("guildId") String guildId);

    /**
     * 根据公会ID和查询名称查看
     * @param guildId 公会ID
     * @param userName 用户名称
     * @return 结果集
     */
    List<ZgmGuild> likeFindPeo(String guildId,String userName);

}
