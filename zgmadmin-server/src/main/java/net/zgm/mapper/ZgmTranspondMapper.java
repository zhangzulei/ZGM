package net.zgm.mapper;

import net.zgm.model.ZgmTranspond;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ZYS on 2019/4/24
 */
public interface ZgmTranspondMapper {

    void addNewsTranspond(@Param("userId") Integer userId, @Param("newsId") Integer newsId, @Param("integral") String integral);


    void addVideoTranspond(@Param("userId") Integer userId, @Param("videoId") Integer videoId, @Param("integral") String integral);

    List<ZgmTranspond> selectZgmTranspondByUid(Integer userId);

    ZgmTranspond selectNewsTranspondById(@Param("userId") Integer userId, @Param("newsId") Integer newsId);

    ZgmTranspond selectVideoTranspondById(@Param("userId") Integer userId, @Param("videoId") Integer videoId);

    List<ZgmTranspond> selectZgmTranspondByDate(@Param("userId") Integer userId, @Param("newsdate") String newsdate,@Param("videodate") String videodate);

}
