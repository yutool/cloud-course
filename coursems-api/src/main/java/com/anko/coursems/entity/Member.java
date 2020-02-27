package com.anko.coursems.entity;

import lombok.Data;

@Data
public class Member {
    private User user;
//    private String userId;
    private String courseId;
    private int experience;
    private int arrive;
    private int score;
    private String remark;
}
