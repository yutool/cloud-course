package com.anko.coursems.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class UserCourse {
    private List<ClazzDetail> joinCourses;
    private List<ClazzDetail> createCourses;
}
