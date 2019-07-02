package net.zgm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ApiModel
public class AdminTaskcQuestion implements Serializable {
    @ApiModelProperty(value = "个人答题记录id")
    private int recordid;//个人答题记录id
    @ApiModelProperty(value = "用户id")
    private int userid;//用户id
    @ApiModelProperty(value = "已领取id")
    private int collarTaskid;//已领取id
    @ApiModelProperty(value = "节点id")
    private int gateway_id;//节点id
    @ApiModelProperty(value = "题目code")
    private String questionCode;//题目code
    @ApiModelProperty(value = "题目选择答案")
    private String questionChoice;//题目选择答案
    @ApiModelProperty(value = "节点答题是否结束")
    private String isComplete;//节点答题是否结束

}
