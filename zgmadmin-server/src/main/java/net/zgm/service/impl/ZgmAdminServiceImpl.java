package net.zgm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.zgm.mapper.*;
import net.zgm.model.*;
import net.zgm.service.ZgmAdminService;
import net.zgm.util.ComparatorDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ZYS on 2019/5/7
 */
@Service
@Transactional
public class ZgmAdminServiceImpl implements ZgmAdminService {

    @Resource
    private ZgmAnswerMapper zgmAnswerMapper;
    @Autowired
    private ZgmAdminMapper zgmAdminMapper;
    @Autowired
    private ZgmGoodsMapper zgmGoodsMapper;
    @Autowired
    private ZgmGatewayMapper zgmGatewayMapper;
    @Autowired
    private ZgmVideoMapper zgmVideoMapper;
    @Autowired
    private ZgmNewsMapper zgmNewsMapper;
    @Autowired
    private ZgmMotorcadeMapper zgmMotorcadeMapper;
    @Autowired
    private ZgmClickzanMapper zgmClickzanMapper;
    @Autowired
    private ZgmIntegralMapper zgmIntegralMapper;
    @Autowired
    private ZgmTranspondMapper zgmTranspondMapper;
    @Autowired
    private ZgmOrderMapper zgmOrderMapper;
    @Autowired
    private ZgmQuestionMapper zgmQuestionMapper;
    @Autowired
    private ZgmProvinceMapper zgmProvinceMapper;
    @Autowired
    private ZgmAddressMapper zgmAddressMapper;
    @Autowired
    private ZgmGoodsclassifyMapper zgmGoodsclassifyMapper;
    @Autowired
    private ZgmTaskMapper zgmTaskMapper;
    @Autowired
    private ZgmInformationMapper zgmInformationMapper;

