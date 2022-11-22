package com.fht.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class UserRole implements Serializable {
    private Integer uid;
    private Integer roleId;
    private String content;
    private String description;

}