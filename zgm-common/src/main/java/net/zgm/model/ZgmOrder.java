package net.zgm.model;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author stylefeng
 * @since 2019-04-22
 */
@Data
public class ZgmOrder  {


    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    private Integer orderId;
    private Integer userId;
    /**
     * 订单用户名
     */
    private String userName;
    /**
     * 收货人姓名
     */
    private String receiveName;
    /**
     * 收获地址
     */

    private String receiveArea;
    private String receiveAddress;
    /**
     * 手机号
     */
    private String telephone;
    /**
     * 商品ID
     */
    private Integer goodsId;
    /**
     * 货物状态 0：未发货 1：已发货 2：已收货
     */
    private Integer transStatus;
    /**
     * 物流单号
     */
    private String logisticNum;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 订单创建时间
     */
    private Date createTime;

    /*订单发货时间*/
    private Date shipmentsTime;


    /*订单收货时间*/
    private Date receivingTime;



//    @Override
//    protected Serializable pkVal() {
//        return this.orderId;
//    }


}
