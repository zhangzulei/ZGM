package net.zgm.model;


import lombok.Data;

import java.io.Serializable;

/**
 * Created by ZYS on 2019/4/26
 */
@Data
public class ZgmMotorcade implements Serializable {

    private Integer motorcadeId;

    private String motorcadeName;

    private String motorcadeLat;


    private String motorcadeLon;

}
