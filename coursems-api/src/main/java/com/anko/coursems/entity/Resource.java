package com.anko.coursems.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Resource {
    private String resId;
    private String resName;
    private String downLink;
    private Date uploadTime;
    private String resSize;
    private int experience;
    private String courseId;
}
