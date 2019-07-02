package net.zgm.controller;

import net.zgm.model.ResultMessage;
import net.zgm.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by ZYS on 2019/5/8
 */
@RestController
public class ViewController {
    @Autowired
    private ViewService viewService;

    @RequestMapping("selectZgmMsgapi")
    public ResultMessage selectZgmMsg(@RequestParam Integer userId) {
        return viewService.selectZgmMsg(userId);
    }

    @RequestMapping("selectZgmAdminByUidapi")
    public ResultMessage selectZgmAdminByUid(@RequestParam(value = "userId") Integer userId) {
        return viewService.selectZgmAdminByUid(userId);
    }

    @RequestMapping("updateZgmAddressapi")
    public ResultMessage updateZgmAddress(Integer addressId, Integer userId, String receiveArea, String receiveName, String receiveAddress, Integer defaultAddress, String telephone) {
        return viewService.updateZgmAddress(addressId, userId, receiveArea, receiveName, receiveAddress, defaultAddress, telephone);
    }

    @RequestMapping("selectZgmAddressByUidapi")
    public ResultMessage selectZgmAddressByUid(Integer userId) {
        return viewService.selectZgmAddressByUid(userId);
    }

    @RequestMapping("addZgmAddressapi")
    public ResultMessage addZgmAddress(Integer userId, String receiveArea, String receiveName, String receiveAddress, Integer defaultAddress, String telephone) {
        return viewService.addZgmAddress(userId, receiveArea, receiveName, receiveAddress, defaultAddress, telephone);
    }

    @RequestMapping("deleteZgmAddressapi")
    public ResultMessage deleteZgmAddress(Integer addressId) {
        return viewService.deleteZgmAddress(addressId);
    }

    @RequestMapping("selectIntegralDetailapi")
    public ResultMessage selectIntegralDetail(Integer userId, Integer pageNo) {
        return viewService.selectIntegralDetail(userId, pageNo);
    }

    @RequestMapping("clickzanNewsUpdateIntergralapi")
    public ResultMessage clickzanNewsUpdateIntergral(Integer userId,
                                                     Integer newsId,
                                                     Integer ifclick) {
        return viewService.clickzanNewsUpdateIntergral(userId, newsId, ifclick);
    }

    @RequestMapping("clickzanVideoUpdateIntergralapi")
    public ResultMessage clickzanVideoUpdateIntergral(Integer userId,
                                                      Integer videoId,
                                                      Integer ifclick) {
        return viewService.clickzanVideoUpdateIntergral(userId, videoId, ifclick);
    }

    @RequestMapping("addNewsTranspondapi")
    public ResultMessage addNewsTranspond(Integer userId, Integer newsId) {
        return viewService.addNewsTranspond(userId, newsId);
    }

    @RequestMapping("addVideoTranspondapi")
    public ResultMessage addVideoTranspond(Integer userId,
                                           Integer videoId
    ) {
        return viewService.addVideoTranspond(userId, videoId);
    }

    @RequestMapping("selectZgmQuestionByGidapi")
    public ResultMessage selectZgmQuestionByGid(Integer userId, Integer gatewayId) {
        return viewService.selectZgmQuestionByGid(userId, gatewayId);
    }

    @RequestMapping("selectAllZgmProvinceapi")
    public ResultMessage selectAllZgmProvince() {
        return viewService.selectAllZgmProvince();
    }

    @RequestMapping("selectZgmGatewayByPidapi")
    public ResultMessage selectZgmGatewayByPid(Integer provinceId) {
        return viewService.selectZgmGatewayByPid(provinceId);
    }

    @RequestMapping("addintegraByquestionsapi")
    public ResultMessage addintegraByquestions(Integer userId, Integer gatewayId, Integer num) {
        return viewService.addintegraByquestions(userId, gatewayId, num);
    }

    @RequestMapping("selectIfclickNewsapi")
    public ResultMessage selectIfclickNews(Integer userId, Integer newsId) {
        return viewService.selectIfclickNews(userId, newsId);
    }

    @RequestMapping("selectIfclickVideoapi")
    public ResultMessage selectIfclickVideo(Integer userId, Integer videoId) {
        return viewService.selectIfclickVideo(userId, videoId);
    }

    @RequestMapping("queryAllZgmTaskapi")
    public ResultMessage queryAllZgmTask(Integer taskId) {
        return viewService.queryAllZgmTask(taskId);
    }

    @RequestMapping("queryAllIntroduce")
    public ResultMessage queryAllIntroduce() {
        return viewService.queryAllIntroduce();
    }

}