    @Override
    public List<ZgmMsg> selectMsg(Integer userId) {
        {
            List<ZgmOrder> list = zgmOrderMapper.selectZgmOrderByUid(userId);
            List<ZgmAnswer> zgmAnswers = zgmAnswerMapper.selectZgmAnswerByUid(userId);
            List<ZgmClickzan> zgmClickzans = zgmClickzanMapper.selectZgmClickzanByUid(userId);
            List<ZgmTranspond> zgmTransponds = zgmTranspondMapper.selectZgmTranspondByUid(userId);
            List<ZgmMsg> zgmMsgList = new ArrayList<ZgmMsg>();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (list.size() != 0) {
                for (ZgmOrder li : list) {
                    ZgmGoods zgmGoods = zgmGoodsMapper.selectZgmGoodsByGid(li.getGoodsId());
                    ZgmMsg zgmMsg = new ZgmMsg();
                    zgmMsg.setIntegral(zgmGoods.getGoodsIntegral());
                    zgmMsg.setType("积分兑换商品");
                    zgmMsg.setAddorsubtract("积分减少");
                    zgmMsg.setDate(format.format(li.getCreateTime()));
                    zgmMsgList.add(zgmMsg);

                    if (li.getReceivingTime() != null) {
                        ZgmMsg zgmMsg1 = new ZgmMsg();
                        zgmMsg1.setType("订单" + li.getOrderNo() + "已发货");
                        zgmMsg1.setDate(format.format(li.getReceivingTime()));
                        zgmMsgList.add(zgmMsg1);
                    }

                    if (li.getShipmentsTime() != null) {
                        ZgmMsg zgmMsg2 = new ZgmMsg();
                        zgmMsg2.setType("订单" + li.getOrderNo() + "已收货");
                        zgmMsg2.setDate(format.format(li.getShipmentsTime()));
                        zgmMsgList.add(zgmMsg2);
                    }


                }
            }
            if (zgmAnswers.size() != 0) {
                for (ZgmAnswer li : zgmAnswers) {
                    if (li.getIntegral() != null) {
                        ZgmMsg zgmMsg = new ZgmMsg();
                        zgmMsg.setIntegral(li.getIntegral());
                        zgmMsg.setType("答题");
                        zgmMsg.setAddorsubtract("积分增加");
                        zgmMsg.setDate(format.format(li.getCreateTime()));
                        zgmMsgList.add(zgmMsg);
                    }

                }
            }
            if (zgmClickzans.size() != 0) {
                for (ZgmClickzan li : zgmClickzans) {
                    if (li.getClicknewsIntegral() != null) {

                        ZgmMsg zgmMsg = new ZgmMsg();
                        zgmMsg.setIntegral(li.getClicknewsIntegral());
                        zgmMsg.setType("新闻点赞");
                        zgmMsg.setAddorsubtract("积分增加");
                        zgmMsg.setDate(format.format(li.getClicknewsTime()));
                        zgmMsgList.add(zgmMsg);

                    }
                    ;
                    if (li.getClickvideoIntegral() != null) {
                        ZgmMsg zgmMsg1 = new ZgmMsg();
                        zgmMsg1.setIntegral(li.getClickvideoIntegral());
                        zgmMsg1.setType("视频点赞");
                        zgmMsg1.setAddorsubtract("积分增加");
                        zgmMsg1.setDate(format.format(li.getClickvideoTime()));
                        zgmMsgList.add(zgmMsg1);
                    }

                }
            }
            if (zgmTransponds.size() != 0) {
                for (ZgmTranspond li : zgmTransponds) {
                    if (li.getTranspondnewsIntegral() != null) {
                        ZgmMsg zgmMsg = new ZgmMsg();
                        zgmMsg.setIntegral(li.getTranspondnewsIntegral());
                        zgmMsg.setType("新闻转发");
                        zgmMsg.setAddorsubtract("积分增加");
                        zgmMsg.setDate(format.format(li.getTranspondnewsTime()));
                        zgmMsgList.add(zgmMsg);

                    }
                    if (li.getTranspondvideoIntegral() != null) {
                        ZgmMsg zgmMsg1 = new ZgmMsg();
                        zgmMsg1.setIntegral(li.getTranspondvideoIntegral());
                        zgmMsg1.setType("视频转发");
                        zgmMsg1.setAddorsubtract("积分增加");
                        zgmMsg1.setDate(format.format(li.getTranspondvideoTime()));
                        zgmMsgList.add(zgmMsg1);
                    }


                }
            }
            if (zgmMsgList.size() != 0) {
                ComparatorDate c = new ComparatorDate();
                Collections.sort(zgmMsgList, c);
            }



            return zgmMsgList;
        }
    }

    @Override
    public ZgmAdmin selectZgmAdminByUid(Integer userId) {

        ZgmAdmin zgmAdmin = zgmAdminMapper.selectZgmAdminByUid(userId);
        return zgmAdmin;
    }

    @Override
    public ZgmAddress selectdefaultAddress(Integer userId) {
        ZgmAddress zgmAddress = zgmAddressMapper.selectdefaultAddress(userId);
        return zgmAddress;
    }

    @Override
    public void updatedefaultAddress(Integer addressId) {
        zgmAddressMapper.updatedefaultAddress(addressId);
    }

    @Override
    public void updateZgmAddress(Integer addressId, String receiveArea, String receiveName, String receiveAddress, Integer defaultAddress, String telephone) {
        zgmAddressMapper.updateZgmAddress(addressId, receiveArea, receiveName, receiveAddress, defaultAddress, telephone);
    }

    @Override
    public List<ZgmAddress> selectZgmAddressByUid(Integer userId) {
        List<ZgmAddress> zgmAddresses = zgmAddressMapper.selectZgmAddressByUid(userId);
        return zgmAddresses;
    }

    @Override
    public void deleteZgmAddress(Integer addressId) {
        zgmAddressMapper.deleteZgmAddress(addressId);
    }

    @Override
    public void addZgmAddress(Integer userId, String receiveArea, String receiveName, String receiveAddress, Integer defaultAddress, String telephone) {
        zgmAddressMapper.addZgmAddress(userId, receiveArea, receiveName, receiveAddress, defaultAddress, telephone);
    }

