package net.zgm.service;

import net.zgm.model.ResultMessage;
import net.zgm.model.ZgmCollarTask;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("zgmtask-server")
public interface ZgmCollarTaskService {

    //引导页新增任务
    @RequestMapping(value = "/insertCollarTaskc",method = RequestMethod.POST)
    ResultMessage insertCollarTaskc(@RequestBody ZgmCollarTask zgmCollarTask);

    //免费领取修改为付费
    @RequestMapping(value = "/findForceTaskc",method = RequestMethod.POST)
    ResultMessage updateTaskcMode(@RequestParam(value = "collarTaskcMode") String collarTaskcMode);

    //查询所有已领取任务
    @RequestMapping(value = "/selectAllCollarTask",method = RequestMethod.GET)
    ResultMessage selectAllCollarTask(@RequestParam(value = "userid") Integer userid,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum);

    //查询所有已领取未过期任务
    @RequestMapping(value = "/selectStateCollarTask",method = RequestMethod.GET)
    ResultMessage selectStateCollarTask(@RequestParam(value = "userid") Integer userid,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum);

    //查询最新领取任务 sql直接查询最新的不需要参数
    @RequestMapping(value = "/selectNewCollarTask",method = RequestMethod.GET)
    ResultMessage selectNewCollarTask(@RequestParam(value = "userid") Integer userid);
    //查询已完成任务
    @RequestMapping(value = "/selectCollarTaskcWan",method = RequestMethod.GET)
    ResultMessage selectCollarTaskcWan(@RequestParam(value = "userid") Integer userid,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum);

//    //查询任务领取前十的任务 定时更新任务属性后台更新
//    @RequestMapping(value = "/findForceTaskc",method = RequestMethod.GET)
//    ResultMessage selectFindHotTask();
    //    //更新任务热门状态
//    void updateCollarTaskHot(List<ZgmCollarTask> collarTasks);
    //更新已领取任务的步数
    @RequestMapping(value = "/updateCollarTaskcStep",method = RequestMethod.POST)
    ResultMessage updateCollarTaskcStep(@RequestParam(value = "step") Integer step, @RequestParam(value = "userid") Integer userid);
    @RequestMapping(value = "/selectCollarTaskcEnd",method = RequestMethod.GET)
    ResultMessage selectCollarTaskcEnd(@RequestParam(value = "userid") Integer userid,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum);

    @RequestMapping(value = "/selectCollarTaskcBycid",method = RequestMethod.GET)
    ResultMessage selectCollarTaskcBycid(@RequestParam(value = "userid") Integer userid,@RequestParam(value = "collarTaskid")Integer collarTaskid);

}
