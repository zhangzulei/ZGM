package net.zgm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by ZYS on 2019/4/24
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultMessage implements Serializable {

    private String status;

    private String msg;

    private Object data;
}
