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
public class ZgmArea implements Serializable {

    private static final long serialVersionUID = 1L;

	private Integer areaId;
    /**
     * 关卡名称
     */
	private String cityName;
    /**
     * 父级id  0：父级 
     */
	private Integer fatherId;

}
