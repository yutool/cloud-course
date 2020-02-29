package com.anko.coursems.service;

import com.anko.coursems.entity.Member;

public interface IMemberService {

    /**
     * 添加班级成员
     */
    void addMember(Member member);

    /**
     * 删除班级成员
     */
    int deleteMember(Member member);

    /**
     * 给学生评分
     */
    Member gradeStudent(Member scoreForm);
}
