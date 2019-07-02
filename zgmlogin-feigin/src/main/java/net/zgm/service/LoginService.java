package net.zgm.service;

import net.zgm.model.ResultMessage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by ZYS on 2019/5/7
 */
@FeignClient("service-zgmlogin")
public interface LoginService {



    @GetMapping("wxland")
    ResultMessage wxland(@RequestParam("wxOpenId") String wxOpenId,
                         @RequestParam(required = false, value = "phoneNumber") String phoneNumber,
                         @RequestParam(required = false, value = "userName") String userName,
                         @RequestParam(required = false, value = "wxPic") String wxPic);

    @GetMapping("sendMsg")
    ResultMessage sendMsg(@RequestParam("phoneNumber") String phoneNumber);

    @GetMapping("verification")
    ResultMessage verification(@RequestParam(value = "phoneNumber") String phoneNumber, @RequestParam(required = false, value = "userName") String userName,
                               @RequestParam(required = false, value = "wxOpenId") String wxOpenId, @RequestParam(required = false, value = "wxPic") String wxPic, @RequestParam(value = "code") String code,
                               @RequestParam(required = false, value = "userId") Integer userId);

    @GetMapping("showGatewayDetail")
    ResultMessage showGatewayDetail(@RequestParam(value = "gatewayId") Integer gatewayId);


    @GetMapping("selectZgmGoodsByGId")
    ResultMessage selectZgmGoodsByGId(@RequestParam(value = "gatewayId") Integer gatewayId,
                                      @RequestParam(value = "pageNo") Integer pageNo,
                                      @RequestParam(value = "userId") Integer userId);

    @GetMapping("selectZgmVideoByGId")
    ResultMessage selectZgmVideoByGId(@RequestParam(value = "gatewayId") Integer gatewayId,
                                      @RequestParam(value = "pageNo") Integer pageNo);

    @GetMapping("selectZgmNewsByGId")
    ResultMessage selectZgmNewsByGId(@RequestParam(value = "gatewayId") Integer gatewayId,
                                     @RequestParam(value = "pageNo") Integer pageNo);

    @GetMapping("queryAllZgmGateway")
    ResultMessage queryAllZgmGateway();

    @GetMapping("selectZgmNewsByNid")
    ResultMessage selectZgmNewsByNid(@RequestParam(value = "newsId") Integer newsId);

    @GetMapping("selectZgmVideoByVid")
    ResultMessage selectZgmVideoByVid(@RequestParam(value = "videoId") Integer videoId);

    @GetMapping("updatecoordinate")
    ResultMessage updatecoordinate(@RequestParam("motorcadeId") Integer motorcadeId, @RequestParam("motorcadeLat") String motorcadeLat, @RequestParam("motorcadeLon") String motorcadeLon);

    @GetMapping("selectZgmMotorcade")
    ResultMessage selectZgmMotorcade();


}
