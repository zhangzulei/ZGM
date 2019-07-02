package net.zgm.mapper;


import net.zgm.model.ZgmNews;
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
public interface ZgmNewsMapper {
    List<ZgmNews> selectZgmNewsByGId(Integer gatewayId);

    void updateNewsclickzanNum(@Param("newsId") Integer newsId, @Param("clicknum") Integer clicknum);

    ZgmNews selectZgmNewsByNid(Integer newsId);


}