package net.zgm.service;

import net.zgm.model.ResultMessage;
import net.zgm.model.ZgmTaskc;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("zgmtask-server")
public interface ZgmTaskcservice {
//首次登陆用户 引导页查询 必须领取任务 参数 是否是学生
    @RequestMapping(value = "/findForceTaskc",method = RequestMethod.GET)
    ResultMessage findForceTaskc(@RequestParam(value = "isStudent") Integer isStudent);
    //查询最新的10条任务 //每天0点查询一次
    @RequestMapping(value = "/selectNewTaskc",method = RequestMethod.GET)
    ResultMessage selectNewTaskc();
    //查询所有任务  去除已领取任务
    @RequestMapping(value = "/selectAllTaskc",method = RequestMethod.GET)
    ResultMessage selectAllTaskc();
    //查询最热的10条数据
    @RequestMapping(value = "/selectHotTaskc",method = RequestMethod.GET)
    ResultMessage selectHotTaskc();

}
