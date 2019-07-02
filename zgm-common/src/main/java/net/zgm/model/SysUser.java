package net.zgm.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@TableName("sysuser")
@Data
public class SysUser {

    @TableId
    private Integer id;

    @TableField("userName")
    private String userName;

    @TableField("userPwd")
    private String userPwd;

    @TableField("roleSym")
    private String roleSym;

    @TableField("roleName")
    private String roleName;

    @TableField("personId")
    private Integer personId;

    @TableField("personName")
    private String personName;

    @TableField("deptId")
    private Integer deptId;

    @TableField("deptName")
    private String deptName;

    @TableField("stts")
    private Integer stts;

    @TableField("descr")
    private String descr;

}