package com.anko.coursems.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Notice {
    private String noticeId;
    private String content;
    private Date releaseTime;
    private String author;
    private int type;
    private String clazzId;
}
