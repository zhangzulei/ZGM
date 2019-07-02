package net.zgm.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ZYS on 2019/4/27
 */
@Data
public class ZgmAnswer implements Serializable {

    private Integer answerId;

    private String userId;

    private String gatewayId;
    private Date createTime;
    private String integral;
}
