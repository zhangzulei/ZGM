package net.zgm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.zgm.model.ResultMessage;
import net.zgm.service.ZgmTaskcservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(value = "答题记录类")
public class ZgmTaskcController {

    @Autowired
    private ZgmTaskcservice zgmTaskcService;


    /**
     * 引导页查询强制展示任务
     * @param isStudent 是否是学生
     * @return 返回任务详情
     */
    @ApiOperation(value = "引导页查询强制展示任务", notes = "根据isStudent 0 或者1  判断展示任务")
    @RequestMapping(value = "/findForceTaskc", method = RequestMethod.GET)
    public ResultMessage findForceTaskc(@RequestParam(value = "isStudent") Integer isStudent) {
        ResultMessage resultMessage = zgmTaskcService.findForceTaskc(isStudent);
        return resultMessage;
    }

    /**
     * 查询最新发布的10条任务
     * @return 返回任务集合
     */
    @ApiOperation(value = "获得最新发布的任务10条", notes = "没有参数")
    @RequestMapping(value = "/selectNewTaskc",method = RequestMethod.GET)
    public ResultMessage selectNewTaskc() {
        ResultMessage resultMessage =zgmTaskcService.selectNewTaskc();
        return resultMessage;
    }

    /**
     * 查询所有任务
     *
     * @return 返回所有任务
     */
    @ApiOperation(value = "查询所有任务", notes = "查询所有已领取任务")
    @RequestMapping(value = "/selectAllTaskc",method = RequestMethod.GET)
    public ResultMessage selectAllTaskc() {
        ResultMessage resultMessage = zgmTaskcService.selectAllTaskc();
        return resultMessage;
    }
    /**
     * 查询所有任务
     *
     * @return 返回所有任务
     */
    @ApiOperation(value = "获得最热的任务10个", notes = "查询所有任务中领取率最高的10个任务")
    @RequestMapping(value = "/selectHotTaskc",method = RequestMethod.GET)
    public ResultMessage selectHotTaskc() {
        ResultMessage resultMessage = zgmTaskcService.selectHotTaskc();
        return resultMessage;
    }
    /**
     * 获取可领取任务
     *
     * @return 返回所有任务
     */
    @ApiOperation(value = "获取可领取任务", notes = "获取可领取任务")
    @RequestMapping(value = "/selectTaskcByHave",method = RequestMethod.GET)
    public ResultMessage selectTaskcByHave(@RequestParam(value = "userid") Integer userid) {
        ResultMessage resultMessage = zgmTaskcService.selectTaskcByHave(userid);
        return resultMessage;
    }
    /**
     * 获取已结束任务
     *
     * @return 返回所有任务
     */
    @ApiOperation(value = "获取已结束任务", notes = "获取已结束任务")
    @RequestMapping(value = "/selectTaskcByEnd",method = RequestMethod.GET)
    public ResultMessage selectTaskcByEnd() {
        ResultMessage resultMessage = zgmTaskcService.selectTaskcByEnd();
        return resultMessage;
    }
    /**
     * 获取任务详情
     *
     * @return 获取任务详情
     */
    @ApiOperation(value = "获取任务详情", notes = "获取任务详情")
    @RequestMapping(value = "/selectTaskcDetails",method = RequestMethod.GET)
    public ResultMessage selectTaskcDetails(@RequestParam(value = "taskcid") Integer taskcid) {
        ResultMessage resultMessage = zgmTaskcService.selectTaskcDetails(taskcid);
        return resultMessage;
    }
}
