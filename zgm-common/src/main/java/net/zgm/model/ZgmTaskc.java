package net.zgm.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 任务详情类
 */
@Data
public class ZgmTaskc implements Serializable {

    private int taskcid;//'任务id 自增'
    private int taskcEndNode;//'任务终点'
    private int taskcOriginNode;//'任务起点'
    private String taskcDetails;//'任务详情'
    private String taskcCreator;//'任务创建者'
    private int taskcSumStep;//'任务总步数'
    private String taskcState;//'任务状态0已过期 1未过期 2是已完成'
    private String taskcType;//'任务类型0非学生1学生'
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")  //FastJson包使用注解
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date taskcEndTime;//'任务结束时间'
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")  //FastJson包使用注解
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date taskcCreateTime;//'任务创建时间'
    private String taskcForce;//是否是强制接取任务
//    private String TaskcHot;//是否热门 0不 1热
    private String taskcName;//任务名称

}
