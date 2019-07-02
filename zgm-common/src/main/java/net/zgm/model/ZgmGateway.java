package net.zgm.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author stylefeng
 * @since 2019-04-22
 */
@Data
@ApiModel
public class ZgmGateway implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "节点id")
    private Integer gatewayId;
    /**
     * 国门名称
     */
    @ApiModelProperty(value = "国门名称")
    private String gatewayName;
    /**
     * 国门图片
     */
    @ApiModelProperty(value = "国门图片")
    private String gatewayPic;
    /**
     * 国门描述
     */
    @ApiModelProperty(value = "国门描述")
    private String gatewayPre;
    /**
     * 城市概括
     */
    @ApiModelProperty(value = "城市概括")
    private String gatewayCity;
    /**
     * 国门特产
     */
    @ApiModelProperty(value = "国门特产")
    private String gatewaySpecialty;
    /**
     * 国门旅游资源
     */
    @ApiModelProperty(value = "国门旅游资源")
    private String gatewayTravel;
    /**
     * 国门坐标
     */
    @ApiModelProperty(value = "国门坐标")
    private String gatewayCoordinate;
    /**
     * 当前任务检查点距离自己多少步
     */
    @ApiModelProperty(value = "当前任务检查点距离自己多少步")
    private int rangeStep;
    private String gatewayProvinceid;
    private String gatewayGoogle;
    private String flag;

    private String gatewaySequence;
    /**
     * 当前检查点需要的步数
     */
    @ApiModelProperty(value = "当前检查点需要的步数")
    private int gatewayStep;
    /**
     * 当前检查点是否完成
     */
    @ApiModelProperty(value = "当前检查点是否完成")
    private String isGatewayStep;
    /**
     * 节点省份名称
     */
    @ApiModelProperty(value = "节点省份名称")
    private String gatewayPName;

//    @Override
//    protected Serializable pkVal() {
//        return this.gatewayId;
//    }


}
