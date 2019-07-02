package net.zgm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 任务节点中间表
 */
@Data
@ApiModel
public class TaskAndNode implements Serializable {
    @ApiModelProperty(value = "id")
    private int taskAndNodeid;//id
    @ApiModelProperty(value = "任务id")
    private int taskcid;//任务id
    @ApiModelProperty(value = "节点id")
    private int gateway_id;//节点id
    @ApiModelProperty(value = "节点完成步数")
    private int gatewayStep;//节点完成步数
    @ApiModelProperty(value = "是否完成")
    private int isGatewayStep;//是否完成
    @ApiModelProperty(value = "已领取任务")
    private int collarTaskid;//已领取任务
    @ApiModelProperty(value = "用户id")
    private int userid;

}
