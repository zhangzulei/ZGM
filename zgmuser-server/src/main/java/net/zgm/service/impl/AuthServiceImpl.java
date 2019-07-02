package net.zgm.service.impl;

import net.zgm.mapper.AuthMapper;
import net.zgm.model.ZgmEnteAuth;
import net.zgm.model.ZgmSelfAuth;
import net.zgm.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wangxiansheng
 * @create 2019-06-05 8:59
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
@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper authMapper;

    @Override
    public int insertSelfAuth(ZgmSelfAuth zgmSelfAuth) {
        zgmSelfAuth.setState(2);
        return authMapper.insertSelfAuth(zgmSelfAuth);
    }

    @Override
    public int insertEnteAuth(ZgmEnteAuth zgmEnteAuth) {
        zgmEnteAuth.setState(2);
        return authMapper.insertEnteAuth(zgmEnteAuth);
    }

    /**
     * 查询用户的认证状态
     * @param userId 用户Id
     * @return  0-未认证 1-个人认证 2-企业认证 3-全部认证
     */
    @Override
    public int findAuthState(Integer userId) {

        int state = 0;

        // 企业认证对象
        ZgmEnteAuth enteByUserId = authMapper.findEnteByUserId(userId);

        // 个人认证对象
        ZgmSelfAuth selfByUserId = authMapper.findSelfByUserId(userId);

        if(enteByUserId != null && enteByUserId.getState().intValue() == 3){
            state = 2;
        }
        if(selfByUserId != null && selfByUserId.getState().intValue() == 3){
            state = 1;
        }
        if(selfByUserId != null && enteByUserId != null && enteByUserId.getState().intValue() == 3 && selfByUserId.getState().intValue() == 3){
            state = 3;
        }

        return state;
    }

}
