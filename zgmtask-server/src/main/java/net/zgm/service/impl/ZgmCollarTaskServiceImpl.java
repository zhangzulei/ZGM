package net.zgm.service.impl;

import net.zgm.mapper.ZgmAdminMapper;
import net.zgm.mapper.ZgmCollarTaskMapper;
import net.zgm.mapper.ZgmTaskcMapper;
import net.zgm.model.ZgmCollarTask;
import net.zgm.model.ZgmTaskc;
import net.zgm.service.ZgmCollarTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ZgmCollarTaskServiceImpl implements ZgmCollarTaskService {

    @Autowired
    private ZgmCollarTaskMapper zgmCollarTaskMapper;
    @Autowired
    private ZgmTaskcMapper zgmTaskcMapper;
    @Autowired
    private ZgmAdminMapper zgmAdminMapper;

    /**
     * 引导页强制领取任务
     * @param zgmCollarTask 任务具体信息 和用户信息
     */
    @Override
    public int insertCollarTaskc(ZgmCollarTask zgmCollarTask) {
        zgmCollarTask.setCollarTaskcCreateTime(new Date());
        zgmCollarTask.setCollarTaskcStep(0);
        zgmCollarTask.setCollarTaskcMode("0");
        zgmCollarTask.setCollarTaskcType("0");
        zgmCollarTask.setCollarTaskcState("1");
        int userid = zgmCollarTask.getUserid();
        int count= zgmCollarTaskMapper.insertCollarTaskc(zgmCollarTask);
        if (count>0){
            zgmAdminMapper.updateLoginStatus(userid);
        }
       return count;
    }

    /**
     * 更新付费类型必须是免费领取 才可以更新
     * @param collarTaskcMode 传递付费类型编码
     */
    @Override
    public int updateTaskcMode(String collarTaskcMode) {
        int count =zgmCollarTaskMapper.updateTaskcMode(collarTaskcMode);
        return count;
    }

    /**
     * 查询所有已领取任务
     * @return
     */
    @Override
    public List<ZgmCollarTask> selectAllCollarTask(Integer adminid) {
        return zgmCollarTaskMapper.selectAllCollarTask(adminid);
    }

    /**
     * 查询所有已领取未过期未完成任务
     * @return 返回结果集
     */
    @Override
    public List<ZgmCollarTask> selectStateCollarTask(Integer userid) {
        List<ZgmCollarTask> list = zgmCollarTaskMapper.selectStateCollarTask(userid);
        for (int i = 0; i <list.size() ; i++) {
            if (list.get(i).getCollarTaskcStep()>=list.get(i).getTaskcSumStep()){
                list.get(i).setCollarTaskcState("2");
                list.get(i).setCollarTaskcEndTime(new Date());
            }
            zgmCollarTaskMapper.updateCollarTaskcState(list.get(i));
        }

        return list;
    }

    /**
     * 查询最新任务
     * @return 返回最新的一个任务详情
     */
    @Override
    public ZgmCollarTask selectNewCollarTask(Integer userid) {
        return zgmCollarTaskMapper.selectNewCollarTask(userid);
    }

    /**
     * 查询最热门的10条任务
     * @return 返回热门集合
     */
    @Override
    public List<ZgmCollarTask> selectFindHotTask() {
        return zgmCollarTaskMapper.selectFindHotTask();
    }

//    /**
//     * 更新热门任务标签
//     * @param collarTasks 根据查询出的任务
//     */
//    @Override
//    public void updateCollarTaskHot(List<ZgmCollarTask> collarTasks) {
//
//    }

    /**
     * 更新任务完成步数
     * @param step 步数
     */
    @Override
    public Date updateCollarTaskcStep(Integer step,Integer userid) {
        int count = zgmCollarTaskMapper.updateCollarTaskcStep(step,userid);
        if (count>0){
            zgmAdminMapper.updateStepTime(userid);
            return  new Date();
        }else {
            return null;
        }

    }


    /**
     * 查询所有已完成任务
     * @param userid
     * @return
     */
    @Override
    public List<ZgmCollarTask> selectCollarTaskcWan(Integer userid) {
        return zgmCollarTaskMapper.selectCollarTaskcWan(userid);
    }

    @Override
    public List<ZgmCollarTask> selectCollarTaskcEnd(Integer userid) {
        return zgmCollarTaskMapper.selectCollarTaskcEnd(userid);
    }
    /**
     * 查询当前已领取任务
     * @param userid
     * @param collarTaskid
     * @return
     */
    @Override
    public ZgmCollarTask selectCollarTaskcBycid(Integer userid, Integer collarTaskid) {
        return zgmCollarTaskMapper.selectCollarTaskcBycid(userid,collarTaskid);
    }

    @Override
    public int selectCollarTaskcCid(ZgmCollarTask zgmCollarTask) {
        return zgmCollarTaskMapper.selectCollarTaskcCid(zgmCollarTask);
    }


    @Scheduled(cron = "0 0 * * * ?")
    protected void synchFabulousCollarTask() {
       List<ZgmTaskc> tlist=  zgmTaskcMapper.selectTaskcByEnd();
        zgmCollarTaskMapper.updateCollarTaskEnd(tlist);
    }



}
