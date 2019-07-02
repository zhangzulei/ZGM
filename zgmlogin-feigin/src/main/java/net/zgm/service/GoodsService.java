package net.zgm.service;

import net.zgm.model.ResultMessage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by ZYS on 2019/5/8
 */

@FeignClient("service-zgmgoods")
public interface GoodsService {


    @GetMapping("queryAllZgmGoods")
    ResultMessage queryAllZgmGoods(@RequestParam("pageNo") Integer pageNo,
                                   @RequestParam(required = false, value = "goodsclassifyId") Integer goodsclassifyId);

    @GetMapping("queryAllGoodsclassify")
    ResultMessage queryAllGoodsclassify();

    @GetMapping("selectZgmGoodsByGid")
    ResultMessage selectZgmGoodsByGid(@RequestParam("goodsId") Integer goodsId);
}
