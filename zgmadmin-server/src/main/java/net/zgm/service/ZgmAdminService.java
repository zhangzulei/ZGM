package net.zgm.service;

import com.github.pagehelper.PageInfo;
import net.zgm.model.*;

import java.util.List;

/**
 * Created by ZYS on 2019/5/7
 */
public interface ZgmAdminService {

    /*消息通知*/
    List<ZgmMsg> selectMsg(Integer userId);

    /*根据用户id查询用户*/
    ZgmAdmin selectZgmAdminByUid(Integer userId);


    /*根据用户id查询此用户是否有默认地址*/
    ZgmAddress selectdefaultAddress(Integer userId);

    /*修改默认地址*/
    void updatedefaultAddress(Integer addressId);

    /*修改收货地址*/
    void updateZgmAddress(Integer addressId, String receiveArea, String receiveName, String receiveAddress, Integer defaultAddress, String telephone);

    /*根据用户id查询所有收货地址*/
    List<ZgmAddress> selectZgmAddressByUid(Integer userId);

    /*删除收货地址*/
    void deleteZgmAddress(Integer addressId);

    /*新增收货地址*/
    void addZgmAddress(Integer userId, String receiveArea, String receiveName, String receiveAddress, Integer defaultAddress, String telephone);

    /*积分明细*/
    PageInfo<ZgmMsg> selectIntegralDetail(Integer userId, Integer pageNo);

    /*新闻点赞送积分,取消点赞扣除积分*/
    void clickzanNewsUpdateIntergral(Integer userId, Integer newsId, Integer ifclick);

    /*视频点赞送积分,取消点赞扣除积分*/
    void clickzanVideoUpdateIntergral(Integer userId, Integer videoId, Integer ifclick);

    /*新闻点赞*/
    void clickzanNews(Integer userId, Integer newsId);

    /*视频点赞*/
    void clickzanVideo(Integer userId, Integer videoId);


    /*新闻转发送积分*/
    void addNewsTranspond(Integer userId, Integer newsId);

    /*视频转发送积分*/
    void addVideoTranspond(Integer userId, Integer videoId);

    /*新闻转发*/
    void newsTranspond(Integer userId, Integer newsId);

    /*视频转发*/
    void videoTranspond(Integer userId, Integer videoId);

    /*根据省份id查询对应题库*/
    List<ZgmQuestion> selectZgmQuestionByGid(Integer gatewayId);

    /*展示所有省份*/
    List<ZgmProvince> selectAllZgmProvince();

    /*根据省份id展示对应国门*/
    List<ZgmGateway> selectZgmGatewayByPid(Integer provinceId);

    /*答题送积分*/
    void addintegraByquestions(Integer userId, Integer num);

    /*根据用户id和newsid查询是否点赞*/
    ZgmClickzan selectIfclickNews(Integer userId, Integer newsId);

    /*根据用户id和videoid查询是否点赞*/
    ZgmClickzan selectIfclickVideo(Integer userId, Integer videoId);

    /*根据国门id和用户id查询此用户是否答题*/
    ZgmAnswer selectZgmAnswerById(Integer userId, Integer gatewayId);

    /*新增答题送积分记录*/
    void addZgmAnswer(Integer userId, Integer gatewayId, Integer num);

    /*查询点赞转发等对应积分*/
    ZgmIntegral selectZgmIntegral();

    /*查询所有任务*/
    List<ZgmTask> queryAllZgmTask(Integer taskId);

    /*查询当日点赞是否达到上限*/
    List<ZgmClickzan> selectZgmClickzanByDate(Integer userId, String clickNewsTime, String clickVideoTime);


    /*查询当日转发是否达到上限*/
    List<ZgmTranspond> selectZgmZgmTranspondByDate(Integer userId, String newsdate, String videodate);

    /*当微信绑定，修改手机号*/
    void updateTelephoneByWx(String telephone, Integer userId);
    /* 根据app信息id 修改app信息简介*/
    public void updateAppIntroduce(ZgmInformation information);
    /* 根据app信息id 修改app信息简介*/
    public List<ZgmInformation> selectAppIntroduce();

}
