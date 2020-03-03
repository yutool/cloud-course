package com.anko.coursems.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Notice {
    private String noticeId;
    private String content;
    private String author;
    private Date releaseTime;
    private int type;
    private String courseId;
}
