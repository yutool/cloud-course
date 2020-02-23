package com.anko.coursems.model;

import lombok.Data;

import java.util.Date;

@Data
public class NoticeDto {
    private String noticeId;
    private String content;
    private Date releaseTime;
    private String author;
    private int type;
    private String clazzId;
}
