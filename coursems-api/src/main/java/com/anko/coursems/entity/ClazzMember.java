package com.anko.coursems.entity;

import lombok.Data;

@Data
public class ClazzMember {
    private String clazzId;
    private String userId;
    private int experience;
    private int arrive;
    private int score;
    private String remark;
}
