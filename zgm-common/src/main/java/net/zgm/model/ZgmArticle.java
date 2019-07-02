package net.zgm.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangxiansheng
 * @create 2019-06-18 13:59
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
@TableName("zgm_article")
public class ZgmArticle implements Serializable {

    private Integer id;

    @TableField("title")
    private String title;

    @TableField("sym")
    private String sym;

    @TableField("publishDate")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")  //FastJson包使用注解
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date publishDate;

    @TableField("createPersonName")
    private String createPersonName;

    @TableField("createDeptId")
    private Integer createDeptId;

    @TableField("createDeptName")
    private String createDeptName;

    @TableField("imgUrl")
    private String imgUrl;

    @TableField("content")
    private String content;

    @TableField("clickCount")
    private Integer clickCount;

    @TableField("stts")
    private Integer stts;

    @TableField("gateway_id")
    private Integer gatewayId;

    @TableField("fabuCount")
    private Integer fabuCount;

    @TableField(exist = false)
    private String[] imgUrlArr;

    @TableField(exist = false)
    private ZgmAdmin zgmAdmin;

    @TableField(exist = false)
    private Integer commentsCount;

}
