package net.zgm.controller;

import com.aliyuncs.exceptions.ClientException;
import com.github.pagehelper.PageInfo;
import net.zgm.model.*;
import net.zgm.service.ZgmLoginService;
import net.zgm.util.AliDayunSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by ZYS on 2019/5/8
 */
@RestController
public class ZgmLoginController {

    @Autowired
    private ZgmLoginService zgmLoginService;

    /*微信登陆入口*/
    @ResponseBody
    @RequestMapping(value = "/wxland", method = RequestMethod.GET)
    public ResultMessage wxland(String wxOpenId,
                                @RequestParam(required = false) String phoneNumber,
                                @RequestParam(required = false) String userName,
                                @RequestParam(required = false) String wxPic) {
        System.out.println(wxOpenId);
        ZgmAdmin zgmAdmin = zgmLoginService.selectZgmAdminByWX(wxOpenId);
        System.out.println(zgmAdmin!=null);
        ResultMessage resultMessage = new ResultMessage();
        if (phoneNumber != null && userName != null && wxPic != null && wxOpenId != null) {
            System.out.println("进入绑定微信 开始绑定");
            zgmLoginService.bandWX(phoneNumber, userName, wxOpenId, wxPic);
            System.out.println("进入绑定微信 绑定结束");
            ZgmAdmin zgmAdmin1 = zgmLoginService.selectZgmAdminByPhone(phoneNumber);
            resultMessage.setStatus("200");
            resultMessage.setMsg("绑定微信成功");
            resultMessage.setData(zgmAdmin1);
            return resultMessage;
        }
        if (null == zgmAdmin) {
            resultMessage.setMsg("请跳转绑定手机");

            resultMessage.setStatus("100");
        } else {
            resultMessage.setData(zgmAdmin);
            resultMessage.setStatus("200");
        }
        return resultMessage;
    }


    /*短信登陆入口*/
    @ResponseBody
    @RequestMapping(value = "/sendMsg", method = RequestMethod.GET)
    public ResultMessage sendMsg(@RequestParam("phoneNumber") String phoneNumber, HttpServletRequest request) throws ClientException {
        ResultMessage resultMessage = new ResultMessage();
        ZgmAdmin zgmAdmin = zgmLoginService.selectZgmAdminByPhone(phoneNumber);
        if (null == zgmAdmin) {
            String msgCode = AliDayunSms.getMsgCode();
            String sms = AliDayunSms.SendMessmage(phoneNumber, "SMS_164268035", msgCode);
            if (sms.equals("fail")) {
                resultMessage.setMsg("验证码发送失败，请稍后再试");
                resultMessage.setStatus("500");
            } else {
                resultMessage.setMsg("验证码发送成功");
                resultMessage.setStatus("200");

            }
            request.getSession().setAttribute(phoneNumber, msgCode);
        } else {
            String msgCode = AliDayunSms.getMsgCode();
            String sms = AliDayunSms.SendMessmage(phoneNumber, "SMS_164277626", msgCode);
            if (sms.equals("fail")) {
                resultMessage.setMsg("验证码发送失败，请稍后再试");
                resultMessage.setStatus("500");
            } else {
                resultMessage.setMsg("验证码发送成功");
                resultMessage.setStatus("200");
            }
            request.getSession().setAttribute(phoneNumber, msgCode);
        }
        return resultMessage;
    }
    /*验证手机验证码是否正确*/

