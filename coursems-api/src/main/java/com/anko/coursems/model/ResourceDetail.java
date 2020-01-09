package com.anko.coursems.model;

import lombok.Data;

import java.util.Date;

@Data
public class ResourceDetail {
    private String resId;
    private String resName;
    private String resPath;
    private Date uploadTime;
    private String resSize;
    private int experience;
    private int readed;
    private String clazzId;
}
