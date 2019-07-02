package net.zgm.model;


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
public class ZgmQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer questionId;
    /**
     * 题目编号
     */
    private String code;
    /**
     * 题目类型 1单选
     */
    private Integer type;
    /**
     * 题干
     */
    private String question;
    /**
     * 题目解析
     */
    private String mark;
    /**
     * 答案
     */
    private String result;
    /**
     * 选项
     */
    private String optionsA;
    private String optionsB;
    private String optionsC;
    private String optionsD;

    /**
     * 城市
     */
    private String gatewayId;
    /**
     * 出题人
     */
    private Integer userid;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 已选择答案
     */
    private String questionChoice;
    /**
     * 当前题目答题状态
     *
     */
    private String isComplete;
//    @Override
//    protected Serializable pkVal() {
//        return this.questionId;
//    }


}