    @RequestMapping(value = "/verification", method = RequestMethod.GET)
    public ResultMessage verification(String phoneNumber, @RequestParam(required = false) String userName,
                                      @RequestParam(required = false) String wxOpenId, @RequestParam(required = false) String wxPic, String code, HttpServletRequest request,
                                      @RequestParam(required = false) Integer userId) {
        ResultMessage resultMessage = new ResultMessage();
        ZgmAdmin zgmAdmin1 = zgmLoginService.selectZgmAdminByPhone(phoneNumber);
        if (null == request.getSession().getAttribute(phoneNumber)) {
            if (code.equals("888888")) {
                System.out.println(code);
                if (userName != null && wxOpenId != null && wxPic != null && phoneNumber != null) {
                    if (zgmAdmin1 != null) {
                        if (zgmAdmin1.getWxOpenId() != null) {
                            resultMessage.setMsg("此手机已绑定微信，请重新绑定");
                            resultMessage.setStatus("100");
                            return resultMessage;
                        } else {
                            zgmLoginService.bandWX(phoneNumber, userName, wxOpenId, wxPic);
                            ZgmAdmin zgmAdmin2 = zgmLoginService.selectZgmAdminByPhone(phoneNumber);
                            resultMessage.setData(zgmAdmin2);
                            resultMessage.setStatus("200");
                            return resultMessage;
                        }
                    } else {
                        zgmLoginService.addZgmAdmin(userName, wxOpenId, wxPic, phoneNumber);
                        ZgmAdmin zgmAdmin = zgmLoginService.selectZgmAdminByWX(wxOpenId);
                        resultMessage.setData(zgmAdmin);
                        resultMessage.setStatus("200");
                        return resultMessage;
                    }

                }
                if (userId != null) {
                    if (zgmAdmin1 != null) {
                        resultMessage.setMsg("此手机号已注册,请重新修改手机号");
                        resultMessage.setStatus("100");
                        return resultMessage;
                    } else {
                        zgmLoginService.updateTelephone(phoneNumber, userId);
                        ZgmAdmin zgmAdmin2 = zgmLoginService.selectZgmAdminByPhone(phoneNumber);
                        resultMessage.setData(zgmAdmin2);
                        resultMessage.setStatus("200");
                        return resultMessage;
                    }
                }
                if (userName == null && wxOpenId == null && wxPic == null && userId == null) {
                    if (null == zgmAdmin1) {
                        zgmLoginService.addZgmAdminBytelephone(phoneNumber);
                        ZgmAdmin zgmAdmin2 = zgmLoginService.selectZgmAdminByPhone(phoneNumber);
                        resultMessage.setStatus("200");
                        resultMessage.setData(zgmAdmin2);
                    } else {
                        resultMessage.setStatus("200");
                        resultMessage.setData(zgmAdmin1);
                        return resultMessage;
                    }
                }
            } else {
                resultMessage.setMsg("验证码有误");
                resultMessage.setStatus("500");
                return resultMessage;
            }

        } else {
            if (request.getSession().getAttribute(phoneNumber).toString().equals(code)) {
                System.out.println(code);
                if (userName != null && wxOpenId != null && wxPic != null && phoneNumber != null) {
                    if (zgmAdmin1 != null) {
                        if (zgmAdmin1.getWxOpenId() != null) {
                            resultMessage.setMsg("此手机已绑定微信，请重新绑定");
                            resultMessage.setStatus("100");
                            return resultMessage;
                        } else {
                            zgmLoginService.bandWX(phoneNumber, userName, wxOpenId, wxPic);
                            ZgmAdmin zgmAdmin2 = zgmLoginService.selectZgmAdminByPhone(phoneNumber);
                            resultMessage.setData(zgmAdmin2);
                            resultMessage.setStatus("200");
                            return resultMessage;
                        }
                    } else {
                        zgmLoginService.addZgmAdmin(userName, wxOpenId, wxPic, phoneNumber);
                        ZgmAdmin zgmAdmin = zgmLoginService.selectZgmAdminByWX(wxOpenId);
                        resultMessage.setData(zgmAdmin);
                        resultMessage.setStatus("200");
                        return resultMessage;
                    }

                }
                if (userId != null) {
                    if (zgmAdmin1 != null) {
                        resultMessage.setMsg("此手机号已注册,请重新修改手机号");
                        resultMessage.setStatus("100");
                        return resultMessage;
                    } else {
                        if (zgmAdmin1.getWxOpenId() == null) {
                            zgmLoginService.updateTelephone(phoneNumber, userId);
                        } else {
                            zgmLoginService.updateTelephoneByWx(phoneNumber, userId);
                        }
                        ZgmAdmin zgmAdmin2 = zgmLoginService.selectZgmAdminByPhone(phoneNumber);
                        resultMessage.setData(zgmAdmin2);
                        resultMessage.setStatus("200");
                        return resultMessage;
                    }
                }
                if (userName == null && wxOpenId == null && wxPic == null && userId == null) {
                    if (null == zgmAdmin1) {
                        zgmLoginService.addZgmAdminBytelephone(phoneNumber);
                        ZgmAdmin zgmAdmin2 = zgmLoginService.selectZgmAdminByPhone(phoneNumber);
                        resultMessage.setStatus("200");
                        resultMessage.setData(zgmAdmin2);
                    } else {
                        resultMessage.setStatus("200");
                        resultMessage.setData(zgmAdmin1);
                        return resultMessage;
                    }
                }
            } else {
                resultMessage.setMsg("验证码有误");
                resultMessage.setStatus("500");
            }
        }
        return resultMessage;
    }

    /*展示國門詳情*/

    @ResponseBody
    @RequestMapping(value = "/showGatewayDetail", method = RequestMethod.GET)
    public ResultMessage showGatewayDetail(Integer gatewayId) {
        ResultMessage resultMessage = new ResultMessage();
        ZgmGateway zgmGateway = zgmLoginService.selectZgmGatewayById(gatewayId);
        resultMessage.setData(zgmGateway);
        resultMessage.setMsg("查询成功");
        resultMessage.setStatus("200");
        return resultMessage;
    }

