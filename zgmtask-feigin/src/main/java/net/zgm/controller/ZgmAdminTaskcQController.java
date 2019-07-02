package net.zgm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.zgm.model.AdminTaskcQuestion;
import net.zgm.model.ResultMessage;
import net.zgm.service.ZgmAdminTaskcQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "答题记录类")
public class ZgmAdminTaskcQController {
    @Autowired
    private ZgmAdminTaskcQService zgmAdminTaskcQService;
    @ApiOperation(value = "当前任务新增题库信息和修改题目信息", notes = "录入必须是10题 参数是 用户id 当前任务id 节点id 必须填写 当前题目code当前题目答案选填 题目状态选填")
    @RequestMapping(value = "/insertByUpAdminTaskcQ",method = RequestMethod.POST)
    public ResultMessage insertByUpAdminTaskcQ(@RequestBody List<AdminTaskcQuestion> adminTaskcQuestion) {
        ResultMessage resultMessage= zgmAdminTaskcQService.insertByUpAdminTaskcQ(adminTaskcQuestion);
        return resultMessage;
    }




}
