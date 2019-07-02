package net.zgm.service;

import net.zgm.model.TaskAndNode;
import net.zgm.model.ZgmAdmin;
import net.zgm.model.ZgmQuestion;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ZgmQuestionService {
    /**
     * 查询当前任务 当前节点的所有题目
     */
    List<ZgmQuestion> selectAllByNode(Integer gatewayid);
    ////     <!--查询当前节点下的题目信息-->
//    <select id="" parameterType="int" resultType="net.zgm.model.ZgmGateway">
//    select * from zgm_question where gateway_id=#{value }
//    </select>
////    <!--未生成过题目 随机产生10道题目-->
//    List<ZgmQuestion> selectZgmQuestionByGid();
//
//    //    <!--已生成过题目根据题目code 查询题目-->
//    List<ZgmQuestion> selectZgmQuestionByGid(List<String> list);
    //生成 调用题目
    List<ZgmQuestion> selectZgmQuestionByGid(TaskAndNode taskAndNode);
    //查询用户信息
    ZgmAdmin selectZgmAdminByUid(Integer userId);

}
