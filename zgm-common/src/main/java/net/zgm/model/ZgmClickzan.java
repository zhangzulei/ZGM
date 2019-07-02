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
public class ZgmClickzan implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer clickId;
    private Integer userId;
    private Integer newsId;
    private Integer videoId;
    private Date clicknewsTime;
    private Date clickvideoTime;
    private String clicknewsIntegral;
    private String clickvideoIntegral;


//    @Override
//    protected Serializable pkVal() {
//        return this.clickId;
//    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

}
