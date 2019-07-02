package net.zgm.mapper;

import net.zgm.model.ZgmMsgVO;

import java.util.List;

/**
 * Created by ZYS on 2019/5/17
 */
public interface ZgmMsgVOMapper {
    List<ZgmMsgVO> queryAll(Integer userId);
}
