package com.fht.entity;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
@Data
public class Role implements Serializable {
    private Integer id;
    @NotEmpty(message = "角色内容不能为空")
    private String content;
    @NotEmpty(message = "角色描述不能为空")
    private String description;

    private static final long serialVersionUID = 1L;


}