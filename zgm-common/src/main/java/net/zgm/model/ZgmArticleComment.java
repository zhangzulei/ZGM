package net.zgm.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author wangxiansheng
 * @create 2019-06-14 9:55
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
@TableName("zgm_article_comment")
@ApiModel
public class ZgmArticleComment implements Serializable {

    @ApiModelProperty(value = "评论ID",name = "id")
    private Integer id;

    @ApiModelProperty(value = "文章ID",name = "articleId")
    @TableField("article_id")
    private Integer articleId;

    @TableField("comment_user_id")
    @ApiModelProperty(value = "留言的用户ID",name = "commentUserId")
    private Integer commentUserId;

    @TableField("comment_date")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")  //FastJson包使用注解
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "回复时间",name = "commentDate")
    private Date commentDate;

    @TableField("comment_content")
    @ApiModelProperty(value = "回复内容",name = "commentContent")
    private String commentContent;

    @TableField("comment_fabulous")
    @ApiModelProperty(value = "留言的赞数",name = "commentFabulous")
    private Integer commentFabulous;

    @TableField("comment_state")
    @ApiModelProperty(value = "评论的状态",name = "commentState")
    private Integer commentState;

    @TableField("parent_id")
    @ApiModelProperty(value = "父级评论ID",name = "parentId")
    private Integer parentId;

    @TableField("objcomm_id")
    @ApiModelProperty(value = "顶级评论ID",name = "objcommId")
    private Integer objcommId;

    @TableField(exist = false)
    private List<ZgmArticleComment> commentList;

    /**
     * 留言人用户名
     */
    @TableField(exist = false)
    private String commentUserName;

    /**
     * 回复人用户名
     */
    @TableField(exist = false)
    private String replyUserName;

    /**
     * 子评论数量
     */
    @TableField(exist = false)
    private Integer commentsCount;

    @TableField(exist = false)
    private String userHeadPath;

}
