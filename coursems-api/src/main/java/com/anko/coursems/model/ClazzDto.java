package com.anko.coursems.model;

import lombok.Data;

import java.util.List;

@Data
public class ClazzDto {
    private CourseDto course;
    private List<MemberDto> members;
    private List<ResourceDto> resources;
    private List<NoticeDto> notices;
}
