package net.zgm.mapper;

import net.zgm.model.ZgmAdmin;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2019-04-22
 */
public interface ZgmAdminMapper  {
    ZgmAdmin selectZgmAdminByPhone(String telephone);

    ZgmAdmin selectZgmAdminByWX(String WX);

    void addZgmAdmin(@Param("userName") String userName, @Param("wxOpenId") String wxOpenId, @Param("wxPic") String wxPic, @Param("telephone") String telephone);

    void updateIntergral(@Param("userId") Integer userId, @Param("userIntegral") Integer userIntegral);

    void updateZgmAdmin(@Param("wxOpenId") String wxOpenId, @Param("telephone") String telephone);

    ZgmAdmin selectZgmAdminByUid(Integer userId);

    void addZgmAdminBytelephone(String telephone);

    void updateTelephone(@Param("telephone") String telephone, @Param("userId") Integer userId);

    void bandWX(@Param("telephone") String telephone, @Param("userName") String userName, @Param("wxOpenId") String wxOpenId, @Param("wxPic") String wxPic);
}