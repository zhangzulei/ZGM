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
public class ZgmAddress implements Serializable{

    private static final long serialVersionUID = 1L;


    private Integer addressId;
    /**
     * 用户id
     */

    private Integer userId;
    /**
     * 收货人名称
     */

    private String receiveName;
    /**
     * 收货地址
     */


    private String receiveArea;

    private String receiveAddress;
    /**
     * 手机号
     */
    private String telephone;
    /**
     * 是否为默认地址 0：未默认 1：已默认
     */
    private Integer defaultAddress;
    private Date createTime;

    private Date updateTime;


}
