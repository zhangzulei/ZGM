package net.zgm.mapper;

import net.zgm.model.ZgmMotorcade;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ZYS on 2019/4/26
 */
public interface ZgmMotorcadeMapper {
    void updatecoordinate(@Param("motorcadeId") Integer motorcadeId, @Param("motorcadeLat") String motorcadeLat, @Param("motorcadeLon") String motorcadeLon);
    ZgmMotorcade selectZgmMotorcade();
}
