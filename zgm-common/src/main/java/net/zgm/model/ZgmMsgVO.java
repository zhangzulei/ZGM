package net.zgm.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by ZYS on 2019/5/17
 */
@Data
public class ZgmMsgVO {

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


    private Integer transpondId;
    private Integer userId;
    private Integer newsId;
    private Integer videoId;
    private Date transpondnewsTime;
    private Date transpondvideoTime;
    private String transpondnewsIntegral;
    private String transpondvideoIntegral;

    private Integer clickId;
    private Integer zanuserId;
    private Integer zannewsId;
    private Integer zanvideoId;
    private Date clicknewsTime;
    private Date clickvideoTime;
    private String clicknewsIntegral;
    private String clickvideoIntegral;

    private Integer answerId;



    private String answeruserId;

    private String gatewayId;
    private Date answercreateTime;
    private String integral;
}
