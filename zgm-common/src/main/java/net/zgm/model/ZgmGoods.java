package net.zgm.model;


import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author stylefeng
 * @since 2019-04-22
 */
@Data
public class ZgmGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer goodsId;
    /**
     * 货物图片
     */

    private String gatewayId;

    private String goodsPic;
    /**
     * 货物价格
     */
    private Double goodsPrice;
    /**
     * 货物详情
     */
    private String goodsDetail;
    /**
     * 货物名称
     */
    private String goodsName;
    /**
     * 是否售罄
     */
    private Integer issellout;
    /**
     * 商品库存
     */
    private Integer goodsRepertory;
    /*商品积分*/
    private Integer goodsIntegral;

    private Integer goodsclassifyId;

    private Integer goodsFreight;



//    @Override
//    protected Serializable pkVal() {
//        return this.goodsId;
//    }


}
