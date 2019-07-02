package net.zgm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.zgm.model.ResultMessage;
import net.zgm.model.TaskAndNode;
import net.zgm.model.ZgmQuestion;
import net.zgm.service.ZgmQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "题目获取")
public class ZgmQuestionController {
    @Autowired
    private ZgmQuestionService zgmQuestionService;
    @ApiOperation(value = "获取当前节点随机题目", notes = "参数用户id 当前任务领取id 相关节点id")
    @RequestMapping(value = "/selectZgmQuestionByGid", method = RequestMethod.POST)
    public ResultMessage selectZgmQuestionByGid(@RequestBody TaskAndNode taskAndNode) {
        ResultMessage resultMessage  = zgmQuestionService.selectZgmQuestionByGid(taskAndNode);
        return resultMessage;
    }


}
