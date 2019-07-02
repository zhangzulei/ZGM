package net.zgm.mapper;

import net.zgm.model.ZgmAdmin;
import org.apache.ibatis.annotations.Param;


public interface ZgmAdminMapper {

    ZgmAdmin selectZgmAdminByUid(Integer userId);
    int updateStepTime(Integer userId);

    int updateLoginStatus(Integer userid);

    ZgmAdmin selectLoginStatus(Integer userid);

}