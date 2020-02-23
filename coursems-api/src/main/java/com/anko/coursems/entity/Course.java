package com.anko.coursems.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@ToString(exclude = {"clazzPhoto","clazzExplain","term", "arrives"})
@Data
public class Course implements Serializable {
    private String courseId;
    private String courseNum;
    private String courseName;
    private String coursePic;
    private String clazzName;
    private String synopsis;
    private String term;
    private int arrives;
    private int resources;
    private int experiences;
    private boolean appraise;
    private String teacherId;
}
