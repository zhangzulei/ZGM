package net.zgm.model;

import java.io.Serializable;

public class ZgmEnteAuth implements Serializable {
    private Integer id;

    private Integer userId;

    private String userName;

    private String idenPeo;

    private String idenSide;

    private String idenNo;

    private String busiLice;

    private Integer state;

    private String other;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getIdenPeo() {
        return idenPeo;
    }

    public void setIdenPeo(String idenPeo) {
        this.idenPeo = idenPeo == null ? null : idenPeo.trim();
    }

    public String getIdenSide() {
        return idenSide;
    }

    public void setIdenSide(String idenSide) {
        this.idenSide = idenSide == null ? null : idenSide.trim();
    }

    public String getIdenNo() {
        return idenNo;
    }

    public void setIdenNo(String idenNo) {
        this.idenNo = idenNo == null ? null : idenNo.trim();
    }

    public String getBusiLice() {
        return busiLice;
    }

    public void setBusiLice(String busiLice) {
        this.busiLice = busiLice == null ? null : busiLice.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }
}