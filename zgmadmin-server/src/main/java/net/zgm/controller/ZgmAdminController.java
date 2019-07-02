package net.zgm.controller;

import com.github.pagehelper.PageInfo;
import net.zgm.model.*;
import net.zgm.service.ZgmAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ZYS on 2019/5/7
 */

@RestController
public class ZgmAdminController {
    @Autowired
    private ZgmAdminService zgmAdminService;

    /*消息通知*/
    @ResponseBody
    @RequestMapping(value = "/selectZgmMsg", method = RequestMethod.GET)
    public ResultMessage selectZgmMsg(Integer userId) {
        ResultMessage resultMessage = new ResultMessage();
        List<ZgmMsg> zgmMsgList = zgmAdminService.selectMsg(userId);
        resultMessage.setData(zgmMsgList);
        resultMessage.setStatus("200");
        resultMessage.setMsg("消息通知");
        return resultMessage;
    }


    /*根据用户id查询用户*/
    @ResponseBody
    @RequestMapping(value = "/selectZgmAdminByUid", method = RequestMethod.GET)
    public ResultMessage selectZgmAdminByUid(Integer userId) {
        System.out.println(userId);
        ResultMessage resultMessage = new ResultMessage();
        ZgmAdmin zgmAdmin = zgmAdminService.selectZgmAdminByUid(userId);
        resultMessage.setData(zgmAdmin);
        resultMessage.setStatus("200");
        resultMessage.setMsg("查询成功");
        return resultMessage;
    }

    /*修改收货地址*/
    @ResponseBody
    @RequestMapping(value = "/updateZgmAddress", method = RequestMethod.GET)
    public ResultMessage updateZgmAddress(Integer addressId, Integer userId, String receiveArea, String receiveName, String receiveAddress, Integer defaultAddress, String telephone) {
        if (defaultAddress == 1) {
            ZgmAddress zgmAddress = zgmAdminService.selectdefaultAddress(userId);
            if (zgmAddress != null) {
                zgmAdminService.updatedefaultAddress(zgmAddress.getAddressId());
            }
        }
        zgmAdminService.updateZgmAddress(addressId, receiveArea, receiveName, receiveAddress, defaultAddress, telephone);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setMsg("成功");
        resultMessage.setStatus("200");
        resultMessage.setData("success");
        return resultMessage;
    }

    /*根据用户id查询所有收货地址*/
    @ResponseBody
    @RequestMapping(value = "/selectZgmAddressByUid", method = RequestMethod.GET)
    public ResultMessage selectZgmAddressByUid(Integer userId) {
        List<ZgmAddress> zgmAddresses = zgmAdminService.selectZgmAddressByUid(userId);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setMsg("成功");
        resultMessage.setStatus("200");
        resultMessage.setData(zgmAddresses);
        return resultMessage;
    }

    /*新增收货地址*/
    @ResponseBody
    @RequestMapping(value = "/addZgmAddress", method = RequestMethod.GET)
    public ResultMessage addZgmAddress(Integer userId, String receiveArea, String receiveName, String receiveAddress, Integer defaultAddress, String telephone) {
        if (defaultAddress == 1) {
            ZgmAddress zgmAddress = zgmAdminService.selectdefaultAddress(userId);
            if (zgmAddress != null) {
                zgmAdminService.updatedefaultAddress(zgmAddress.getAddressId());
            }
        }
        zgmAdminService.addZgmAddress(userId, receiveArea, receiveName, receiveAddress, defaultAddress, telephone);

        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setMsg("成功");
        resultMessage.setStatus("200");
        resultMessage.setData("success");
        return resultMessage;
    }

    /*删除收货地址*/
    @ResponseBody
    @RequestMapping(value = "/deleteZgmAddress", method = RequestMethod.GET)
    public ResultMessage deleteZgmAddress(Integer addressId) {
        zgmAdminService.deleteZgmAddress(addressId);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setMsg("成功");
        resultMessage.setStatus("200");
        resultMessage.setData("success");
        return resultMessage;

    }

    /*积分明细*/
    @ResponseBody
    @RequestMapping(value = "/selectIntegralDetail", method = RequestMethod.GET)
    public ResultMessage selectIntegralDetail(Integer userId, Integer pageNo) {
        ResultMessage resultMessage = new ResultMessage();
        PageInfo<ZgmMsg> zgmMsgList = zgmAdminService.selectIntegralDetail(userId, pageNo);
        resultMessage.setData(zgmMsgList);
        resultMessage.setStatus("200");
        resultMessage.setMsg("积分明细");
        return resultMessage;
    }


