package net.zgm.service;

import net.zgm.model.ResultMessage;
import net.zgm.model.ZgmGateway;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@FeignClient("zgmtask-server")
public interface ZgmGatewayService {

    /**
     * 展示当前节点详情
     * @param gatewayId
     * @return 当前节点
     */
    @RequestMapping(value = "/selectZgmGatewayById",method = RequestMethod.GET)
    ResultMessage selectZgmGatewayById(@RequestParam(value = "gatewayId") Integer gatewayId);


    /**
     * --查询所有已领取任务下的所有节点信息--
     * @param taskcid
     * @return
     */
    @RequestMapping(value = "/selectAllGatewayByTaskid",method = RequestMethod.GET)
    ResultMessage selectAllGatewayByTaskid(@RequestParam(value = "taskcid") Integer taskcid);

    /**
     * 根据省份id查询节点
     * @param gatewayProvinceid
     * @return
     */
    @RequestMapping(value = "/selectZgmGatewayBySid",method = RequestMethod.GET)
    ResultMessage selectZgmGatewayBySid(@RequestParam(value = "gatewayProvinceid") Integer gatewayProvinceid,@RequestParam(value = "taskcid") Integer taskcid);

    /**
     * 查询所有省
     * @return
     */
    @RequestMapping(value = "/selectZgmProvince",method = RequestMethod.GET)
    ResultMessage selectZgmProvince();

    /**
     * 查询当前用户登录状态
     * @param userid
     * @return
     */
    @RequestMapping(value = "/selectLoginStatus",method = RequestMethod.GET)
    ResultMessage selectLoginStatus(@RequestParam(value = "userid") Integer userid);

}
