package net.zgm.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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
@TableName("zgm_admin")
public class ZgmAdmin  implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    @TableId("user_id")
    private Integer userId;
    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    @TableField("user_name")
    private String userName;
    /**
     * 微信开发id
     */
    @ApiModelProperty(value = "微信开发id")
    @TableField("wx_open_id")
    private String wxOpenId;
    /**
     * 微信号
     */
    @ApiModelProperty(value = "微信号")
    @TableField("wx_no")
    private String wxNo;
    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    @TableField("telephone")
    private String telephone;
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    @TableField("sex")
    private Integer sex;

    @TableField("createtime")
    private Date createtime;
    /**
     * 微信头像
     */
    @ApiModelProperty(value = "微信头像")
    @TableField("wx_pic")
    private String wxPic;
    /**
     * 积分
     */
    @TableField("motorcade_id")
    private Integer motorcadeId;

    @TableField("integral")
    private String integral;
    /**
     * 步数更新最后时间
     */
    @TableField("stepTime")
    private Date stepTime;
    /**
     * l登录状态
     */
    @TableField("loginStatus")
    private Integer loginStatus;



//    @Override
//    protected Serializable pkVal() {
//        return this.userId;
//    }




}
