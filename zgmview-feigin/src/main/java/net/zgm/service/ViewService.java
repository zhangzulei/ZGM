package net.zgm.service;

import net.zgm.model.ResultMessage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by ZYS on 2019/5/8
 */

@FeignClient("service-zgmadmin")
public interface ViewService {

    @GetMapping("selectZgmMsg")
    ResultMessage selectZgmMsg(@RequestParam(value = "userId") Integer userId);

    @GetMapping("selectZgmAdminByUid")
    ResultMessage selectZgmAdminByUid(@RequestParam(value = "userId") Integer userId);

    @GetMapping("updateZgmAddress")
    ResultMessage updateZgmAddress(@RequestParam(value = "addressId") Integer addressId,
                                   @RequestParam(value = "userId") Integer userId,
                                   @RequestParam(value = "receiveArea") String receiveArea,
                                   @RequestParam(value = "receiveName") String receiveName,
                                   @RequestParam(value = "receiveAddress") String receiveAddress,
                                   @RequestParam(value = "defaultAddress") Integer defaultAddress,
                                   @RequestParam(value = "telephone") String telephone);

    @GetMapping("selectZgmAddressByUid")
    ResultMessage selectZgmAddressByUid(@RequestParam(value = "userId") Integer userId);

    @GetMapping("addZgmAddress")
    ResultMessage addZgmAddress(@RequestParam(value = "userId") Integer userId,
                                @RequestParam(value = "receiveArea") String receiveArea,
                                @RequestParam(value = "receiveName") String receiveName,
                                @RequestParam(value = "receiveAddress") String receiveAddress,
                                @RequestParam(value = "defaultAddress") Integer defaultAddress,
                                @RequestParam(value = "telephone") String telephone);

    @GetMapping("deleteZgmAddress")
    ResultMessage deleteZgmAddress(@RequestParam(value = "addressId") Integer addressId);

    @GetMapping("selectIntegralDetail")
    ResultMessage selectIntegralDetail(@RequestParam(value = "userId") Integer userId,
                                       @RequestParam(value = "pageNo") Integer pageNo);

    @GetMapping("clickzanNewsUpdateIntergral")
    ResultMessage clickzanNewsUpdateIntergral(@RequestParam(value = "userId") Integer userId,
                                              @RequestParam(value = "newsId") Integer newsId,
                                              @RequestParam(value = "ifclick") Integer ifclick);

    @GetMapping("clickzanVideoUpdateIntergral")
    ResultMessage clickzanVideoUpdateIntergral(@RequestParam(value = "userId") Integer userId,
                                               @RequestParam(value = "videoId") Integer videoId,
                                               @RequestParam(value = "ifclick") Integer ifclick);

    @GetMapping("addNewsTranspond")
    ResultMessage addNewsTranspond(@RequestParam(value = "userId") Integer userId,
                                   @RequestParam(value = "newsId") Integer newsId
    );

    @GetMapping("addVideoTranspond")
    ResultMessage addVideoTranspond(@RequestParam(value = "userId") Integer userId,
                                    @RequestParam(value = "videoId") Integer videoId
    );

    @GetMapping("selectZgmQuestionByGid")
    ResultMessage selectZgmQuestionByGid(@RequestParam(value = "userId") Integer userId,
                                         @RequestParam(value = "gatewayId") Integer gatewayId);

    @GetMapping("selectAllZgmProvince")
    ResultMessage selectAllZgmProvince();

    @GetMapping("selectZgmGatewayByPid")
    ResultMessage selectZgmGatewayByPid(@RequestParam(value = "provinceId") Integer provinceId);

    @GetMapping("addintegraByquestions")
    ResultMessage addintegraByquestions(@RequestParam(value = "userId") Integer userId,
                                        @RequestParam(value = "gatewayId") Integer gatewayId,
                                        @RequestParam(value = "num") Integer num
    );

    @GetMapping("selectIfclickNews")
    ResultMessage selectIfclickNews(@RequestParam(value = "userId") Integer userId,
                                    @RequestParam(value = "newsId") Integer newsId);

    @GetMapping("selectIfclickVideo")
    ResultMessage selectIfclickVideo(@RequestParam(value = "userId") Integer userId,
                                     @RequestParam(value = "videoId") Integer videoId);

    @GetMapping("queryAllZgmTask")
    ResultMessage queryAllZgmTask(@RequestParam(value = "taskId") Integer taskId);
    @GetMapping("queryAllIntroduce")
    ResultMessage queryAllIntroduce();
}
