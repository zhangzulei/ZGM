package net.zgm.service.impl;

import net.zgm.mapper.ZgmAdminMapper;
import net.zgm.mapper.ZgmAdminTaskcQuestionMapper;
import net.zgm.mapper.ZgmQuestionMapper;
import net.zgm.model.AdminTaskcQuestion;
import net.zgm.model.TaskAndNode;
import net.zgm.model.ZgmAdmin;
import net.zgm.model.ZgmQuestion;
import net.zgm.service.ZgmQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZgmQuestionServiceImpl implements ZgmQuestionService {
    @Autowired
    private ZgmQuestionMapper zgmQuestionMapper;
    @Autowired
    private ZgmAdminTaskcQuestionMapper adminTaskcQuestionMapper;
    @Autowired
    private ZgmAdminMapper zgmAdminMapper;

    @Override
    public List<ZgmQuestion> selectAllByNode(Integer gatewayid) {
        return zgmQuestionMapper.selectAllByNode(gatewayid);
    }


    /**
     * 进入答题节点时调用题目 包括已生成未生成
     *
     * @return 返回题目列表
     */
    @Override
    public List<ZgmQuestion> selectZgmQuestionByGid(TaskAndNode taskAndNode) {

        List<AdminTaskcQuestion> taskcQuestionList = adminTaskcQuestionMapper.selectGawQuest(taskAndNode);
        List<ZgmQuestion> questionList = new ArrayList<ZgmQuestion>();

        if (taskcQuestionList != null && taskcQuestionList.size() != 0) {

            List<String> list = new ArrayList<String>();
            for (int i = 0; i < taskcQuestionList.size(); i++) {
                list.add(taskcQuestionList.get(i).getQuestionCode());
            }
            questionList = zgmQuestionMapper.selectZgmQuestionByCode(list);
            for (int j = 0; j < questionList.size(); j++) {
                questionList.get(j).setQuestionChoice(taskcQuestionList.get(j).getQuestionChoice());
                questionList.get(j).setIsComplete(taskcQuestionList.get(j).getIsComplete());

            }
        } else {
            questionList = zgmQuestionMapper.selectZgmQuestionByGid(taskAndNode.getGateway_id());
        }
        return questionList;
    }

    @Override
    public ZgmAdmin selectZgmAdminByUid(Integer userId) {
        return zgmAdminMapper.selectZgmAdminByUid(userId);
    }
}
