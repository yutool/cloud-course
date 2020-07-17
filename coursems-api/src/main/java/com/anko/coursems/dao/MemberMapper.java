package com.anko.coursems.dao;

import com.anko.coursems.core.BaseMapper;
import com.anko.coursems.entity.Member;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@BaseMapper.Meta(table = "t_member", id = "id")
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
