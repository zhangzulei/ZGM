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
public interface ZgmGatewayMapper{
    ZgmGateway selectZgmGatewayById(Integer gatewayId);

    List<ZgmGateway> queryAllZgmGateway();

    List<ZgmGoods> selectZgmGoodsByGId(Integer gatewayId);

    List<ZgmGateway> selectZgmGatewayByPid(Integer provinceId);
}