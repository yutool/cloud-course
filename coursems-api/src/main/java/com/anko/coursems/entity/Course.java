package com.anko.coursems.entity;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@ToString(exclude = {"synopsis","teacher", "members", "resources", "notices"})
public class Course implements Serializable {

    private String courseId;

    private String courseNum;

    private String courseName;

    private String coursePic;

    private String clazzName;

    private String term;

    private String synopsis;

    private Integer arrivesNum;

    private Integer resourcesNum;

    private Integer experiencesNum;

    private Boolean appraise;

    private String teacherId;

    private String teacherName;

}
