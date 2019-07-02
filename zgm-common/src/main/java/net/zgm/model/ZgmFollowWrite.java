package net.zgm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author wangxiansheng
 * @create 2019-06-11 18:06
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
@AllArgsConstructor
@NoArgsConstructor
public class ZgmFollowWrite implements Serializable {

    private Integer id;

    private Integer userId;

    private Integer followId;

    private Date createDate;

    private Integer isDelete;

    public ZgmFollowWrite(Integer userId, Integer followId, Date createDate, Integer isDelete) {
        this.userId = userId;
        this.followId = followId;
        this.createDate = createDate;
        this.isDelete = isDelete;
    }
}

