package net.zgm.controller;

import net.zgm.model.ResultMessage;
import net.zgm.model.TaskAndNode;
import net.zgm.model.ZgmAdmin;
import net.zgm.model.ZgmQuestion;
import net.zgm.service.ZgmQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ZgmQuestionController {
    @Autowired
    private ZgmQuestionService zgmQuestionService;

    @RequestMapping(value = "/selectZgmQuestionByGid", method = RequestMethod.POST)
    public ResultMessage selectZgmQuestionByGid(@RequestBody TaskAndNode taskAndNode) {
        ZgmAdmin zgmAdmin = zgmQuestionService.selectZgmAdminByUid(taskAndNode.getUserid());
        ResultMessage resultMessage = new ResultMessage();
        if (zgmAdmin.getWxOpenId() == null) {
            resultMessage.setStatus("100");
            resultMessage.setMsg("用户未绑定微信，不可参与答题活动");
            return resultMessage;
        }
        List<ZgmQuestion> list = zgmQuestionService.selectZgmQuestionByGid(taskAndNode);
        if (list != null) {
            resultMessage.setMsg("查询成功");
            resultMessage.setStatus("200");
            resultMessage.setData(list);
            return resultMessage;
        } else {
            resultMessage.setMsg("查询失败请检查您的信息");
            resultMessage.setStatus("500");
            resultMessage.setData("失败");
            return resultMessage;
        }

    }


}
