package net.zgm.model;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ZYS on 2019/4/24
 */
@Data
public class ZgmTranspond implements Serializable {

    private Integer transpondId;
    private Integer userId;
    private Integer newsId;
    private Integer videoId;
    private Date transpondnewsTime;
    private Date transpondvideoTime;
    private String transpondnewsIntegral;
    private String transpondvideoIntegral;

}
