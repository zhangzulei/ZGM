package net.zgm.controller;

import net.zgm.model.ResultMessage;
import net.zgm.model.ZgmAdmin;
import net.zgm.model.ZgmGateway;
import net.zgm.model.ZgmProvince;
import net.zgm.service.ZgmGatewayService;
import net.zgm.service.ZgmProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ZgmGatewayController {

    @Autowired
    private ZgmGatewayService zgmGatewayService;
    @Autowired
    private ZgmProvinceService zgmProvinceService;

    /**
     * 查询当前节点信息
     *
     * @return
     */
    @RequestMapping(value = "/selectZgmGatewayById",method = RequestMethod.GET)
    public ResultMessage selectZgmGatewayById(@RequestParam(value = "gatewayId") Integer gatewayId) {
        ResultMessage resultMessage = new ResultMessage();
        ZgmGateway zgmGateway = zgmGatewayService.selectZgmGatewayById(gatewayId);
        if (zgmGateway!=null){
            resultMessage.setMsg("查询当前节点详情成功");
            resultMessage.setStatus("200");
            resultMessage.setData(zgmGateway);
        }else{
            resultMessage.setMsg("查询当前节点详情失败");
            resultMessage.setStatus("500");
            resultMessage.setData("fail");
        }
        return resultMessage;
    }

    /**
     * 查询所有当前任务下的节点
     * @param taskcid
     * @return
     */
    @RequestMapping(value = "/selectAllGatewayByTaskid",method = RequestMethod.GET)
    public ResultMessage selectAllGatewayByTaskid(@RequestParam(value = "taskcid") Integer taskcid) {
        List<ZgmGateway> list = zgmGatewayService.selectAllGatewayByTaskid(taskcid);
        ResultMessage resultMessage = new ResultMessage();
        if (list!=null&&list.size()!=0){
            resultMessage.setMsg("查询当前任务节点详情成功");
            resultMessage.setStatus("200");
            resultMessage.setData(list);
        }else{
            resultMessage.setMsg("查询当前任务节点详情失败");
            resultMessage.setStatus("500");
            resultMessage.setData("fail");
        }
        return resultMessage;
    }

    /**
     * 查询当前省的节点
     * @param gatewayProvinceid
     * @return
     */
    @RequestMapping(value = "/selectZgmGatewayBySid",method = RequestMethod.GET)
    public ResultMessage selectZgmGatewayBySid(@RequestParam(value = "gatewayProvinceid") Integer gatewayProvinceid,@RequestParam(value = "taskcid") Integer taskcid) {
        List<ZgmGateway> list = zgmGatewayService.selectZgmGatewayBySid(gatewayProvinceid,taskcid);
        ResultMessage resultMessage = new ResultMessage();
        if (list!=null&&list.size()!=0){
            resultMessage.setMsg("查询省节点详情成功");
            resultMessage.setStatus("200");
            resultMessage.setData(list);
        }else{
            resultMessage.setMsg("查询省节点详情失败");
            resultMessage.setStatus("500");
            resultMessage.setData("fail");
        }
        return resultMessage;

    }
    /**
     * 查询所有省
     * @return
     */
    @RequestMapping(value = "/selectZgmProvince",method = RequestMethod.GET)
    public ResultMessage selectZgmProvince() {
        List<ZgmProvince> list = zgmProvinceService.selectAllZgmProvince();
        ResultMessage resultMessage = new ResultMessage();
        if (list!=null&&list.size()!=0){
            resultMessage.setMsg("查询省成功");
            resultMessage.setStatus("200");
            resultMessage.setData(list);
        }else{
            resultMessage.setMsg("查询省失败");
            resultMessage.setStatus("500");
            resultMessage.setData("fail");
        }
        return resultMessage;

    }
    /**
     * 查询当前用户登录状态
     *
     * @return
     */
    @RequestMapping(value = "/selectLoginStatus",method = RequestMethod.GET)
    public ResultMessage selectLoginStatus(@RequestParam(value = "userid") Integer userid) {
        ResultMessage resultMessage = new ResultMessage();
         ZgmAdmin zgmAdmin = zgmGatewayService.selectLoginStatus(userid);
            resultMessage.setMsg("查询当前用户登录状态成功");
            resultMessage.setStatus("200");
            resultMessage.setData(zgmAdmin);

        return resultMessage;
    }

}
