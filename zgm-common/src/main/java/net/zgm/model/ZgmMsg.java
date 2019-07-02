package net.zgm.model;


import lombok.Data;

import java.io.Serializable;

/**
 * Created by ZYS on 2019/4/27
 */
@Data
public class ZgmMsg implements Serializable {


    private Object type;
    private Object integral;
    private String date;
    private Object addorsubtract;


}
