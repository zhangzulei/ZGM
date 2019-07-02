package net.zgm.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangxiansheng
 * @create 2019-06-03 14:44
 * <p>
 * |                   _oo8oo_
 * |                  o8888888o
 * |                  88" . "88
 * |                  (| -_- |)
 * |                  0\  =  /0
 * |                ___/'==='\___
 * |              .' \\|     |// '.
 * |             / \\|||  :  |||// \
 * |            / _||||| -:- |||||_ \
 * |           |   | \\\  -  /// |   |
 * |           | \_|  ''\---/''  |_/ |
 * |           \  .-\__  '-'  __/-.  /
 * |         ___'. .'  /--.--\  '. .'___
 * |      ."" '<  '.___\_<|>_/___.'  >' "".
 * |     | | :  `- \`.:`\ _ /`:.`/ -`  : | |
 * |     \  \ `-.   \_ __\ /__ _/   .-` /  /
 * | =====`-.____`.___ \_____/ ___.`____.-`=====
 * |                   `=---=`
 * | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * |         佛祖保佑         永不宕机/永无bug
 */
@Data
@ToString
@TableName("zgm_userrela")
public class ZgmUser implements Serializable {

    /**
     * 主键ID,自增列
     */
    @TableId("id")
    private Integer id;

    /**
     * '关联的用户ID'
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * '用户姓名'
     */
    @TableField("user_name")
    private String userName;

    /**
     * '用户性别'
     */
    @TableField("user_sex")
    private String userSex;

    /**
     * '用户年龄'
     */
    @TableField("user_age")
    private Integer userAge;

    /**
     * '用户学校'
     */
    @TableField("user_school")
    private String userSchool;

    /**
     * '用户年级'
     */
    @TableField("user_grade")
    private String userGrade;

    /**
     * '用户班级'
     */
    @TableField("user_class")
    private String userClass;

    /**
     * '用户地址省ID'
     */
    @TableField("user_provinces")
    private Integer userProvinces;

    /**
     * '用户地址市ID'
     */
    @TableField("user_cities")
    private Integer userCities;

    /**
     * '用户地址县区ID'
     */
    @TableField("user_areas")
    private Integer userAreas;

    /**
     * '用户详细地址'
     */
    @TableField("user_address")
    private String userAddress;

    /**
     * '0-未删除 1-已删除'
     */
    @TableField("isdelete")
    private Integer isdelete;

    /**
     * '注册时间'
     */
    @TableField("create_date")
    private Date createDate;

    /**
     * '是否是学生 0-不是学生 1-是学生'
     */
    @TableField("isstudent")
    private Integer isStudent;

    @TableField("user_subject")
    private String userSubject;

}
