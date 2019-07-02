package net.zgm.controller;

import net.zgm.model.ResultMessage;
import net.zgm.model.ZgmFriend;
import net.zgm.model.ZgmGateway;
import net.zgm.service.ZgmFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ZgmFriendController {
    @Autowired
    private ZgmFriendService zgmFriendService;

    @RequestMapping(value = "/selectAllFriend",method = RequestMethod.GET)
    public ResultMessage selectAllFriend(@RequestParam(value = "userid") Integer userid, @RequestParam(value = "atteUserid") Integer atteUserid) {
        List<ZgmFriend> list = zgmFriendService.selectAllFriend(userid,atteUserid);
        ResultMessage resultMessage = new ResultMessage();
        if (list!=null&&list.size()!=0){
            resultMessage.setMsg("查询好友成功");
            resultMessage.setStatus("200");
            resultMessage.setData(list);
        }else{
            resultMessage.setMsg("查询好友失败");
            resultMessage.setStatus("500");
            resultMessage.setData("fail");
        }
        return resultMessage;
    }
    @RequestMapping(value = "/selectIsFried",method = RequestMethod.GET)
    public ResultMessage selectIsFried(@RequestParam(value = "userid") Integer userid, @RequestParam(value = "atteUserid") Integer atteUserid) {
        ZgmFriend zgmFriend = zgmFriendService.selectIsFried(userid,atteUserid);
        ResultMessage resultMessage = new ResultMessage();
        boolean flag= false;
        if (zgmFriend!=null){
            flag=true;
            resultMessage.setMsg("查询是否好友成功");
            resultMessage.setStatus("200");
            resultMessage.setData(flag);
        }else{
            resultMessage.setMsg("查询是否好友失败");
            resultMessage.setStatus("500");
            resultMessage.setData(flag);
        }
        return resultMessage;
    }

}
