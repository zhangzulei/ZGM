package net.zgm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.zgm.model.ResultMessage;
import net.zgm.model.ZgmCollarTask;
import net.zgm.model.ZgmTaskc;
import net.zgm.service.ZgmCollarTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class ZgmCollarTaskController {
    @Autowired
    private ZgmCollarTaskService zgmCollarTaskService;

    /**
     * 领取任务
     *
     * @param zgmCollarTask 任务具体信息 和用户信息
     */
    @RequestMapping(value = "/insertCollarTaskc", method = RequestMethod.POST)
    public synchronized ResultMessage insertCollarTaskc(@RequestBody ZgmCollarTask zgmCollarTask) {
        int count = zgmCollarTaskService.insertCollarTaskc(zgmCollarTask);

        ResultMessage resultMessage = new ResultMessage();
        if (count > 0) {
          int  collarTaskcid = zgmCollarTaskService.selectCollarTaskcCid(zgmCollarTask);
            resultMessage.setMsg("成功");
            resultMessage.setStatus("200");
            resultMessage.setData(collarTaskcid);
        } else {
            resultMessage.setMsg("新增失败请检查您的信息");
            resultMessage.setStatus("500");
            resultMessage.setData("失败");
        }

        return resultMessage;

    }

    /**
     * 更新付费类型必须是免费领取 才可以更新
     *
     * @param collarTaskcMode 传递付费类型编码
     */
    @RequestMapping(value = "/updateTaskcMode", method = RequestMethod.POST)
    public ResultMessage updateTaskcMode(@RequestParam(value = "collarTaskcMode") String collarTaskcMode) {
        int count = zgmCollarTaskService.updateTaskcMode(collarTaskcMode);
        ResultMessage resultMessage = new ResultMessage();
        if (count > 0) {
            resultMessage.setMsg("修改成功");
            resultMessage.setStatus("200");
            resultMessage.setData("success");
        } else {
            resultMessage.setMsg("修改失败请检查您的信息");
            resultMessage.setStatus("500");
            resultMessage.setData("失败");
        }

        return resultMessage;

    }

    /**
     * 查询所有已领取任务
     *
     * @return
     */
    @RequestMapping(value = "/selectAllCollarTask", method = RequestMethod.GET)
    public ResultMessage selectAllCollarTask(@RequestParam(value = "userid") Integer userid,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum,15);
        ResultMessage resultMessage = new ResultMessage();
        List<ZgmCollarTask> zgmCollarTaskc = zgmCollarTaskService.selectAllCollarTask(userid);
        PageInfo<ZgmCollarTask> pageInfo = new PageInfo<ZgmCollarTask>(zgmCollarTaskc);
        resultMessage.setMsg("查询所以已领取任务成功");
        resultMessage.setStatus("200");
        resultMessage.setData(pageInfo);
        return resultMessage;
    }

    /**
     * 查询所有已领取未过期未完成任务
     *
     * @return 返回结果集
     */
    @RequestMapping(value = "/selectStateCollarTask",method = RequestMethod.GET)
    public ResultMessage  selectStateCollarTask(@RequestParam(value = "userid") Integer userid,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum,15);
        ResultMessage resultMessage = new ResultMessage();
        List<ZgmCollarTask> zgmCollarTaskc = zgmCollarTaskService.selectStateCollarTask(userid);
        PageInfo<ZgmCollarTask> pageInfo = new PageInfo<ZgmCollarTask>(zgmCollarTaskc);
        resultMessage.setMsg("查询所有已领取进行中任务成功");
        resultMessage.setStatus("200");
        resultMessage.setData(pageInfo);
        return resultMessage;

    }

    /**
     * 查询最新任务
     *
     * @return 返回最新的一个任务详情
     */
    @RequestMapping(value = "/selectNewCollarTask",method = RequestMethod.GET)
    public ResultMessage selectNewCollarTask(@RequestParam(value = "userid") Integer userid) {
        ResultMessage resultMessage = new ResultMessage();
        ZgmCollarTask zgmCollarTask = zgmCollarTaskService.selectNewCollarTask(userid);
        resultMessage.setMsg("查询最新进行中任务成功");
        resultMessage.setStatus("200");
        resultMessage.setData(zgmCollarTask);
        return resultMessage;
    }

