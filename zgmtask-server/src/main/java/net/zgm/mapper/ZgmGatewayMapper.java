package net.zgm.mapper;


import net.zgm.model.ZgmGateway;
import net.zgm.model.ZgmGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2019-04-22
 */
public interface ZgmGatewayMapper {
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
    List<ZgmGateway> selectZgmGatewayBySid(@Param("gatewayProvinceid") Integer gatewayProvinceid, @Param("taskcid") Integer taskcid);

}