package net.zgm.mapper;


import net.zgm.model.ZgmAddress;
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
public interface ZgmAddressMapper {
    List<ZgmAddress> selectZgmAddressByUid(Integer userId);

    void addZgmAddress(@Param("userId") Integer userId, @Param("receiveArea") String receiveArea,
                       @Param("receiveName") String receiveName, @Param("receiveAddress") String receiveAddress,
                       @Param("defaultAddress") Integer defaultAddress, @Param("telephone") String telephone);

    void updateZgmAddress(@Param("addressId") Integer addressId, @Param("receiveArea") String receiveArea,
                          @Param("receiveName") String receiveName, @Param("receiveAddress") String receiveAddress,
                          @Param("defaultAddress") Integer defaultAddress, @Param("telephone") String telephone);

    void deleteZgmAddress(Integer addressId);


    ZgmAddress selectdefaultAddress(@Param("userId") Integer userId);

    void updatedefaultAddress(Integer addressId);


}