    /*新闻点赞送积分，取消点赞扣除积分*/
    @ResponseBody
    @RequestMapping(value = "/clickzanNewsUpdateIntergral", method = RequestMethod.GET)
    public ResultMessage clickzanNewsUpdateIntergral(Integer userId,
                                                     Integer newsId,
                                                     Integer ifclick) {
        ResultMessage resultMessage = new ResultMessage();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date());
        List<ZgmClickzan> zgmClickzans = zgmAdminService.selectZgmClickzanByDate(userId, date, null);
        ZgmIntegral zgmIntegral = zgmAdminService.selectZgmIntegral();
        if (zgmClickzans.size() >= zgmIntegral.getClickMax()) {
            zgmAdminService.clickzanNews(userId, newsId);
            resultMessage.setMsg("今日点赞送积分已达到上限，不在赠送积分");
            resultMessage.setStatus("500");
            resultMessage.setData("success");

        } else {
            zgmAdminService.clickzanNewsUpdateIntergral(userId, newsId, ifclick);
            resultMessage.setMsg("新闻点赞成功");
            resultMessage.setStatus("200");
            resultMessage.setData("success");
        }

        return resultMessage;
    }


    /*视频点赞送积分，取消点赞扣除积分*/
    @ResponseBody
    @RequestMapping(value = "/clickzanVideoUpdateIntergral", method = RequestMethod.GET)
    public ResultMessage clickzanVideoUpdateIntergral(Integer userId,
                                                      Integer videoId,
                                                      Integer ifclick) {
        ResultMessage resultMessage = new ResultMessage();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String date = format.format(new Date());
        List<ZgmClickzan> zgmClickzans = zgmAdminService.selectZgmClickzanByDate(userId, null, date);
        ZgmIntegral zgmIntegral = zgmAdminService.selectZgmIntegral();

        if (zgmClickzans.size() >= zgmIntegral.getClickMax()) {
            zgmAdminService.clickzanVideo(userId, videoId);
            resultMessage.setMsg("今日点赞送积分已达到上限，不在赠送积分");
            resultMessage.setStatus("500");
            resultMessage.setData("success");
        } else {
            zgmAdminService.clickzanVideoUpdateIntergral(userId, videoId, ifclick);
            resultMessage.setMsg("视频点赞/取消点成功");
            resultMessage.setStatus("200");
            resultMessage.setData("success");
        }


        return resultMessage;
    }


    /*新闻转发送积分*/
    @ResponseBody
    @RequestMapping(value = "/addNewsTranspond", method = RequestMethod.GET)
    public ResultMessage addNewsTranspond(Integer userId,
                                          Integer newsId
    ) {

        ResultMessage resultMessage = new ResultMessage();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date());
        List<ZgmTranspond> zgmTransponds = zgmAdminService.selectZgmZgmTranspondByDate(userId, date, null);
        ZgmIntegral zgmIntegral = zgmAdminService.selectZgmIntegral();

        if (zgmTransponds.size() >= zgmIntegral.getTranspondMax()) {
            zgmAdminService.newsTranspond(userId, newsId);
            resultMessage.setMsg("今日转发已达上限");
            resultMessage.setStatus("500");
            resultMessage.setData("success");
        } else {
            zgmAdminService.addNewsTranspond(userId, newsId);
            resultMessage.setMsg("新闻转发成功");
            resultMessage.setStatus("200");
            resultMessage.setData("success");
        }

        return resultMessage;
    }


    /*视频转发送积分*/
    @ResponseBody
    @RequestMapping(value = "/addVideoTranspond", method = RequestMethod.GET)
    public ResultMessage addVideoTranspond(Integer userId,
                                           Integer videoId
    ) {
        ResultMessage resultMessage = new ResultMessage();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date());
        List<ZgmTranspond> zgmTransponds = zgmAdminService.selectZgmZgmTranspondByDate(userId, null, date);
        ZgmIntegral zgmIntegral = zgmAdminService.selectZgmIntegral();

        if (zgmTransponds.size() >= zgmIntegral.getTranspondMax()) {
            zgmAdminService.videoTranspond(userId, videoId);
            resultMessage.setMsg("今日转发已达上限");
            resultMessage.setStatus("500");
            resultMessage.setData("success");
        } else {
            zgmAdminService.addVideoTranspond(userId, videoId);
            resultMessage.setMsg("视频转发成功");
            resultMessage.setStatus("200");

            resultMessage.setData("success");
        }


        return resultMessage;
    }


    /*根据国门id生成题库*/
    @ResponseBody
    @RequestMapping(value = "/selectZgmQuestionByGid", method = RequestMethod.GET)
    public ResultMessage selectZgmQuestionByGid(Integer userId, Integer gatewayId) {

        List<ZgmQuestion> zgmQuestions = zgmAdminService.selectZgmQuestionByGid(gatewayId);
        ResultMessage resultMessage = new ResultMessage();
        ZgmIntegral zgmIntegral = zgmAdminService.selectZgmIntegral();
        ZgmAdmin zgmAdmin = zgmAdminService.selectZgmAdminByUid(userId);
        if (zgmAdmin.getWxOpenId() == null) {
            resultMessage.setStatus("100");
            resultMessage.setMsg("用户未绑定微信，不可参与答题活动");
            return resultMessage;
        }

        resultMessage.setStatus("200");
        resultMessage.setMsg("查询题库成功");
        resultMessage.setData(zgmQuestions);
        return resultMessage;
    }

    /*展示所有省份*/
    @ResponseBody
    @RequestMapping(value = "/selectAllZgmProvince", method = RequestMethod.GET)
    public ResultMessage selectAllZgmProvince() {
        List<ZgmProvince> list = zgmAdminService.selectAllZgmProvince();
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setMsg("查询所有省份成功");
        resultMessage.setStatus("200");
        resultMessage.setData(list);
        return resultMessage;
    }

    /*根据省份id显示对应国门*/
    @ResponseBody
    @RequestMapping(value = "/selectZgmGatewayByPid", method = RequestMethod.GET)
    public ResultMessage selectZgmGatewayByPid(Integer provinceId) {

        List<ZgmGateway> list = zgmAdminService.selectZgmGatewayByPid(provinceId);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setMsg("查询省份对应国门成功");
        resultMessage.setStatus("200");
        resultMessage.setData(list);
        return resultMessage;
    }


    /*答题送积分*/
    @ResponseBody
    @RequestMapping(value = "/addintegraByquestions", method = RequestMethod.GET)
    public ResultMessage addintegraByquestions(Integer userId, Integer gatewayId, Integer num) {
        ZgmAnswer zgmAnswer = zgmAdminService.selectZgmAnswerById(userId, gatewayId);
        ResultMessage resultMessage = new ResultMessage();
        ZgmIntegral zgmIntegral = zgmAdminService.selectZgmIntegral();
        if (zgmAnswer != null) {
            resultMessage.setMsg("此国门已答题送积分，不再赠送积分");
            resultMessage.setStatus("100");
        } else {
            zgmAdminService.addZgmAnswer(userId, gatewayId, num);
            zgmAdminService.addintegraByquestions(userId, num);
            resultMessage.setMsg("答题送积分成功");
            resultMessage.setStatus("200");
            resultMessage.setData((zgmIntegral.getAnswerIntegral()) * num);
        }
        return resultMessage;
    }

    /*查看新闻是否点赞*/
    @ResponseBody
    @RequestMapping(value = "/selectIfclickNews", method = RequestMethod.GET)
    public ResultMessage selectIfclickNews(Integer userId, Integer newsId) {
        ZgmClickzan zgmClickzan = zgmAdminService.selectIfclickNews(userId, newsId);
        ResultMessage resultMessage = new ResultMessage();

        if (null == zgmClickzan) {
            resultMessage.setData("0");
        } else {
            resultMessage.setData("1");
        }
        resultMessage.setMsg("success");
        resultMessage.setStatus("200");
        return resultMessage;
    }

    /*查看视频是否点赞*/
    @ResponseBody
    @RequestMapping(value = "/selectIfclickVideo", method = RequestMethod.GET)
    public ResultMessage selectIfclickVideo(Integer userId, Integer videoId) {
        ZgmClickzan zgmClickzan = zgmAdminService.selectIfclickVideo(userId, videoId);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setData(zgmClickzan);
        if (null == zgmClickzan) {
            resultMessage.setData("0");
        } else {
            resultMessage.setData("1");
        }
        resultMessage.setMsg("成功");
        resultMessage.setStatus("200");
        return resultMessage;
    }

    /*查询所有任务*/
    @ResponseBody
    @RequestMapping(value = "/queryAllZgmTask", method = RequestMethod.GET)
    public ResultMessage queryAllZgmTask(@RequestParam(required = false) Integer taskId) {
        List<ZgmTask> zgmTasks = zgmAdminService.queryAllZgmTask(taskId);
        List<ZgmTask> zgmTasks1 = new ArrayList<ZgmTask>();
        List<ZgmTask> zgmTasks2 = new ArrayList<ZgmTask>();
        List<ZgmTask> zgmTasks3 = new ArrayList<ZgmTask>();
        Map<String, List<ZgmTask>> map = new HashMap<String, List<ZgmTask>>();
        for (ZgmTask z :
                zgmTasks) {
            if (z.getTaskStatus().equals("0")) {
                zgmTasks1.add(z);
            }
            if (z.getTaskStatus().equals("1")) {
                zgmTasks2.add(z);
            }
            if (z.getTaskStatus().equals("2")) {
                zgmTasks3.add(z);
            }
        }
        map.put("0", zgmTasks1);
        map.put("1", zgmTasks2);
        map.put("2", zgmTasks3);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setData(map);
        resultMessage.setStatus("200");
        resultMessage.setMsg("查询成功");
        return resultMessage;
    }

    /* 更改app简介*/
    public void updateAppIntroduce(ZgmInformation information) {

        zgmAdminService.updateAppIntroduce(information);
    }

    /* 查询app简介*/
    @ResponseBody
    @RequestMapping(value = "/queryAllIntroduce", method = RequestMethod.GET)
    public ResultMessage selectAppIntroduce() {
        List<ZgmInformation> zgmInformations = zgmAdminService.selectAppIntroduce();
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setData(zgmInformations);
        resultMessage.setMsg("成功");
        resultMessage.setStatus("200");
        return resultMessage;

    }

}
