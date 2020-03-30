package com.anko.coursems.entity;

import lombok.Data;

@Data
public class Member {
    private User user;
    private String courseId;
    private int arrive;
    private int resource;
    private int experience;
    private int score;
    private String remark;
}
