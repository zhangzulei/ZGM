package net.zgm.mapper;

import net.zgm.model.ZgmProvince;

import java.util.List;

/**
 * Created by ZYS on 2019/4/25
 */
public interface ZgmProvinceMapper {
    /**
     * 查询所有省份
     * @return
     */
    List<ZgmProvince> selectAllZgmProvince();
}
