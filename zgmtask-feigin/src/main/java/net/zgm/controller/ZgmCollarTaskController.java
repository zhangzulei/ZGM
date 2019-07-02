package net.zgm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.zgm.model.ResultMessage;
import net.zgm.model.ZgmCollarTask;
import net.zgm.service.ZgmCollarTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(value = "已领取任务接口")
public class ZgmCollarTaskController {

    @Autowired
    private ZgmCollarTaskService zgmCollarTaskService;
    /**
     * 引导页强制领取任务
     * @param zgmCollarTask 任务具体信息和用户信息
     */
    @ApiOperation(value = "任务领取", notes = "传入对象新增任务")

    @RequestMapping(value = "/insertCollarTaskc", method = RequestMethod.POST)
    public ResultMessage insertCollarTaskc(@RequestBody ZgmCollarTask zgmCollarTask) {
        ResultMessage resultMessage = zgmCollarTaskService.insertCollarTaskc(zgmCollarTask);
        return resultMessage;

    }

    /**
     * 更新付费类型必须是免费领取 才可以更新
     *
     * @param collarTaskcMode 传递付费类型编码
     */
    @ApiOperation(value = "更改付费任务类型", notes = "免费领取可改为付费任务 不可改为免费")
    @ApiImplicitParam(name = "collarTaskcMode",value = "付费传1",required = true,dataType = "string",paramType = "query")
    @RequestMapping(value = "/updateTaskcMode",method = RequestMethod.POST)
    public ResultMessage updateTaskcMode( @RequestParam(value = "collarTaskcMode") String collarTaskcMode) {
        ResultMessage resultMessage = zgmCollarTaskService.updateTaskcMode(collarTaskcMode);
        return resultMessage;

    }

    /**
     * 查询所有已领取任务
     *
     * @return
     */
    @ApiOperation(value = "获得所有已领取任务", notes = "query风格传参")
    @ApiImplicitParam(name = "userid",value = "用户id",required = true,dataType = "Integer",paramType = "query")
    @RequestMapping(value = "/selectAllCollarTask", method = RequestMethod.GET)
    public ResultMessage selectAllCollarTask(@RequestParam(value = "userid") Integer userid,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        ResultMessage resultMessage = zgmCollarTaskService.selectAllCollarTask(userid,pageNum);
        return resultMessage;

    }

    /**
     * 查询所有已领取未过期未完成任务
     *
     * @return 返回结果集
     */
    @ApiOperation(value = "获得所有已领取未过期未完成任务", notes = "query风格传参")
    @ApiImplicitParam(name = "userid",value = "用户id",required = true,dataType = "Integer",paramType = "query")
    @RequestMapping(value = "/selectStateCollarTask",method = RequestMethod.GET)
    public ResultMessage  selectStateCollarTask(@RequestParam(value = "userid") Integer userid,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        ResultMessage resultMessage = zgmCollarTaskService.selectStateCollarTask(userid,pageNum);
        return resultMessage;

    }

    /**
     * 查询最新任务
     *
     * @return 返回最新的一个任务详情
     */
    @ApiOperation(value = "获得最新领取的任务", notes = "query风格传参")
    @ApiImplicitParam(name = "userid",value = "用户id",required = true,dataType = "Integer",paramType = "query")
    @RequestMapping(value = "/selectNewCollarTask",method = RequestMethod.GET)
    public ResultMessage selectNewCollarTask(@RequestParam(value = "userid") Integer userid) {
        ResultMessage resultMessage = zgmCollarTaskService.selectNewCollarTask(userid);
        return resultMessage;
    }

    /**
     * 更新任务完成步数
     *
     */
    @ApiOperation(value = "更新所有未完成未过期的任务步数", notes = "query风格传参")
    @ApiImplicitParams({
            @ApiImplicitParam (name = "userid",value = "用户id",required = true,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam (name = "step",value = "每次更新增加的步数",required = true,dataType = "Integer",paramType = "query")
    })
    @RequestMapping(value = "updateCollarTaskcStep",method = RequestMethod.POST)
    public ResultMessage updateCollarTaskcStep(@RequestParam(value = "step") Integer step,@RequestParam(value = "userid") Integer userid) {
        ResultMessage resultMessage = zgmCollarTaskService.updateCollarTaskcStep(step, userid);

        return resultMessage;
    }
    //查询已完成任务
    @ApiOperation(value = "查询已完成任务", notes = "query风格传参")
    @RequestMapping(value = "selectCollarTaskcWan",method = RequestMethod.GET)
    public ResultMessage selectCollarTaskcWan(@RequestParam(value = "userid") Integer userid,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        ResultMessage resultMessage = zgmCollarTaskService.selectCollarTaskcWan( userid,pageNum);
        return resultMessage;
    }

    @ApiOperation(value = "查询已结束任务", notes = "query风格传参")
    @RequestMapping(value = "selectCollarTaskcEnd",method = RequestMethod.GET)
    public ResultMessage selectCollarTaskcEnd(@RequestParam(value = "userid") Integer userid,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        ResultMessage resultMessage = zgmCollarTaskService.selectCollarTaskcEnd( userid,pageNum);
        return resultMessage;
    }

    //查询当前任务
    @ApiOperation(value = "查询当前任务", notes = "query风格传参")
    @RequestMapping(value = "selectCollarTaskcBycid",method = RequestMethod.GET)
    public ResultMessage selectCollarTaskcBycid(@RequestParam(value = "userid") Integer userid,@RequestParam(value = "collarTaskid") Integer collarTaskid) {
        ResultMessage resultMessage = zgmCollarTaskService.selectCollarTaskcBycid( userid,collarTaskid);
        return resultMessage;
    }

}
