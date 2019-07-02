package net.zgm.service;

import net.zgm.model.AdminTaskcQuestion;
import net.zgm.model.TaskAndNode;

import java.util.List;

public interface ZgmAdminTaskcQService {

    /**
     * 查询当前用户当前任务当前节点下答题详情
     * @param taskAndNode 当前任务 当前节点信息
     * @return 返回查询到的 题目答案 和 题目code
     */
    List<AdminTaskcQuestion> selectGawQuest(TaskAndNode taskAndNode);

    /**
     * 新增 修改答题记录
     * @param adminTaskcQuestion 对象
     * @return
     */
    int insertByUpAdminTaskcQ(List<AdminTaskcQuestion>  adminTaskcQuestion);

}
