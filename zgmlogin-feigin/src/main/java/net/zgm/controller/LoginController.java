package net.zgm.controller;

import net.zgm.model.ResultMessage;
import net.zgm.service.GoodsService;
import net.zgm.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ZYS on 2019/5/7
 */
@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("wxlandapi")
    @ResponseBody
    public ResultMessage wxland(String wxOpenId,
                                @RequestParam(required = false) String phoneNumber,
                                @RequestParam(required = false) String userName,
                                @RequestParam(required = false) String wxPic) {
        return loginService.wxland(wxOpenId, phoneNumber, userName, wxPic);
    }

    @RequestMapping("showGatewayDetailapi")
    @ResponseBody
    public ResultMessage showGatewayDetail(Integer gatewayId) {

        return loginService.showGatewayDetail(gatewayId);
    }

    @RequestMapping("selectZgmGoodsByGIdapi")
    @ResponseBody
    public ResultMessage selectZgmVideoByGId(Integer gatewayId, Integer pageNo, Integer userId) {
        return loginService.selectZgmGoodsByGId(gatewayId, pageNo, userId);
    }

    @RequestMapping("selectZgmVideoByGIdapi")
    @ResponseBody
    public ResultMessage selectZgmVideoByGId(Integer gatewayId, Integer pageNo) {
        return loginService.selectZgmVideoByGId(gatewayId, pageNo);
    }


    @RequestMapping("selectZgmNewsByGIdapi")
    @ResponseBody
    public ResultMessage selectZgmNewsByGId(Integer gatewayId, Integer pageNo) {
        return loginService.selectZgmNewsByGId(gatewayId, pageNo);
    }

    @RequestMapping("queryAllZgmGatewayapi")
    @ResponseBody
    public ResultMessage queryAllZgmGateway() {
        return loginService.queryAllZgmGateway();
    }

    @RequestMapping("selectZgmNewsByNidapi")
    @ResponseBody
    public ResultMessage selectZgmNewsByNid(Integer newsId) {
        return loginService.selectZgmNewsByNid(newsId);
    }

    @RequestMapping("selectZgmVideoByVidapi")
    @ResponseBody
    public ResultMessage selectZgmVideoByVid(Integer videoId) {
        return loginService.selectZgmVideoByVid(videoId);
    }

    @RequestMapping("updatecoordinateapi")
    @ResponseBody
    public ResultMessage updatecoordinate(@RequestParam("motorcadeId") Integer motorcadeId, @RequestParam("motorcadeLat") String motorcadeLat, @RequestParam("motorcadeLon") String motorcadeLon) {
        return loginService.updatecoordinate(motorcadeId, motorcadeLat, motorcadeLon);
    }

    @RequestMapping("selectZgmMotorcadeapi")
    @ResponseBody
    public ResultMessage selectZgmMotorcade() {
        return loginService.selectZgmMotorcade();
    }


    @RequestMapping("queryAllZgmGoodsapi")
    @ResponseBody
    public ResultMessage queryAllZgmGoods(Integer pageNo, @RequestParam(required = false) Integer goodsclassifyId) {
        return goodsService.queryAllZgmGoods(pageNo, goodsclassifyId);
    }


    @RequestMapping("queryAllGoodsclassifyapi")
    @ResponseBody
    public ResultMessage queryAllGoodsclassify() {
        return goodsService.queryAllGoodsclassify();
    }

    @RequestMapping("selectZgmGoodsByGidapi")
    @ResponseBody
    public ResultMessage selectZgmGoodsByGid(Integer goodsId) {
        return goodsService.selectZgmGoodsByGid(goodsId);
    }

    @RequestMapping("Transpondhtml")
    public String Transpondhtml(Integer newsId, HttpServletRequest request) {
        request.setAttribute("newsId", newsId);
        return "news";
    }


}
