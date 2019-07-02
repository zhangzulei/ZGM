package net.zgm.service.impl;

import net.zgm.mapper.AuthMapper;
import net.zgm.mapper.MailListMapper;
import net.zgm.mapper.UserMapper;
import net.zgm.model.*;
import net.zgm.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wangxiansheng
 * @create 2019-06-03 15:26
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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<ZgmUser> findRelaByUserId(Integer userId) {
        return userMapper.findRelaByUserId(userId);
    }

    @Override
    public int insertUser(ZgmUser user) {
        user.setCreateDate(new Date());
        user.setIsdelete(0);
        return userMapper.insert(user);
    }

    @Override
    public int findIsAbout(Integer userId) {
        return userMapper.findIsAbout(userId);
    }

    @Override
    public int createAbout(Integer userId) {
        return userMapper.createAbout(userId);
    }

    @Override
    public int createWrite(String phone, String pwd) {
        return userMapper.createWrite(phone, pwd);
    }

    @Override
    public ZgmAdmin findAdminById(Integer userId) {
        return userMapper.findAdminById(userId);
    }

    @Autowired
    MailListMapper mailListMapper;

    @Override
    public void addMailList(List<ZgmUserMailList> list) {
        list.forEach(item -> {
            mailListMapper.insert(item);
        });
    }
}
