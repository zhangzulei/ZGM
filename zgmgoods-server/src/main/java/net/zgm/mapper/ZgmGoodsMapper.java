package net.zgm.mapper;


import net.zgm.model.ZgmGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2019-04-22
 */
public interface ZgmGoodsMapper{
    List<ZgmGoods> queryAllZmgGoods(@Param("goodsclassifyId") Integer goodsclassifyId);

    void convertGoods(Integer goodsId);

    ZgmGoods selectZgmGoodsByGid(Integer goodsId);


}