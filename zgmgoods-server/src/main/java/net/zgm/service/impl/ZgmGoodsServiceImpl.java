package net.zgm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.zgm.mapper.*;
import net.zgm.model.ZgmGoods;
import net.zgm.model.ZgmGoodsclassify;
import net.zgm.service.ZgmGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ZYS on 2019/5/8
 */
@Service
@Transactional
public class ZgmGoodsServiceImpl implements ZgmGoodsService {

    @Autowired
    private ZgmGoodsMapper zgmGoodsMapper;

    @Autowired
    private ZgmGoodsclassifyMapper zgmGoodsclassifyMapper;


    @Override
    public PageInfo<ZgmGoods> queryAllZmgGoods(Integer pageNo, Integer count, Integer goodsclassifyId) {
        PageHelper.startPage(pageNo, count);
        List<ZgmGoods> list = zgmGoodsMapper.queryAllZmgGoods(goodsclassifyId);
        PageInfo<ZgmGoods> zgmGoodsPageInfo = new PageInfo<ZgmGoods>(list);
        return zgmGoodsPageInfo;
    }

    @Override
    public List<ZgmGoodsclassify> queryAllGoodsclassify() {
        List<ZgmGoodsclassify> zgmGoodsclassifies = zgmGoodsclassifyMapper.queryAllGoodsclassify();
        return zgmGoodsclassifies;
    }

    @Override
    public ZgmGoods selectZgmGoodsByGoodId(Integer goodsId) {
        ZgmGoods zgmGoods = zgmGoodsMapper.selectZgmGoodsByGid(goodsId);
        return zgmGoods;
    }
}

