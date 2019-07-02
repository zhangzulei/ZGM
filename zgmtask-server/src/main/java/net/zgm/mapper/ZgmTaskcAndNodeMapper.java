package net.zgm.mapper;

import net.zgm.model.TaskAndNode;

import java.util.List;

public interface ZgmTaskcAndNodeMapper {
     //<!--查询当前任务下所有节点需要的步数-->
    List<TaskAndNode> selectZgmGatewayByStep(Integer taskcid );

    //<!--步数更新后更新节点步数状态-->
    int updateGatewayStepState(TaskAndNode taskAndNode);

   // <!--生成题目时更新题目状态为已生成-->
    int updateGatewayQuestionState(TaskAndNode taskAndNode);

    //改变当前节点步数完成情况

    //查询当前节点完成需要的步数


}
