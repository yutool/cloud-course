package com.anko.coursems.dao;

import com.anko.coursems.entity.Member;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberMapper {

    Member select(Member member);

    int add(Member member);

    int delete(Member clazzMember);

    int grade(Member scoreForm);

    int deleteMemberByCourseId(String courseId);
}
