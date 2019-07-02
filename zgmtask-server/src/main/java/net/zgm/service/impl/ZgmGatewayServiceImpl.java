package net.zgm.service.impl;

import net.zgm.mapper.ZgmAdminMapper;
import net.zgm.mapper.ZgmGatewayMapper;
import net.zgm.model.ZgmAdmin;
import net.zgm.model.ZgmGateway;
import net.zgm.service.ZgmGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZgmGatewayServiceImpl implements ZgmGatewayService {
    @Autowired
    private ZgmGatewayMapper zgmGatewayMapper;
    @Autowired
    private ZgmAdminMapper zgmAdminMapper;

    @Override
    public ZgmGateway selectZgmGatewayById(Integer gatewayId) {

        return zgmGatewayMapper.selectZgmGatewayById(gatewayId);
    }

    @Override
    public List<ZgmGateway> selectAllGatewayByTaskid(Integer collarTaskid) {
        List<ZgmGateway> list = zgmGatewayMapper.selectAllGatewayByTaskid(collarTaskid);
        for (int i = 0; i <list.size() ; i++) {
            String name = list.get(i).getGatewayName();
            int a =name.indexOf("（");
            int b =name.indexOf("）");
            String aname = name.substring(a+1,b);
            name =name.substring(0,a);
            list.get(i).setGatewayName(name);
            list.get(i).setGatewayPName(aname);
        }
        return list;
    }

    @Override
    public List<ZgmGateway> selectZgmGatewayBySid(Integer gatewayProvinceid,Integer taskcid) {
        List<ZgmGateway> list = zgmGatewayMapper.selectZgmGatewayBySid(gatewayProvinceid,taskcid);
        for (int i = 0; i <list.size() ; i++) {
            String name = list.get(i).getGatewayName();
            int a =name.indexOf("（");
            int b =name.indexOf("）");
            String aname = name.substring(a+1,b);
            name =name.substring(0,a);
            list.get(i).setGatewayName(name);
            list.get(i).setGatewayPName(aname);
        }
        return list;
    }

    @Override
    public ZgmAdmin selectLoginStatus(Integer userid) {
        return zgmAdminMapper.selectLoginStatus(userid);
    }
}
