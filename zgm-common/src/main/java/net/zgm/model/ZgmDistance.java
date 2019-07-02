package net.zgm.model;


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
public class ZgmDistance implements Serializable {

    private static final long serialVersionUID = 1L;

	private Integer distanceId;
    /**
     * 当前时间
     */
	private Date currenttime;
    /**
     * 总公里数
     */
	private Double kilometertotal;
    /**
     * 用户id
     */
	private Integer adminid;
    /**
     * 用户行走总步数
     */
	private Long userstepnumber;
    /**
     * 每步距离设置
     */
	private Double stepset;




//	@Override
//	protected Serializable pkVal() {
//		return this.distanceId;
//	}


}
