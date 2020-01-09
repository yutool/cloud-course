package com.anko.coursems.service.impl;

import com.anko.coursems.dao.CourseMapper;
import com.anko.coursems.entity.ClazzInfo;
import com.anko.coursems.entity.ClazzMember;
import com.anko.coursems.model.ClazzDetail;
import com.anko.coursems.model.RequestResult;
import com.anko.coursems.model.StatusCode;
import com.anko.coursems.model.UserCourse;
import com.anko.coursems.service.ICourseService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class CourseService implements ICourseService {
    @Autowired
    private CourseMapper courseMapper;

    public RequestResult getAllCourse(String userId) {
        ArrayList<ClazzDetail> joinCourse = courseMapper.getJoinCourses(userId);
        ArrayList<ClazzDetail> createCourse = courseMapper.getCreateCourses(userId);
        UserCourse userCourse = new UserCourse(joinCourse, createCourse);
        return new RequestResult(userCourse,"查询所有班级成功", StatusCode.SUCCESS);
    }

    public RequestResult createCourse(ClazzInfo clazzInfo) {
        String clazzId = "c" + RandomStringUtils.randomAlphanumeric(19);
        clazzInfo.setClazzId(clazzId);      // 设置前验证id是否存在
        String clazzNum = RandomStringUtils.randomNumeric(6);
        clazzInfo.setClazzNum(clazzNum);    // 设置前验证num是否存在
        int res = courseMapper.createCourse(clazzInfo);
        if(res >= 1) {
            return new RequestResult(clazzId,"创建班级成功", StatusCode.SUCCESS);
        } else {
            return new RequestResult("创建班级失败", StatusCode.ERROR);
        }
    }

    public RequestResult searchCourse(String clazzNum) {
        ClazzDetail clazzDetail = courseMapper.searchCourse(clazzNum);
        if(clazzDetail != null) {
            return new RequestResult(clazzDetail,"查找班级成功", StatusCode.SUCCESS);
        } else {
            return new RequestResult("班级不存在", StatusCode.ERROR);
        }
    }

    public RequestResult joinCourse(ClazzMember joinForm) {
        if (courseMapper.findClazzMemberById(joinForm) != null) {
            return new RequestResult("你已经在该班级中", StatusCode.ERROR);
        }
        int res = courseMapper.joinCourse(joinForm);
        if(res >= 1) {
            return new RequestResult("加入班级成功", StatusCode.SUCCESS);
        } else {
            return new RequestResult("加入班级失败", StatusCode.ERROR);
        }
    }

    public RequestResult quitCourse(ClazzMember clazzMember) {
        int res = courseMapper.quitCourse(clazzMember);
        if(res >= 1) {
            return new RequestResult("退出班级成功", StatusCode.SUCCESS);
        } else {
            return new RequestResult("退出班级失败", StatusCode.ERROR);
        }
    }
    // 解散班级
    @Transactional
    public RequestResult dissolveCourse(String clazzId) {
        courseMapper.deleteClazzResource(clazzId);
        courseMapper.deleteClazzMember(clazzId);
        courseMapper.deleteClazzNotice(clazzId);
        if(courseMapper.dissolveCourse(clazzId) >= 1) {
            return new RequestResult("解散班级成功", StatusCode.SUCCESS);
        } else {
            return new RequestResult("解散班级失败", StatusCode.ERROR);
        }
    }
}
