package net.zgm.mapper;

import net.zgm.model.ZgmOrder;
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
public interface ZgmOrderMapper  {
    void addZgmOrder(@Param("userId") Integer userId, @Param("userName") String userName, @Param("receiveName") String receiveName,
                     @Param("receiveArea") String receiveArea,
                     @Param("receiveAddress") String receiveAddress, @Param("telephone") String telephone,
                     @Param("goodsId") Integer goodsId, @Param("orderNo") String orderNo);

    List<ZgmOrder> selectZgmOrderByUid(@Param("userId") Integer userId);

    void receivingZgmOrder(@Param("orderId") Integer orderId);
}