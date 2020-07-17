package com.anko.coursems.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class Member implements Serializable {

    private Integer id;

    private String userId;

    private String courseId;

    private Integer arrive;

    private Integer resource;

    private Integer experience;

    private Integer score;

    private String remark;
}
