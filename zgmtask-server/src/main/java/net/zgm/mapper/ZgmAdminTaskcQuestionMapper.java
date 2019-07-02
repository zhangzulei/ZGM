package net.zgm.mapper;

import net.zgm.model.AdminTaskcQuestion;
import net.zgm.model.TaskAndNode;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ZgmAdminTaskcQuestionMapper {
    /**
     * 查询当前用户当前任务当前节点下答题详情
     * @param taskAndNode 当前任务 当前节点信息
     * @return 返回查询到的 题目答案 和 题目code
     */
    List<AdminTaskcQuestion> selectGawQuest(TaskAndNode taskAndNode);

    /**
     * 新增答题记录
     * @param adminTaskcQuestion 新增对象
     * @return
     */
    int insertAdminTaskcQ(List<AdminTaskcQuestion> adminTaskcQuestion);

    /**
     * 修改答题记录
     * @param adminTaskcQuestion
     * @return
     */
    int updateAdminTaskcQ(List<AdminTaskcQuestion> adminTaskcQuestion);

}
