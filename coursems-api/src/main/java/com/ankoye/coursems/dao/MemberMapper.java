package com.ankoye.coursems.dao;

import com.ankoye.coursems.common.support.BaseMapper;
import com.ankoye.coursems.entity.Member;
import org.springframework.stereotype.Repository;

@Repository
@BaseMapper.Meta(table = "t_member")
public interface MemberMapper extends BaseMapper<Member> {

    int grade(Member scoreForm);
//
//    @Select("select * from t_member" +
//            " where course_id = #{courseId}" +
//            " and user_id = #{user.userId}")
//    Member select(Member member);
//
//    @Insert("insert into t_member(course_id, user_id)" +
//            " values(#{courseId}, #{user.userId})")
//    int add(Member member);
//
//    @Delete("delete from t_member" +
//            " where course_id = #{courseId}" +
//            " and user_id = #{user.userId}")
//    int delete(Member clazzMember);
//
//    @Delete("delete from t_member" +
//            " where course_id = #{courseId}")
//    int deleteMembersByCourseId(String courseId);

}
