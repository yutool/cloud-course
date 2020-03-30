package com.anko.coursems.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Resource {
    private String resId;
    private String resName;
    private Long resSize;
    private String downLink;
    private Date uploadTime;
    private int experience;
    private String courseId;
}
