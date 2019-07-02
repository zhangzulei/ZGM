package net.zgm.mapper;


import net.zgm.model.TaskAndNode;
import net.zgm.model.ZgmQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2019-04-22
 */
public interface ZgmQuestionMapper {
    /**
     * 查询当前任务 当前节点的所有题目
     */
    List<ZgmQuestion> selectAllByNode(Integer gatewayid);
////     <!--查询当前节点下的题目信息-->
//    <select id="" parameterType="int" resultType="net.zgm.model.ZgmGateway">
//    select * from zgm_question where gateway_id=#{value }
//    </select>
//    <!--未生成过题目 随机产生10道题目-->
    List<ZgmQuestion> selectZgmQuestionByGid(Integer gateway_id);

//    <!--已生成过题目根据题目code 查询题目-->
    List<ZgmQuestion> selectZgmQuestionByCode(List<String> list);


}