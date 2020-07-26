package com.ankoye.coursems.dao;

import com.ankoye.coursems.common.support.BaseMapper;
import com.ankoye.coursems.entity.Course;
import com.ankoye.coursems.model.CourseDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@BaseMapper.Meta(table = "t_course", exId = "course_id")
public interface CourseMapper extends BaseMapper<Course> {

    List<Course> joinList(String userId);

    List<Course> createList(String userId);

    CourseDTO getCourseDetail(String clazzId);


//    @Select("select * from t_course" +
//            " where course_num = #{courseNum}")
//    Course findByNum(String num);
//
//    @Insert("insert into t_course(course_id, course_num, clazz_name, course_name, term, synopsis, course_pic, teacher_id)" +
//            " values(#{courseId}, #{courseNum}, #{clazzName}, #{courseName}, #{term}, #{synopsis}, #{coursePic}, #{teacher.userId})")
//    int add(Course course);
//
//    @Delete("delete from t_course" +
//            " where course_id = #{courseId}")
//    int delete(String id);
//
//    @Update("update t_course" +
//            " set appraise = #{appraise}" +
//            " where course_id=#{courseId}")
//    int toggleAppraise(Course course);
//
//    @Delete("update  t_course" +
//            " set course_pic = #{coursePic}" +
//            " where course_id = #{courseId}")
//    int updatePic(@Param("courseId") String id, @Param("coursePic") String relativePath);

}