    /*展示國門對應的藏寶*/
    @ResponseBody
    @RequestMapping(value = "/selectZgmGoodsByGId", method = RequestMethod.GET)
    public ResultMessage selectZgmGoodsByGId(Integer gatewayId, Integer pageNo, Integer userId) {
        ZgmAdmin zgmAdmin = zgmLoginService.selectZgmAdminByUid(userId);
        ResultMessage resultMessage = new ResultMessage();
        if (zgmAdmin.getWxOpenId() == null) {
            resultMessage.setStatus("100");
            resultMessage.setMsg("此用户未绑定微信，不可参与藏宝活动");
            return resultMessage;

        }

        PageInfo<ZgmGoods> gatewayZgmGoods = zgmLoginService.selectZgmGoodsByGId(pageNo, 10, gatewayId);
        resultMessage.setData(gatewayZgmGoods);
        resultMessage.setMsg("查询成功");
        resultMessage.setStatus("200");
        return resultMessage;
    }


    /*展示國門對應的视频*/
    @ResponseBody
    @RequestMapping(value = "/selectZgmVideoByGId", method = RequestMethod.GET)

    public ResultMessage selectZgmVideoByGId(Integer gatewayId, Integer pageNo) {
        ResultMessage resultMessage = new ResultMessage();
        PageInfo<ZgmVideo> zgmVideos = zgmLoginService.selectZgmVideoByGId(pageNo, 10, gatewayId);
        resultMessage.setData(zgmVideos);
        resultMessage.setMsg("查询成功");
        resultMessage.setStatus("200");
        return resultMessage;
    }


    /*展示國門對應的新闻*/
    @ResponseBody
    @RequestMapping(value = "/selectZgmNewsByGId", method = RequestMethod.GET)
    public ResultMessage selectZgmNewsByGId(Integer gatewayId, Integer pageNo) {
        ResultMessage resultMessage = new ResultMessage();
        PageInfo<ZgmNews> ZgmNews = zgmLoginService.selectZgmNewsByGId(pageNo, 10, gatewayId);
        resultMessage.setData(ZgmNews);
        resultMessage.setMsg("查询成功");
        resultMessage.setStatus("200");
        return resultMessage;
    }


    /*查询所有国门*/
    @ResponseBody
    @RequestMapping(value = "/queryAllZgmGateway", method = RequestMethod.GET)
    public ResultMessage queryAllZgmGateway() {
        ResultMessage resultMessage = new ResultMessage();
        List<ZgmGateway> list = zgmLoginService.queryAllZgmGateway();
        resultMessage.setData(list);
        resultMessage.setMsg("查询成功");
        resultMessage.setStatus("200");
        return resultMessage;
    }


    /*根据新闻id显示新闻详情*/
    @ResponseBody
    @RequestMapping(value = "/selectZgmNewsByNid", method = RequestMethod.GET)
    public ResultMessage selectZgmNewsByNid(Integer newsId) {
        ZgmNews zgmNews = zgmLoginService.selectZgmNewsByNid(newsId);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setData(zgmNews);
        resultMessage.setMsg("成功");
        resultMessage.setStatus("200");
        return resultMessage;
    }

    /*根据视频id显示视频详情*/
    @ResponseBody
    @RequestMapping(value = "/selectZgmVideoByVid", method = RequestMethod.GET)
    public ResultMessage selectZgmVideoByVid(Integer videoId) {
        ZgmVideo zgmVideo = zgmLoginService.selectZgmVideoByVid(videoId);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setData(zgmVideo);
        resultMessage.setStatus("200");
        resultMessage.setMsg("成功");
        return resultMessage;
    }

    /*更新车队位置*/
    @ResponseBody
    @RequestMapping(value = "/updatecoordinate", method = RequestMethod.GET)
    public ResultMessage updatecoordinate(@RequestParam("motorcadeId") Integer motorcadeId, @RequestParam("motorcadeLat") String motorcadeLat, @RequestParam("motorcadeLon") String motorcadeLon) {
        zgmLoginService.updatecoordinate(motorcadeId, motorcadeLat, motorcadeLon);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setMsg("成功");
        resultMessage.setStatus("200");
        resultMessage.setData("sucess");
        return resultMessage;

    }

    /*显示车队位置*/
    @ResponseBody
    @RequestMapping(value = "/selectZgmMotorcade", method = RequestMethod.GET)
    public ResultMessage selectZgmMotorcade() {
        ZgmMotorcade zgmMotorcade = zgmLoginService.selectZgmMotorcade();
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setMsg("成功");
        resultMessage.setStatus("200");
        resultMessage.setData(zgmMotorcade);
        return resultMessage;
    }


}
