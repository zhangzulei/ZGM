package net.zgm.service;

import net.zgm.model.ZgmCollarTask;
import net.zgm.model.ZgmTaskc;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ZgmCollarTaskService {

    //引导页新增任务
    int insertCollarTaskc(ZgmCollarTask zgmCollarTask);

    //免费领取修改为付费
    int updateTaskcMode(String collarTaskcMode);

    //查询所有已领取任务
    List<ZgmCollarTask> selectAllCollarTask(Integer userid);

    //查询所有已领取未过期任务
    List<ZgmCollarTask> selectStateCollarTask(Integer userid);

    //查询最新领取任务 sql直接查询最新的不需要参数
    ZgmCollarTask selectNewCollarTask(Integer userid);

    //查询任务领取前十的任务 定时更新任务属性后台更新
     List<ZgmCollarTask> selectFindHotTask();

    //    //更新任务热门状态
//    void updateCollarTaskHot(List<ZgmCollarTask> collarTasks);
    //更新已领取任务的步数
    Date updateCollarTaskcStep(Integer step, Integer userid);


    /**
     * 查询已完成任务
     * @param userid
     */
    List<ZgmCollarTask> selectCollarTaskcWan(Integer userid);
    /**
     * 查询所有已领取未完成 已结束的任务 6.19
     * @return
     */
    List<ZgmCollarTask> selectCollarTaskcEnd(Integer userid);

    /**
     * 查询当前已领取任务
     * @param userid
     * @param collarTaskid
     * @return
     */
    ZgmCollarTask selectCollarTaskcBycid( Integer userid, Integer collarTaskid);
    /**
     * 查询当前任务已领取ID
     * @param zgmCollarTask
     * @return
     */
    int selectCollarTaskcCid(ZgmCollarTask zgmCollarTask);

}
