package net.zgm.mapper;

import net.zgm.model.ZgmCollarTask;
import net.zgm.model.ZgmTaskc;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 已领取任务
 */
public interface ZgmCollarTaskMapper {
    //引导页新增任务
    int insertCollarTaskc(ZgmCollarTask zgmCollarTask);

    //免费领取修改为付费
    int updateTaskcMode(String collarTaskcMode);

    //查询所有已领取任务
    List<ZgmCollarTask> selectAllCollarTask(@Param("userid") Integer userid);

    //查询所有已领取未过期任务
    List<ZgmCollarTask> selectStateCollarTask(@Param("userid") Integer userid);

    //查询最新领取任务 返回任务sql直接查询最新的不需要参数
    ZgmCollarTask selectNewCollarTask(@Param("userid") Integer userid);

    //查询任务最热前十的任务 定时更新任务属性后台更新
    List<ZgmCollarTask> selectFindHotTask();

    //    //更新任务过期状态
   void updateCollarTaskEnd(List<ZgmTaskc> Taskcs);
    //更新已领取任务的步数
    int updateCollarTaskcStep(@Param("step") Integer step,@Param("userid") Integer userid);

    //更新已领取任务状态是否完成 根据时间定时调取 根据步数 更新完成
    int updateCollarTaskcState (ZgmCollarTask zgmCollarTask);

    /**
     * 查询所有已完成的任务
     * @param userid
     * @return
     */
    List<ZgmCollarTask> selectCollarTaskcWan(@Param("userid") Integer userid);
    /**
     * 查询所有已领取未完成 已结束的任务 6.19
     * @return
     */
    List<ZgmCollarTask> selectCollarTaskcEnd( @Param("userid") Integer userid);

    ZgmCollarTask selectCollarTaskcBycid(@Param("userid") Integer userid,@Param("collarTaskid") Integer collarTaskid);

    /**
     * 查询当前任务的步数
     * @return
     */
    int selectCollarTaskcStep(ZgmCollarTask zgmCollarTask);

    /**
     * 查询当前任务已领取ID
     * @param zgmCollarTask
     * @return
     */
    int selectCollarTaskcCid(ZgmCollarTask zgmCollarTask);
}
