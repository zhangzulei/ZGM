package net.zgm.mapper;

import net.zgm.model.ZgmFriend;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ZgmFriendMapper {

    /**
     * 查询出我的好友
     *
     * @return 返回集合
     */
    List<ZgmFriend> selectAllFriend(@Param("userid") Integer userid, @Param("atteUserid") Integer atteUserid);
    ZgmFriend selectIsFried(@Param("userid") Integer userid, @Param("atteUserid") Integer atteUserid);

}
