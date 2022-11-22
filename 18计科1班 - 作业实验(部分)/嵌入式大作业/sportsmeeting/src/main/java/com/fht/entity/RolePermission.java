package com.fht.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class RolePermission implements Serializable {
    private Integer permissionId;
    private Integer roleId;
    private String content;
    private String description;

}