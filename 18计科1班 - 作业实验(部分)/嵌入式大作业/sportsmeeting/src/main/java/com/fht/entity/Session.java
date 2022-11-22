package com.fht.entity;

import java.io.Serializable;
import lombok.Data;
/**
 * (Session)实体类
 *
 * @author halt
 * @since 2020-04-11 14:58:17
 */
@Data
public class Session implements Serializable {
    private static final long serialVersionUID = -54842948002335000L;
    
    private Integer id;
    
    private String content;
    
    private String description;
    /**
    * 当前运动会  默认0   当前运动会为1
    */
    private Integer status;


}