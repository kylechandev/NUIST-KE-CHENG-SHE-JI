package com.fht.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class UserCompetition implements Serializable {
    private Integer id;

    private Integer uid;

    private Integer cid;

    private Integer score;

    private String grade;

    private String username;
    private String cname;
    private String realname;
    private String ckind;
    private String applyCapita;
    private String isApply;
    private String capita;
    private String team;
    private String session;
    private String department;


}