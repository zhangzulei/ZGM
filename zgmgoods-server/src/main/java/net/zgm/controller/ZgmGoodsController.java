package net.zgm.controller;

import com.github.pagehelper.PageInfo;
import net.zgm.model.ResultMessage;
import net.zgm.model.ZgmGoods;
import net.zgm.model.ZgmGoodsclassify;
import net.zgm.service.ZgmGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ZYS on 2019/5/8
 */
@RestController
public class ZgmGoodsController {
    @Autowired
    private ZgmGoodsService zgmGoodsService;

    /*查询所有积分兑换商品*/
    @ResponseBody
    @RequestMapping(value = "/queryAllZgmGoods", method = RequestMethod.GET)
    public ResultMessage queryAllZgmGoods(Integer pageNo, @RequestParam(required = false) Integer goodsclassifyId) {
        ResultMessage resultMessage = new ResultMessage();
        PageInfo<ZgmGoods> zgmGoodsPageInfo = zgmGoodsService.queryAllZmgGoods(pageNo, 10, goodsclassifyId);
        System.out.println(zgmGoodsPageInfo.getSize());
        resultMessage.setData(zgmGoodsPageInfo);
        resultMessage.setMsg("查询成功");
        resultMessage.setStatus("200");
        return resultMessage;
    }

    /*所有商品分类*/
    @ResponseBody
    @RequestMapping(value = "/queryAllGoodsclassify", method = RequestMethod.GET)
    public ResultMessage queryAllGoodsclassify() {
        List<ZgmGoodsclassify> zgmGoodsclassifies = zgmGoodsService.queryAllGoodsclassify();
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setMsg("成功");
        resultMessage.setStatus("200");
        resultMessage.setData(zgmGoodsclassifies);
        return resultMessage;

    }


    /*根据商品id查询商品详情*/
    @ResponseBody
    @RequestMapping(value = "/selectZgmGoodsByGid", method = RequestMethod.GET)
    public ResultMessage selectZgmGoodsByGid(Integer goodsId) {
        ResultMessage resultMessage = new ResultMessage();
        ZgmGoods zgmGoods = zgmGoodsService.selectZgmGoodsByGoodId(goodsId);
        resultMessage.setData(zgmGoods);
        resultMessage.setStatus("200");
        resultMessage.setMsg("查询成功");
        return resultMessage;
    }

}
