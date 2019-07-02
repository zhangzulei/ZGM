package net.zgm.service.impl;

import net.zgm.mapper.ZgmAdminTaskcQuestionMapper;
import net.zgm.model.AdminTaskcQuestion;
import net.zgm.model.TaskAndNode;
import net.zgm.service.ZgmAdminTaskcQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZgmAdminTaskcQserviceImpl implements ZgmAdminTaskcQService {
    @Autowired
    private ZgmAdminTaskcQuestionMapper adminTaskcQuestionMapper;

    @Override
    public List<AdminTaskcQuestion> selectGawQuest(TaskAndNode taskAndNode) {
        return adminTaskcQuestionMapper.selectGawQuest(taskAndNode);
    }

    @Override
    public int insertByUpAdminTaskcQ(List<AdminTaskcQuestion> adminTaskcQuestion) {
        System.out.println("进入新增题目");
        for (int i = 0; i <adminTaskcQuestion.size() ; i++) {
            System.out.println(adminTaskcQuestion.get(i).toString());
        }
        int count = 0;
        TaskAndNode taskAndNode = new TaskAndNode();
        taskAndNode.setCollarTaskid(adminTaskcQuestion.get(1).getCollarTaskid());
        taskAndNode.setGateway_id(adminTaskcQuestion.get(1).getGateway_id());

        List<AdminTaskcQuestion> taskcQuestionList = adminTaskcQuestionMapper.selectGawQuest(taskAndNode);


        if (taskcQuestionList != null && taskcQuestionList.size() != 0) {
            System.out.println("进入修改题目");
            count = adminTaskcQuestionMapper.updateAdminTaskcQ(adminTaskcQuestion);
        } else {
            System.out.println("进入新增");
            count = adminTaskcQuestionMapper.insertAdminTaskcQ(adminTaskcQuestion);
        }
        return count;
    }


}
