package com.fht.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
@JsonIgnoreProperties(value = { "handler" })
public class Competition implements Serializable {

    private Integer id;
    @NotEmpty(message = "项目名不能为空")
    private String name;

    private String description;

    private String site;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;

    private String kind;

    private Integer capita;
    private Integer isTeam;


    private List<UserCompetition> userCompetition;


}