package com.anko.coursems.model;

import lombok.Data;

@Data
public class MemberDetail {
    private String userId;
    private String stuName;
    private String stuNum;
    private String signature;
    private int experience;
    private int viewRes;
    private int arrive;
    private int score;
    private String remark;
}
