package com.anko.coursems.dao;

import com.anko.coursems.entity.ClazzInfo;
import com.anko.coursems.entity.ClazzMember;
import com.anko.coursems.model.ClazzDetail;
import com.anko.coursems.model.MemberDetail;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CourseMapper {
    // 获取用户加入的班级
    ArrayList<ClazzDetail> getJoinCourses(String userId);
    // 获取用户创建的班级
    ArrayList<ClazzDetail> getCreateCourses(String userId);
    // 创建班级
    int createCourse(ClazzInfo clazzInfo);
    // 通过班课号查找班级
    ClazzDetail searchCourse(String clazzNum);
    // 加入班级
    int joinCourse(ClazzMember joinForm);
    // 退出班级
    int quitCourse(ClazzMember clazzMember);
    // 解散班级成功
    int dissolveCourse(String clazzId);
    // 删除班级成员
    int deleteClazzMember(String clazzId);
    // 删除班级成员
    int deleteClazzResource(String clazzId);
    // 删除班级成员
    int deleteClazzNotice(String clazzId);
    // 查看用户是否在该班级中
    ClazzMember findClazzMemberById(ClazzMember joinForm);
}