    @Override
    public PageInfo<ZgmMsg> selectIntegralDetail(Integer userId, Integer pageNo) {
        List<ZgmOrder> list = zgmOrderMapper.selectZgmOrderByUid(userId);
        List<ZgmAnswer> zgmAnswers = zgmAnswerMapper.selectZgmAnswerByUid(userId);
        List<ZgmClickzan> zgmClickzans = zgmClickzanMapper.selectZgmClickzanByUid(userId);
        List<ZgmTranspond> zgmTransponds = zgmTranspondMapper.selectZgmTranspondByUid(userId);
        List<ZgmMsg> zgmMsgList = new ArrayList<ZgmMsg>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (list.size() != 0) {
            for (ZgmOrder li : list) {
                ZgmGoods zgmGoods = zgmGoodsMapper.selectZgmGoodsByGid(li.getGoodsId());
                ZgmMsg zgmMsg = new ZgmMsg();
                zgmMsg.setIntegral(zgmGoods.getGoodsIntegral());
                zgmMsg.setType("积分兑换商品");
                zgmMsg.setAddorsubtract("积分减少");
                zgmMsg.setDate(format.format(li.getCreateTime()));
                zgmMsgList.add(zgmMsg);
            }
        }
        if (zgmAnswers.size() != 0) {
            for (ZgmAnswer li : zgmAnswers) {
                if (li.getIntegral() != null) {
                    ZgmMsg zgmMsg = new ZgmMsg();

                    zgmMsg.setIntegral(li.getIntegral());
                    zgmMsg.setType("答题");
                    zgmMsg.setAddorsubtract("积分增加");
                    zgmMsg.setDate(format.format(li.getCreateTime()));
                    zgmMsgList.add(zgmMsg);
                }

            }
        }
        if (zgmClickzans.size() != 0) {
            for (ZgmClickzan li : zgmClickzans) {
                if (li.getClicknewsIntegral() != null) {
                    ZgmMsg zgmMsg = new ZgmMsg();
                    zgmMsg.setIntegral(li.getClicknewsIntegral());
                    zgmMsg.setType("新闻点赞");
                    zgmMsg.setAddorsubtract("积分增加");
                    zgmMsg.setDate(format.format(li.getClicknewsTime()));
                    zgmMsgList.add(zgmMsg);

                }
                ;
                if (li.getClickvideoIntegral() != null) {
                    ZgmMsg zgmMsg1 = new ZgmMsg();
                    zgmMsg1.setIntegral(li.getClickvideoIntegral());
                    zgmMsg1.setType("视频点赞");
                    zgmMsg1.setAddorsubtract("积分增加");
                    zgmMsg1.setDate(format.format(li.getClickvideoTime()));
                    zgmMsgList.add(zgmMsg1);
                }
            }
        }
        if (zgmTransponds.size() != 0) {
            for (ZgmTranspond li : zgmTransponds) {
                if (li.getTranspondnewsIntegral() != null) {
                    ZgmMsg zgmMsg = new ZgmMsg();
                    zgmMsg.setIntegral(li.getTranspondnewsIntegral());
                    zgmMsg.setType("新闻转发");
                    zgmMsg.setAddorsubtract("积分增加");
                    zgmMsg.setDate(format.format(li.getTranspondnewsTime()));
                    zgmMsgList.add(zgmMsg);

                }
                if (li.getTranspondvideoIntegral() != null) {
                    ZgmMsg zgmMsg1 = new ZgmMsg();
                    zgmMsg1.setIntegral(li.getTranspondvideoIntegral());
                    zgmMsg1.setType("视频转发");
                    zgmMsg1.setAddorsubtract("积分增加");
                    zgmMsg1.setDate(format.format(li.getTranspondvideoTime()));
                    zgmMsgList.add(zgmMsg1);
                }


            }
        }
        if (zgmMsgList.size() > 0) {
            ComparatorDate c = new ComparatorDate();
            Collections.sort(zgmMsgList, c);
        }

        PageInfo<ZgmMsg> zgmGoodsPageInfo = new PageInfo<ZgmMsg>(zgmMsgList);
        return zgmGoodsPageInfo;
    }

