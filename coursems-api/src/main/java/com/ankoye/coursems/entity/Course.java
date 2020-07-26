package com.ankoye.coursems.entity;

import com.ankoye.coursems.common.support.BaseEntity;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author ankoye@qq.com
 */
@Data
@Accessors(chain = true)
@ToString(exclude = {"synopsis","teacher", "members", "resources", "notices"})
public class Course extends BaseEntity {

//    private static final long serialVersionUID = 5454990500926278155L;

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
