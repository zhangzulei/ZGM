package net.zgm.service;

import com.github.pagehelper.PageInfo;
import net.zgm.model.ZgmGoods;
import net.zgm.model.ZgmGoodsclassify;

import java.util.List;

/**
 * Created by ZYS on 2019/5/8
 */
public interface ZgmGoodsService {

    /*查询所有积分兑换商品*/
    PageInfo<ZgmGoods> queryAllZmgGoods(Integer pageNo, Integer count, Integer goodsclassifyId);

    /*查询所有商品分类*/
    List<ZgmGoodsclassify> queryAllGoodsclassify();

    /*根据商品id查询商品详情*/
    ZgmGoods selectZgmGoodsByGoodId(Integer goodsId);
}