    @Override
    public void clickzanNewsUpdateIntergral(Integer userId, Integer newsId, Integer ifclick) {
        ZgmIntegral zgmIntegral = zgmIntegralMapper.selectZgmIntegral();
        if (ifclick == 1) {
            zgmClickzanMapper.addNewsClickzan(userId, newsId, zgmIntegral.getClickIntegral() + "");
            zgmNewsMapper.updateNewsclickzanNum(newsId, 1);
            zgmAdminMapper.updateIntergral(userId, zgmIntegral.getClickIntegral());
        } else {
            zgmClickzanMapper.deleteNewsClickzan(userId, newsId);
            zgmNewsMapper.updateNewsclickzanNum(newsId, -1);
            zgmAdminMapper.updateIntergral(userId, -zgmIntegral.getClickIntegral());
        }

    }

    @Override
    public void clickzanVideoUpdateIntergral(Integer userId, Integer videoId, Integer ifclick) {
        ZgmIntegral zgmIntegral = zgmIntegralMapper.selectZgmIntegral();
        if (ifclick == 1) {
            zgmClickzanMapper.addVideoClickzan(userId, videoId, zgmIntegral.getClickIntegral() + "");
            zgmVideoMapper.updateVideoclickzanNum(videoId, 1);
            zgmAdminMapper.updateIntergral(userId, zgmIntegral.getClickIntegral());

        } else {
            zgmClickzanMapper.deleteVideoClickzan(userId, videoId);
            zgmVideoMapper.updateVideoclickzanNum(videoId, -1);
            zgmAdminMapper.updateIntergral(userId, -zgmIntegral.getClickIntegral());

        }

    }

    @Override
    public void clickzanNews(Integer userId, Integer newsId) {
        ZgmIntegral zgmIntegral = zgmIntegralMapper.selectZgmIntegral();
        zgmClickzanMapper.addNewsClickzan(userId, newsId, null);
        zgmNewsMapper.updateNewsclickzanNum(newsId, 1);
    }

    @Override
    public void clickzanVideo(Integer userId, Integer videoId) {
        ZgmIntegral zgmIntegral = zgmIntegralMapper.selectZgmIntegral();
        zgmClickzanMapper.addVideoClickzan(userId, videoId, null);
        zgmVideoMapper.updateVideoclickzanNum(videoId, 1);
    }

    @Override
    public void addNewsTranspond(Integer userId, Integer newsId) {
        ZgmIntegral zgmIntegral = zgmIntegralMapper.selectZgmIntegral();
        ZgmTranspond zgmTranspond = zgmTranspondMapper.selectNewsTranspondById(userId, newsId);
        if (null == zgmTranspond) {
            zgmTranspondMapper.addNewsTranspond(userId, newsId, zgmIntegral.getTranspondIntegral() + "");
            zgmAdminMapper.updateIntergral(userId, zgmIntegral.getTranspondIntegral());
        }


    }

    @Override
    public void addVideoTranspond(Integer userId, Integer videoId) {
        ZgmTranspond zgmTranspond = zgmTranspondMapper.selectVideoTranspondById(userId, videoId);
        ZgmIntegral zgmIntegral = zgmIntegralMapper.selectZgmIntegral();

        if (null == zgmTranspond) {
            zgmTranspondMapper.addVideoTranspond(userId, videoId, zgmIntegral.getTranspondIntegral() + "");
            zgmAdminMapper.updateIntergral(userId, zgmIntegral.getTranspondIntegral());
        }

    }

    @Override
    public void newsTranspond(Integer userId, Integer newsId) {
        ZgmTranspond zgmTranspond = zgmTranspondMapper.selectNewsTranspondById(userId, newsId);

        if (null == zgmTranspond) {
            zgmTranspondMapper.addNewsTranspond(userId, newsId, null);
        }
    }

