package net.zgm.service.impl;

import net.zgm.mapper.ZgmTaskcMapper;
import net.zgm.model.ZgmTaskc;
import net.zgm.service.ZgmTaskcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 *
 */
@Service
public class ZgmTaskcServiceImpl implements ZgmTaskcService {
    @Autowired
    private ZgmTaskcMapper zgmTaskcMapper;

    /**
     * 引导页查询强制展示任务
     * @param isStudent 是否是学生
     * @return 返回任务详情
     */
    @Override
    public List<ZgmTaskc> findForceTaskc(@RequestParam(value = "isStudent") Integer isStudent) {

        return zgmTaskcMapper.findForceTaskc(isStudent);
    }

    /**
     * 查询最新发布的10条任务
     * @return 返回任务集合
     */
    @Override
    public List<ZgmTaskc> selectNewTaskc() {
        return zgmTaskcMapper.selectNewTaskc();
    }

    /**
     * 查询所有任务
     * @return 返回所有任务
     */
    @Override
    public List<ZgmTaskc> selectAllTaskc() {
        return zgmTaskcMapper.selectAllTaskc();
    }

    /**
     * 查询最热10条任务
     * @return
     */
    @Override
    public List<ZgmTaskc> selectHotTaskc(List<Integer> list) {
        return zgmTaskcMapper.selectHotTaskc(list);
    }

    @Override
    public ZgmTaskc selectTaskcByTaskcid(Integer taskcid) {
        return zgmTaskcMapper.selectTaskcByTaskcid(taskcid);
    }

    @Override
    public List<ZgmTaskc> selectTaskcByEnd() {

        return zgmTaskcMapper.selectTaskcByEnd();
    }

    @Override
    public List<ZgmTaskc> selectTaskcByHave(List<Integer> list) {
        return zgmTaskcMapper.selectTaskcByHave(list);
    }

    @Override
    public ZgmTaskc selectTaskcDetails(Integer taskcid) {
        return zgmTaskcMapper.selectTaskcDetails(taskcid);
    }

}
