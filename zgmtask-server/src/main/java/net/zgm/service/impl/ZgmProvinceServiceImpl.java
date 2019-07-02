package net.zgm.service.impl;

import net.zgm.mapper.ZgmProvinceMapper;
import net.zgm.model.ZgmProvince;
import net.zgm.service.ZgmProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZgmProvinceServiceImpl implements ZgmProvinceService {
    @Autowired
    private ZgmProvinceMapper zgmProvinceMapper;

    @Override
    public List<ZgmProvince> selectAllZgmProvince() {
        return zgmProvinceMapper.selectAllZgmProvince();
    }
}
