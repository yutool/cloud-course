package com.ankoye.coursems.service;

import com.ankoye.coursems.common.support.IService;
import com.ankoye.coursems.entity.Member;

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
