package net.zgm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.zgm.model.ResultMessage;
import net.zgm.model.ZgmGateway;
import net.zgm.service.ZgmGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "节点管理类")
public class ZgmGatewayController {

    @Autowired
    private ZgmGatewayService zgmGatewayService;
    /**
     * 查询当前节点信息
     * @return
     */
    @ApiOperation(value = "根据当前节点查询信息", notes = "根据当前节点id返回当前节点具体信息")
    @RequestMapping(value = "/selectZgmGatewayById",method = RequestMethod.GET)
    public ResultMessage selectZgmGatewayById(@RequestParam(value = "gatewayId") Integer gatewayId) {
        ResultMessage resultMessage = zgmGatewayService.selectZgmGatewayById(gatewayId);
        return resultMessage;
    }

    /**
     * 查询所有当前任务下的节点
     * @param taskcid
     * @return
     */
    @ApiOperation(value = "查询当前任务下所有节点", notes = "根据当前任务id返回当前所有节点")
    @RequestMapping(value = "/selectAllGatewayByTaskid",method = RequestMethod.GET)
    public ResultMessage selectAllGatewayByTaskid(@RequestParam(value = "taskcid") Integer taskcid) {
        ResultMessage resultMessage = zgmGatewayService.selectAllGatewayByTaskid(taskcid);
        return resultMessage;
    }

    /**
     * 查询当前省的节点
     * @param gatewayProvinceid
     * @return
     */
    @ApiOperation(value = "查询当前省份下的所有节点", notes = "根据当前省份id返回当前所有节点")
    @RequestMapping(value = "/selectZgmGatewayBySid",method = RequestMethod.GET)
    public ResultMessage selectZgmGatewayBySid(@RequestParam(value = "gatewayProvinceid") Integer gatewayProvinceid,@RequestParam(value = "taskcid") Integer taskcid) {
        ResultMessage resultMessage = zgmGatewayService.selectZgmGatewayBySid(gatewayProvinceid,taskcid);
        return resultMessage;

    }
    /**
     * 查询所有省
     * @return
     */
    @ApiOperation(value = "查询所有省", notes = "用于展示省市")
    @RequestMapping(value = "/selectZgmProvince",method = RequestMethod.GET)
    public ResultMessage selectZgmProvince() {
        ResultMessage resultMessage = zgmGatewayService.selectZgmProvince();
        return resultMessage;
    }
    /**
     * 查询用户登录状态
     * @return
     */
    @ApiOperation(value = "查询用户登录状态", notes = "根据登录状态决定引导任务")
    @RequestMapping(value = "/selectLoginStatus",method = RequestMethod.GET)
    public ResultMessage selectLoginStatus(@RequestParam(value = "userid") Integer userid) {
        ResultMessage resultMessage = zgmGatewayService.selectLoginStatus(userid);
        return resultMessage;
    }
}
