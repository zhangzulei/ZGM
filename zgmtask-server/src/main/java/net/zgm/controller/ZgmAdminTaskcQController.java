package net.zgm.controller;

import net.zgm.model.AdminTaskcQuestion;
import net.zgm.model.ResultMessage;
import net.zgm.model.TaskAndNode;
import net.zgm.service.ZgmAdminTaskcQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ZgmAdminTaskcQController {
    @Autowired
    private ZgmAdminTaskcQService zgmAdminTaskcQService;

    @RequestMapping(value = "/insertByUpAdminTaskcQ",method = RequestMethod.POST)
    public ResultMessage insertByUpAdminTaskcQ(@RequestBody List<AdminTaskcQuestion> adminTaskcQuestion) {
        ResultMessage resultMessage = new ResultMessage();
        int count  = zgmAdminTaskcQService.insertByUpAdminTaskcQ(adminTaskcQuestion);
        if(count>0){
            resultMessage.setMsg("个人题库更新成功");
            resultMessage.setStatus("200");
            resultMessage.setData("success");
        }else {
            resultMessage.setMsg("个人题库更新失败");
            resultMessage.setStatus("500");
            resultMessage.setData("fail");
        }
        return resultMessage;
    }
    @RequestMapping(value = "/selectGawQuest",method = RequestMethod.POST)
    public ResultMessage selectGawQuest(@RequestBody TaskAndNode taskAndNode) {
        ResultMessage resultMessage = new ResultMessage();
        List<AdminTaskcQuestion> adminTaskcQuestions  = zgmAdminTaskcQService.selectGawQuest(taskAndNode);
        if(adminTaskcQuestions.size()>0 && adminTaskcQuestions!=null){
            resultMessage.setMsg("查询当前节点题库成功");
            resultMessage.setStatus("200");
            resultMessage.setData(adminTaskcQuestions);
        }else {
            resultMessage.setMsg("个人题库更新失败");
            resultMessage.setStatus("500");
            resultMessage.setData("fail");
        }
        return resultMessage;
    }




}
