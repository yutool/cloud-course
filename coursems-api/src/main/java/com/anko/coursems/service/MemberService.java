package com.anko.coursems.service;

import com.anko.coursems.common.support.IService;
import com.anko.coursems.entity.Member;

public interface MemberService extends IService<Member> {

    /**
     * 添加班级成员
     */
    int addMember(Member member);

    /**
     * 给学生评分
     */
    Member gradeStudent(Member scoreForm);
}
