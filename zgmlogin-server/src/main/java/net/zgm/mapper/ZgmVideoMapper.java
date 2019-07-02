package net.zgm.mapper;

import net.zgm.model.ZgmVideo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ZYS on 2019/4/22
 */
public interface ZgmVideoMapper {
    List<ZgmVideo> selectZgmVideoByGId(Integer gatewayId);
    void updateVideoclickzanNum(@Param("videoId") Integer videoId, @Param("clicknum") Integer clicknum);
    ZgmVideo selectZgmVideoByVid(Integer videoId);
}
