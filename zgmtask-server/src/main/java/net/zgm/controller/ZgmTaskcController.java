package net.zgm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import net.zgm.model.ResultMessage;
import net.zgm.model.ZgmCollarTask;
import net.zgm.model.ZgmTaskc;
import net.zgm.service.ZgmCollarTaskService;
import net.zgm.service.ZgmTaskcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ZgmTaskcController {
    @Autowired
    private ZgmTaskcService zgmTaskcService;
    @Autowired
    private ZgmCollarTaskService zgmCollarTaskService;


    /**
     * 引导页查询强制展示任务
     *
     * @param isStudent 是否是学生
     * @return 返回任务详情
     */
    @RequestMapping(value = "/findForceTaskc", method = RequestMethod.GET)
    public ResultMessage findForceTaskc(@RequestParam(value = "isStudent") Integer isStudent) {
        ResultMessage resultMessage = new ResultMessage();
        List<ZgmTaskc> zgmTaskcs = zgmTaskcService.findForceTaskc(isStudent);
        resultMessage.setMsg("查询合适的强制任务成功");
        resultMessage.setStatus("200");
        resultMessage.setData(zgmTaskcs);
        return resultMessage;
    }

    /**
     * 查询最新发布的10条任务
     *
     * @return 返回任务集合
     */
    @RequestMapping(value = "/selectNewTaskc",method = RequestMethod.GET)
    public ResultMessage selectNewTaskc() {
        ResultMessage resultMessage = new ResultMessage();
        List<ZgmTaskc> zgmTaskcs = zgmTaskcService.selectNewTaskc();
        resultMessage.setMsg("查询最新任务成功");
        resultMessage.setStatus("200");
        resultMessage.setData(zgmTaskcs);
        return resultMessage;
    }

    /**
     * 查询所有任务
     *
     * @return 返回所有任务
     */
    @RequestMapping(value = "/selectAllTaskc",method = RequestMethod.GET)
    public ResultMessage selectAllTaskc() {
        ResultMessage resultMessage = new ResultMessage();
        List<ZgmTaskc> zgmTaskcs = zgmTaskcService.selectAllTaskc();
        resultMessage.setMsg("查询最新任务成功");
        resultMessage.setStatus("200");
        resultMessage.setData(zgmTaskcs);
        return resultMessage;
    }
    /**
     * 查询最热发布的10条任务
     *
     * @return 返回任务集合
     */
    @RequestMapping(value = "/selectHotTaskc",method = RequestMethod.GET)
    public ResultMessage selectHotTaskc() {
        ResultMessage resultMessage = new ResultMessage();
        List<ZgmCollarTask> list =zgmCollarTaskService.selectFindHotTask();
        List<Integer> list1 = new ArrayList<>();
        for (int i = 0; i <list.size() ; i++) {
            list1.add(list.get(i).getTaskcid());
        }
        List<ZgmTaskc> zgmTaskcs = zgmTaskcService.selectHotTaskc(list1);
        resultMessage.setMsg("查询最热任务成功");
        resultMessage.setStatus("200");
        resultMessage.setData(zgmTaskcs);
        return resultMessage;
    }

    /**
     * 查询任务详情
     * @param taskcid 任务id
     * @return 返回对象
     */
    @RequestMapping(value = "/selectTaskcByTaskcid",method = RequestMethod.GET)
    public ResultMessage selectTaskcByTaskcid(@RequestParam(value = "taskcid") Integer taskcid) {
        ResultMessage resultMessage = new ResultMessage();
        ZgmTaskc zgmTaskc = zgmTaskcService.selectTaskcByTaskcid(taskcid);
        resultMessage.setMsg("查询最新任务成功");
        resultMessage.setStatus("200");
        resultMessage.setData(zgmTaskc);
        return resultMessage;
    }

    /**
     * 查询已结束任务成功
     * @return
     */
    @RequestMapping(value = "/selectTaskcByEnd",method = RequestMethod.GET)
    public ResultMessage selectTaskcByEnd() {
        ResultMessage resultMessage = new ResultMessage();
        List<ZgmTaskc> zgmTaskcs = zgmTaskcService.selectTaskcByEnd();
        resultMessage.setMsg("查询已结束任务成功");
        resultMessage.setStatus("200");
        resultMessage.setData(zgmTaskcs);
        return resultMessage;

    }
    /**
     * 查询任务详情
     * @return
     */
    @RequestMapping(value = "/selectTaskcDetails",method = RequestMethod.GET)
    public ResultMessage selectTaskcDetails(@RequestParam(value = "taskcid") Integer taskcid) {
        ResultMessage resultMessage = new ResultMessage();
        ZgmTaskc zgmTaskc = zgmTaskcService.selectTaskcDetails(taskcid);
        resultMessage.setMsg("查询任务详情成功");
        resultMessage.setStatus("200");
        resultMessage.setData(zgmTaskc);
        return resultMessage;
    }
    /**
     * 查询所有可领取任务 未领取
     * @param userid
     * @return
     */
    @RequestMapping(value = "/selectTaskcByHave",method = RequestMethod.GET)
    public ResultMessage selectTaskcByHave(@RequestParam(value = "userid") Integer userid,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum,15);
        ResultMessage resultMessage = new ResultMessage();
        List<ZgmCollarTask> list =zgmCollarTaskService.selectStateCollarTask(userid);
        List<Integer> list1 = new ArrayList<>();
        for (int i = 0; i <list.size() ; i++) {
            list1.add(list.get(i).getTaskcid());
        }
        List<ZgmTaskc> zgmTaskcs = zgmTaskcService.selectTaskcByHave(list1);
        PageInfo<ZgmTaskc> pageInfo = new PageInfo<ZgmTaskc>(zgmTaskcs);
        resultMessage.setMsg("查询未领取任务成功");
        resultMessage.setStatus("200");
        resultMessage.setData(pageInfo);
        return resultMessage;
    }
}
