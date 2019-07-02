package net.zgm.mapper;

import net.zgm.model.ZgmEnteAuth;
import net.zgm.model.ZgmSelfAuth;

/**
 * @author wangxiansheng
 * @create 2019-06-04 17:06
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
public interface AuthMapper {

    /**
     * 申请个人身份认证
     * @param zgmSelfAuth 个人身份认证
     * @return 插入是否成功
     */
    int insertSelfAuth(ZgmSelfAuth zgmSelfAuth);

    /**
     * 申请企业身份认证
     * @param zgmEnteAuth 企业身份认证对象
     * @return 插入是否成功
     */
    int insertEnteAuth(ZgmEnteAuth zgmEnteAuth);

    /**
     * 根据用户ID查询个人注册信息
     * @param userId 用户ID
     * @return 结果对象
     */
    ZgmSelfAuth findSelfByUserId(Integer userId);

    /**
     * 根据用户ID查询企业注册信息
     * @param userId 用户ID
     * @return 结果对象
     */
    ZgmEnteAuth findEnteByUserId(Integer userId);

}
