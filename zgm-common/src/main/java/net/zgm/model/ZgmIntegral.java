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
public class ZgmIntegral implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer integralId;
    /**
     * 积分
     */
    private Integer clickIntegral;
    /**
     * 用户id
     */
    private Integer transpondIntegral;

    /**
     * 积分
     */
    private Integer answerIntegral;

    /**
     * 最大点赞次数
     */
    private Integer clickMax;


    /**
     * 最大转发次数
     */
    private Integer transpondMax;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }



//    @Override
//    protected Serializable pkVal() {
//        return this.integralId;
//    }


}
