package com.anko.coursems.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString(exclude = {"synopsis","teacher", "members", "resources", "notices"})
public class Course implements Serializable {
    private String courseId;
    private String courseNum;
    private String courseName;
    private String coursePic;
    private String clazzName;
    private String synopsis;
    private String term;
    private int arrivesNum;
    private int resourcesNum;
    private int experiencesNum;
    private boolean appraise;
    private User teacher;
    private List<Member> members;
    private List<Resource> resources;
    private List<Notice> notices;
}
