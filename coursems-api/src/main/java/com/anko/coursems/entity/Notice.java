package com.anko.coursems.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Notice {
    private String noticeId;

    private String content;

    private String author;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date releaseTime;

    private int type;

    private String courseId;
}