    @Override
    public void videoTranspond(Integer userId, Integer videoId) {
        ZgmTranspond zgmTranspond = zgmTranspondMapper.selectVideoTranspondById(userId, videoId);
        if (null == zgmTranspond) {
            zgmTranspondMapper.addVideoTranspond(userId, videoId, null);
        }
    }

    @Override
    public List<ZgmQuestion> selectZgmQuestionByGid(Integer gatewayId) {
        List<ZgmQuestion> zgmQuestions = zgmQuestionMapper.selectZgmQuestionByGid(gatewayId);
        return zgmQuestions;
    }

    @Override
    public List<ZgmProvince> selectAllZgmProvince() {
        List<ZgmProvince> list = zgmProvinceMapper.selectAllZgmProvince();
        return list;
    }

    @Override
    public List<ZgmGateway> selectZgmGatewayByPid(Integer provinceId) {
        List<ZgmGateway> list = zgmGatewayMapper.selectZgmGatewayByPid(provinceId);
        return list;
    }

    @Override
    public void addintegraByquestions(Integer userId, Integer num) {
        ZgmIntegral zgmIntegral = zgmIntegralMapper.selectZgmIntegral();
        zgmAdminMapper.updateIntergral(userId, zgmIntegral.getAnswerIntegral() * num);
    }

    @Override
    public ZgmClickzan selectIfclickNews(Integer userId, Integer newsId) {
        ZgmClickzan zgmClickzan = zgmClickzanMapper.selectIfclickNews(userId, newsId);

        return zgmClickzan;
    }

    @Override
    public ZgmClickzan selectIfclickVideo(Integer userId, Integer videoId) {
        ZgmClickzan zgmClickzan = zgmClickzanMapper.selectIfclickVideo(userId, videoId);
        return zgmClickzan;
    }

    @Override
    public ZgmAnswer selectZgmAnswerById(Integer userId, Integer gatewayId) {
        ZgmAnswer zgmAnswer = zgmAnswerMapper.selectZgmAnswerById(userId, gatewayId);
        return zgmAnswer;
    }

    @Override
    public void addZgmAnswer(Integer userId, Integer gatewayId, Integer num) {
        ZgmIntegral zgmIntegral = zgmIntegralMapper.selectZgmIntegral();
        zgmAnswerMapper.addZgmAnswer(userId, gatewayId, zgmIntegral.getAnswerIntegral() * num);
    }

    @Override
    public ZgmIntegral selectZgmIntegral() {
        ZgmIntegral zgmIntegral = zgmIntegralMapper.selectZgmIntegral();

        return zgmIntegral;
    }

    @Override
    public List<ZgmTask> queryAllZgmTask(Integer taskId) {
        List<ZgmTask> zgmTasks = zgmTaskMapper.queryAllZgmTask(taskId);
        return zgmTasks;
    }

    @Override
    public List<ZgmClickzan> selectZgmClickzanByDate(Integer userId, String clickNewsTime, String clickVideoTime) {
        List<ZgmClickzan> zgmClickzans = zgmClickzanMapper.selectZgmClickzanByDate(userId, clickNewsTime, clickVideoTime);
        return zgmClickzans;
    }

    @Override
    public List<ZgmTranspond> selectZgmZgmTranspondByDate(Integer userId, String newsdate, String videodate) {
        List<ZgmTranspond> zgmTransponds = zgmTranspondMapper.selectZgmTranspondByDate(userId, newsdate, videodate);
        return zgmTransponds;
    }

    @Override
    public void updateTelephoneByWx(String telephone, Integer userId) {
        zgmAdminMapper.updateTelephoneByWx(telephone, userId);
    }

    @Override
    public void updateAppIntroduce(ZgmInformation information) {
        zgmInformationMapper.updateAppIntroduce(information);
    }

    @Override
    public List<ZgmInformation> selectAppIntroduce() {

        return zgmInformationMapper.selectAppIntroduce();
    }

}
