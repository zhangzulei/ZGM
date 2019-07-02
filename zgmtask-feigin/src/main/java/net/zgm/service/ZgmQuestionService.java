package net.zgm.service;

import net.zgm.model.ResultMessage;
import net.zgm.model.TaskAndNode;
import net.zgm.model.ZgmQuestion;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@FeignClient("zgmtask-server")
public interface ZgmQuestionService {
    /**
     * 查询当前任务 当前节点的所有题目
     */
//    @RequestMapping(value = "/selectAllByNode",method = RequestMethod.GET)
//    List<ZgmQuestion> selectAllByNode(Integer gatewayid);
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
    @RequestMapping(value = "/selectZgmQuestionByGid",method = RequestMethod.POST)
    ResultMessage selectZgmQuestionByGid(@RequestBody TaskAndNode taskAndNode);


}
