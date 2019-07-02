package net.zgm.service;

import net.zgm.model.ZgmAdmin;
import net.zgm.model.ZgmGateway;

import java.util.List;

public interface ZgmGatewayService {

    /**
     * 展示当前节点详情
     * @param gatewayId
     * @return 当前节点
     */
    ZgmGateway selectZgmGatewayById(Integer gatewayId);


    /**
     * --查询所有已领取任务下的所有节点信息--
     * @param collarTaskid
     * @return
     */
    List<ZgmGateway> selectAllGatewayByTaskid(Integer collarTaskid);

    /**
     * 根据省份id查询节点
     * @param gatewayProvinceid
     * @return
     */
    List<ZgmGateway> selectZgmGatewayBySid( Integer gatewayProvinceid, Integer taskcid);

    /**
     * 查询用户登录状态的方法
     */
    ZgmAdmin selectLoginStatus(Integer userid);

}
