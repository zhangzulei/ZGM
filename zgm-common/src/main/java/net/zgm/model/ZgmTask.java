package net.zgm.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by ZYS on 2019/4/29
 */

@Data
public class ZgmTask implements Serializable {

    private Integer taskId;//任务id
    private String taskName;//任务名称
    private String taskPre;//任务简介
    private String taskStatus;//任务状态

}
