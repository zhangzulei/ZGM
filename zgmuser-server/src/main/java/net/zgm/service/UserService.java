package net.zgm.service;

import net.zgm.model.ZgmAdmin;
import net.zgm.model.ZgmUser;
import net.zgm.model.ZgmUserMailList;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author wangxiansheng
 * @create 2019-06-03 15:25
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
public interface UserService {

    /**
     * 根据用户ID查找出所有建立亲属管理的成员
     * @param userId 当前登录的用户ID
     * @return 成员列表
     */
    List<ZgmUser> findRelaByUserId(Integer userId);

    /**
     * 新增学生记录
     * @param user 学生
     * @return 新增的数量
     */
    int insertUser(ZgmUser user);

    /**
     * 查询是否认证
     * @param userId 用户ID
     * @return 是否认证
     */
    int findIsAbout(Integer userId);

    /**
     * 用户认证
     * @param userId  用户ID
     * @return 账号密码
     */
    int createAbout(Integer userId);

    /**
     * 插入写手数据
     * @param phone 用户名(手机)
     * @param pwd 密码(随机六位数)
     * @return 插入结果
     */
    int createWrite(@Param("phone") String phone, @Param("pwd") String pwd);

    /**
     * 根据用户ID查找用户信息
     * @param userId 用户ID
     * @return 个人信息
     */
    ZgmAdmin findAdminById(Integer userId);

    void addMailList(List<ZgmUserMailList> list);

}
