package net.zgm.model;


import lombok.Data;

import java.io.Serializable;

/**
 * Created by ZYS on 2019/4/22
 */
@Data
public class ZgmVideo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer videoId;

    private Integer gatewayId;

    private String videoTitle;


    private String videoUrl;
    private Integer clickzanNum;

//    @Override
//    protected Serializable pkVal() {
//        return this.videoId;
//    }


}
