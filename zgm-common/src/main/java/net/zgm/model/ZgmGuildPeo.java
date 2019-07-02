package net.zgm.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class ZgmGuildPeo implements Serializable {
    private Integer id;

    private String guildId;

    private Integer userId;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")  //FastJson包使用注解
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    private Integer isadopt;

    private Integer role;

}