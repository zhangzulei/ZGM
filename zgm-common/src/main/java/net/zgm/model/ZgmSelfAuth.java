package net.zgm.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class ZgmSelfAuth implements Serializable {
    private Integer id;

    private Integer userId;

    private String idenPeo;

    private String idenSide;

    private String userName;

    private String idenNo;

    private Integer state;

}