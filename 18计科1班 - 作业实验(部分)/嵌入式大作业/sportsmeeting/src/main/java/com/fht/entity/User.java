package com.fht.entity;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
@Data
public class User implements Serializable {
    private Integer id;

    @NotEmpty(message = "用户名不能为空")
    private String username;
    @NotEmpty(message = "密码不能为空")
    private String password;

    private Integer sex;

    private String email;

    private String phone;
    @NotEmpty(message = "真实姓名不能为空")
    private String realname;

    private String department;

    private String team;

    private String startSchoolYear;

    private String leaveSchoolYear;

    private Byte enable;

    private UserRole userRole;

    private static final long serialVersionUID = 1L;


}