package net.zgm.service;

import net.zgm.model.ZgmProvince;

import java.util.List;

public interface ZgmProvinceService {
    /**
     * 查询所有省份
     * @return
     */
    List<ZgmProvince> selectAllZgmProvince();
}
