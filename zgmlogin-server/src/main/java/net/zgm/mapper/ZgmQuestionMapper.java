package net.zgm.mapper;


import net.zgm.model.ZgmQuestion;
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
public interface ZgmQuestionMapper {
    List<ZgmQuestion> selectZgmQuestionByGid(@Param("gatewayId") Integer gatewayId);

}