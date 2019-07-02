package net.zgm.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by ZYS on 2019/4/26
 */

@Data
public class ZgmGoodsclassify implements Serializable {



    private Integer goodsclassifyId;
    /**
     * 货物图片
     */

    private String goodsclassifyName;
}
