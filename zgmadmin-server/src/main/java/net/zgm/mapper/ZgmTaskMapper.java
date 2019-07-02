package net.zgm.mapper;

import net.zgm.model.ZgmTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ZYS on 2019/4/29
 */
public interface ZgmTaskMapper {
    List<ZgmTask> queryAllZgmTask(@Param("taskId") Integer taskId);

}


