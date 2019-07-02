package net.zgm.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@NoArgsConstructor
public class ZgmGuildLog implements Serializable {
    private Integer id;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")  //FastJson包使用注解
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;

    private String guildId;

    private String content;

    private Integer userId;

    private Integer isdelete;

    /**
     * 构造函数
     * @param date 创建日期
     * @param guildId 操作公会的ID
     * @param content 操作内容
     * @param userId 操作人
     */
    public ZgmGuildLog(Date date, String guildId, String content, Integer userId) {
        this.date = date;
        this.guildId = guildId;
        this.content = content;
        this.userId = userId;
    }
}