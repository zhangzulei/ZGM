package net.zgm.mapper;

import net.zgm.model.ZgmTaskc;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 任务详情接口
 */
public interface ZgmTaskcMapper {
    //首次登陆用户 引导页查询 必须领取任务 参数 是否是学生
    List<ZgmTaskc> findForceTaskc(@RequestParam(value = "isStudent") Integer isStudent);

    //查询最新的10条任务 //每天0点查询一次
    List<ZgmTaskc> selectNewTaskc();

    //查询所有任务
    List<ZgmTaskc> selectAllTaskc();
    ZgmTaskc selectTaskcByTaskcid(Integer taskcid);
    //查询最热的10条数据
    List<ZgmTaskc> selectHotTaskc(List<Integer> list);
//    //更新热门任务列表
//    void updateHotTaskc(List<Integer> taskcid);

    //查询所有过期任务
    List<ZgmTaskc> selectTaskcByEnd();

    /**
     * 查询所有未过期 未进行中的未领取的任务
     * @param list 进行中的任务id
     * @return
     */
    List<ZgmTaskc> selectTaskcByHave(List<Integer> list);

    int selectTaskcSumStep(Integer taskcid);
    /**
     * 查询任务简介
     */
    ZgmTaskc selectTaskcDetails(Integer taskcid);

}
