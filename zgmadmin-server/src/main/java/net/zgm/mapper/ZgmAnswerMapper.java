package net.zgm.mapper;

import net.zgm.model.ZgmAnswer;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * Created by ZYS on 2019/4/27
 */

public interface ZgmAnswerMapper {
    ZgmAnswer selectZgmAnswerById(@Param("userId") Integer userId, @Param("gatewayId") Integer gatewayId);
    void  addZgmAnswer(@Param("userId") Integer userId, @Param("gatewayId") Integer gatewayId,@Param("integral")Integer integral);
    List<ZgmAnswer> selectZgmAnswerByUid(Integer userId);
}