//    /**
//     * 查询最热门的10条任务
//     *
//     * @return 返回热门集合Id
//     */
//    @RequestMapping(value = "/selectFindHotTask",method = RequestMethod.GET)
//    public ResultMessage selectFindHotTask() {
//        ResultMessage resultMessage = new ResultMessage();
//        List<ZgmCollarTask> zgmCollarTask = zgmCollarTaskService.selectFindHotTask();
//        resultMessage.setMsg("查询最热门的10条任务成功");
//        resultMessage.setStatus("200");
//        resultMessage.setData(zgmCollarTask);
//        return resultMessage;
//    }

    /**
     * 更新任务完成步数
     */
    @RequestMapping(value = "updateCollarTaskcStep",method = RequestMethod.POST)
    public ResultMessage updateCollarTaskcStep(@RequestParam(value = "step") Integer step,@RequestParam(value = "userid") Integer userid) {
        Date count = zgmCollarTaskService.updateCollarTaskcStep(step,userid);
        ResultMessage resultMessage = new ResultMessage();
        if (count != null ) {
            resultMessage.setMsg("修改成功");
            resultMessage.setStatus("200");
            resultMessage.setData(count);
        }else {
            resultMessage.setMsg("修改失败");
            resultMessage.setStatus("500");
            resultMessage.setData("修改失败");
        }
        return resultMessage;
    }

    /**
     * 暂时无用
     * @param ste
     * @return
     */
//    @RequestMapping(value = "updateCollarTaskcState",method = RequestMethod.POST)
//    public ResultMessage updateCollarTaskcState(@RequestParam(value = "step") Integer step) {
//        int count = zgmCollarTaskService.updateCollarTaskcState(step);
//        ResultMessage resultMessage = new ResultMessage();
//        if (count > 0) {
//            resultMessage.setMsg("修改成功");
//            resultMessage.setStatus("200");
//            resultMessage.setData("success");
//        }
//        return resultMessage;
//    }
    /**
     * 查询所有已完成任务
     * @param userid
     * @return
     */
    @RequestMapping(value = "selectCollarTaskcWan",method = RequestMethod.GET)
    public ResultMessage selectCollarTaskcWan(@RequestParam(value = "userid") Integer userid,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum,15);
        ResultMessage resultMessage = new ResultMessage();
        List<ZgmCollarTask> zgmCollarTasks = zgmCollarTaskService.selectCollarTaskcWan(userid);

        PageInfo<ZgmCollarTask> pageInfo = new PageInfo<ZgmCollarTask>(zgmCollarTasks);
        resultMessage.setMsg("查询已完成任务成功");
        resultMessage.setStatus("200");
        resultMessage.setData(pageInfo);
        return resultMessage;
    }
    /**
     * 查询过期任务成功
     * @param userid
     * @return
     */
    @RequestMapping(value = "selectCollarTaskcEnd",method = RequestMethod.GET)
    public ResultMessage selectCollarTaskcEnd(@RequestParam(value = "userid") Integer userid,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum,15);
        ResultMessage resultMessage = new ResultMessage();
        List<ZgmCollarTask> zgmCollarTasks = zgmCollarTaskService.selectCollarTaskcEnd(userid);
        PageInfo<ZgmCollarTask> pageInfo = new PageInfo<ZgmCollarTask>(zgmCollarTasks);
        resultMessage.setMsg("查询过期任务成功");
        resultMessage.setStatus("200");
        resultMessage.setData(pageInfo);
        return resultMessage;
    }

    /**
     * 查询当前任务
     * @param userid
     * @return
     */
    @RequestMapping(value = "selectCollarTaskcBycid",method = RequestMethod.GET)
    public ResultMessage selectCollarTaskcBycid(@RequestParam(value = "userid") Integer userid,@RequestParam(value = "collarTaskid") Integer collarTaskid) {
        ResultMessage resultMessage = new ResultMessage();
        ZgmCollarTask zgmCollarTasks = zgmCollarTaskService.selectCollarTaskcBycid(userid,collarTaskid);
        resultMessage.setMsg("查询当前任务成功");
        resultMessage.setStatus("200");
        resultMessage.setData(zgmCollarTasks);
        return resultMessage;
    }


}
