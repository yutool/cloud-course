package com.anko.coursems.model;

import com.anko.coursems.common.utils.UrlUtils;
import lombok.Builder;
import lombok.Data;

@Data
public class CourseDto {
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
    private String teacherName;

    // 需要获取服务器绝对地址
    public String getCoursePic() {
        return UrlUtils.toServerUrl(coursePic);
    }
}
