package net.zgm.mapper;


import net.zgm.model.ZgmClickzan;
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
public interface ZgmClickzanMapper {
    void addNewsClickzan(@Param("userId") Integer userId, @Param("newsId") Integer newsId);

    void deleteNewsClickzan(@Param("userId") Integer userId, @Param("newsId") Integer newsId);

    void addVideoClickzan(@Param("userId") Integer userId, @Param("videoId") Integer videoId);

    void deleteVideoClickzan(@Param("userId") Integer userId, @Param("videoId") Integer videoId);

    ZgmClickzan selectIfclickNews(@Param("userId") Integer userId, @Param("newsId") Integer newsId);

    ZgmClickzan selectIfclickVideo(@Param("userId") Integer userId, @Param("videoId") Integer videoId);

    List<ZgmClickzan> selectZgmClickzanByUid(Integer userId);
}