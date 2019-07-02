package net.zgm.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 已领取任务类
 */
@Data
@ApiModel(value="已领取任务对象",description="已任务对象ZgmCollarTask")
public class ZgmCollarTask implements Serializable {
    @ApiModelProperty(value="已领取任务id",name="collarTaskid")
    private int collarTaskid;//'已领取任务id 主键'
    @ApiModelProperty(value="任务id",name="taskcid")
    private int taskcid;//'任务id'
    @ApiModelProperty(value="用户id",name="adminid")
    private int userid;//'用户id'
    @ApiModelProperty(value="任务领取人id",name="userid")
    private int relaid;//'任务领取人id'
    @ApiModelProperty(value="任务完成步数",name="collarTaskcStep")
    private int collarTaskcStep;//'任务完成步数'
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")  //FastJson包使用注解
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value="任务领取时间",name="collarTaskcCreateTime")
    private Date collarTaskcCreateTime;//'任务领取时间'
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")  //FastJson包使用注解
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value="任务完成时间",name="collarTaskcEndTime")
    private Date collarTaskcEndTime;//'任务完成时间'
    @ApiModelProperty(value="任务领取方式",name="collarTaskcMode")
    private String collarTaskcMode;//'任务领取方式 0普通1付费'
    @ApiModelProperty(value="0代表非学生1代表学生类型",name="collarTaskcType")
    private String collarTaskcType;//'0代表非学生1代表学生类型'
    @ApiModelProperty(value="任务状态 0位过期 1为未过期",name="collarTaskcState")
    private String collarTaskcState;//'任务状态 0位过期 1为未过期'2为已完成
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")  //FastJson包使用注解
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value="步数最后更新时间",name="collarTaskcStepTime")
    private Date collarTaskcStepTime;

//    @ApiModelProperty(value="是否热门 0不1热",name="collarTaskcHot")
//    private String collarTaskcHot;//是否热门 0不1热
@ApiModelProperty(value="当前任务数量",name="count1")
    private int count1;
    @ApiModelProperty(value="每次的更新步数",name="stpe")
    private int stpe; //每次的更新步数

    //联查返回需要
    @ApiModelProperty(value="任务终点",name="taskcEnd")
    private String taskcEndNode;//'任务终点'
    @ApiModelProperty(value="任务起点",name="taskcOrigin")
    private String taskcOriginNode;//'任务起点'
    @ApiModelProperty(value="任务详情",name="taskcDetails")
    private String taskcDetails;//'任务详情'
    @ApiModelProperty(value="任务创建者",name="taskcCreator")
    private String taskcCreator;//'任务创建者'
    @ApiModelProperty(value="任务总步数",name="taskcSumStep")
    private int taskcSumStep;//'任务总步数'
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")  //FastJson包使用注解
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value="任务结束时间",name="taskcEndTime")
    private Date taskcEndTime;//'任务结束时间'
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")  //FastJson包使用注解
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value="任务创建时间",name="taskcCreateTime")
    private Date taskcCreateTime;//'任务创建时间'
    @ApiModelProperty(value="是否是强制接取任务",name="taskcForce")
    private String taskcForce;//是否是强制接取任务
    //    private String TaskcHot;//是否热门 0不 1热
    @ApiModelProperty(value="任务名称",name="taskcName")
    private String taskcName;//任务名称
    //dshjfdkfd






    
}
