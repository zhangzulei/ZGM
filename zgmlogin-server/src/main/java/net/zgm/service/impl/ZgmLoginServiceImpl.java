package net.zgm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.zgm.mapper.*;
import net.zgm.model.*;
import net.zgm.service.ZgmLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ZYS on 2019/5/7
 */
@Transactional
@Service
public class ZgmLoginServiceImpl implements ZgmLoginService {

    @Autowired
    private ZgmAdminMapper zgmAdminMapper;

    @Autowired
    private ZgmGatewayMapper zgmGatewayMapper;
    @Autowired
    private ZgmVideoMapper zgmVideoMapper;
    @Autowired
    private ZgmNewsMapper zgmNewsMapper;
    @Autowired
    private ZgmMotorcadeMapper zgmMotorcadeMapper;

    @Override
    public ZgmAdmin selectZgmAdminByWX(String WXOpenid) {
        ZgmAdmin zgmAdmin = zgmAdminMapper.selectZgmAdminByWX(WXOpenid);
        return zgmAdmin;
    }

    @Override
    public void bandWX(String telephone, String userName, String wxOpenId, String wxPic) {
        zgmAdminMapper.bandWX(telephone, userName, wxOpenId, wxPic);
    }

    @Override
    public ZgmAdmin selectZgmAdminByPhone(String telephone) {
        ZgmAdmin zgmAdmin = zgmAdminMapper.selectZgmAdminByPhone(telephone);
        return zgmAdmin;
    }

    @Override
    public void addZgmAdmin(String userName, String wxOpenId, String wxPic, String telephone) {
        zgmAdminMapper.addZgmAdmin(userName, wxOpenId, wxPic, telephone);
    }

    @Override
    public void updateTelephone(String telephone, Integer userId) {
        zgmAdminMapper.updateTelephone(telephone, userId);
    }

    @Override
    public void addZgmAdminBytelephone(String telephone) {
        zgmAdminMapper.addZgmAdminBytelephone(telephone);
    }

    @Override
    public ZgmGateway selectZgmGatewayById(Integer gatewayId) {
        ZgmGateway zgmGateway = zgmGatewayMapper.selectZgmGatewayById(gatewayId);
        return zgmGateway;
    }

    @Override
    public ZgmAdmin selectZgmAdminByUid(Integer userId) {
        ZgmAdmin zgmAdmin = zgmAdminMapper.selectZgmAdminByUid(userId);
        return zgmAdmin;
    }

    @Override
    public PageInfo<ZgmGoods> selectZgmGoodsByGId(Integer pageNo, Integer count, Integer gatewayId) {
        PageHelper.startPage(pageNo, count);
        List<ZgmGoods> list = zgmGatewayMapper.selectZgmGoodsByGId(gatewayId);
        PageInfo<ZgmGoods> zgmGoodsPageInfo = new PageInfo<ZgmGoods>(list);
        return zgmGoodsPageInfo;
    }

    @Override
    public PageInfo<ZgmVideo> selectZgmVideoByGId(Integer pageNo, Integer count, Integer gatewayId) {
        PageHelper.startPage(pageNo, count);
        List<ZgmVideo> zgmVideos = zgmVideoMapper.selectZgmVideoByGId(gatewayId);
        PageInfo<ZgmVideo> zgmVideoPageInfo = new PageInfo<ZgmVideo>(zgmVideos);
        return zgmVideoPageInfo;
    }

    @Override
    public PageInfo<ZgmNews> selectZgmNewsByGId(Integer pageNo, Integer count, Integer gatewayId) {
        PageHelper.startPage(pageNo, count);
        List<ZgmNews> list = zgmNewsMapper.selectZgmNewsByGId(gatewayId);
        PageInfo<ZgmNews> zgmNewsPageInfo = new PageInfo<ZgmNews>(list);
        return zgmNewsPageInfo;
    }

    @Override
    public List<ZgmGateway> queryAllZgmGateway() {
        List<ZgmGateway> list = zgmGatewayMapper.queryAllZgmGateway();
        return list;
    }

    @Override
    public ZgmNews selectZgmNewsByNid(Integer newsId) {
        ZgmNews zgmNews = zgmNewsMapper.selectZgmNewsByNid(newsId);
        return zgmNews;
    }

    @Override
    public ZgmVideo selectZgmVideoByVid(Integer videoId) {
        ZgmVideo zgmVideo = zgmVideoMapper.selectZgmVideoByVid(videoId);
        return zgmVideo;
    }

    @Override
    public void updatecoordinate(Integer motorcadeId, String motorcadeLat, String motorcadeLon) {
        zgmMotorcadeMapper.updatecoordinate(motorcadeId, motorcadeLat, motorcadeLon);
    }

    @Override
    public ZgmMotorcade selectZgmMotorcade() {
        ZgmMotorcade zgmMotorcade = zgmMotorcadeMapper.selectZgmMotorcade();
        return zgmMotorcade;
    }

    @Override
    public void updateTelephoneByWx(String telephone, Integer userId) {
        zgmAdminMapper.updateTelephoneByWx(telephone, userId);

    }
}
