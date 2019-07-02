package net.zgm.service;

import io.swagger.models.auth.In;
import net.zgm.model.ZgmFriend;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ZgmFriendService {
    /**
     * 查询出我的好友
     * @param userid  atteUserid
     * @return 返回集合
     */
   List<ZgmFriend> selectAllFriend( Integer userid,  Integer atteUserid);
    ZgmFriend selectIsFried(Integer userid,  Integer atteUserid);
}
