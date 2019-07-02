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
public class ZgmNews implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer newsId;
    private String newsTitle;
    private String content;
    private Date createAt;
    private String image;
    private String movie;

    private Integer gatewayId;
    private Integer clickzanNum;



//    @Override
//    protected Serializable pkVal() {
//        return this.newsId;
//    }


}
