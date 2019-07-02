package net.zgm.service.impl;

import net.zgm.mapper.ZgmFriendMapper;
import net.zgm.model.ZgmFriend;
import net.zgm.service.ZgmFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZgmFriendServiceImpl implements ZgmFriendService {
    @Autowired
    private ZgmFriendMapper zgmFriendMapper;


    @Override
    public List<ZgmFriend> selectAllFriend( Integer userid,Integer atteUserid) {
       List<ZgmFriend> listMy =  zgmFriendMapper.selectAllFriend(userid,atteUserid);
        return listMy;
    }

    @Override
    public ZgmFriend selectIsFried(Integer userid, Integer atteUserid) {
        ZgmFriend zgmFriend = zgmFriendMapper.selectIsFried(userid,atteUserid);
        return zgmFriend;
    }
}
