package com.anko.coursems.service.impl;

import com.anko.coursems.common.result.Result;
import com.anko.coursems.common.result.ResultCode;
import com.anko.coursems.dao.CourseMapper;
import com.anko.coursems.entity.ClazzInfo;
import com.anko.coursems.entity.ClazzMember;
import com.anko.coursems.model.ClazzDetail;
import com.anko.coursems.model.UserCourse;
import com.anko.coursems.service.ICourseService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService implements ICourseService {
    @Autowired
    private CourseMapper courseMapper;

    public UserCourse getAllCourse(String userId) {
        List<ClazzDetail> joinCourse = courseMapper.getJoinCourses(userId);
        List<ClazzDetail> createCourse = courseMapper.getCreateCourses(userId);
        return new UserCourse(joinCourse, createCourse);
    }

    public ResultCode createCourse(ClazzInfo clazzInfo) {
        String clazzId = "c" + RandomStringUtils.randomAlphanumeric(19);
        clazzInfo.setClazzId(clazzId);
        String clazzNum = RandomStringUtils.randomNumeric(6);
        clazzInfo.setClazzNum(clazzNum);
        int res = courseMapper.createCourse(clazzInfo);
        if(res < 1) {
            return ResultCode.SYSTEM_INNER_ERROR;
        }
        return ResultCode.SUCCESS;
    }

    public ClazzDetail searchCourse(String clazzNum) {
        return courseMapper.searchCourse(clazzNum);
    }

    public ResultCode joinCourse(ClazzMember joinForm) {
        if (courseMapper.findClazzMemberById(joinForm) != null) {
            return ResultCode.DATA_ALREADY_EXISTED;
        }
        int res = courseMapper.joinCourse(joinForm);
        if(res < 1) {
            return ResultCode.SYSTEM_INNER_ERROR;
        }
        return ResultCode.SUCCESS;
    }

    public ResultCode quitCourse(ClazzMember clazzMember) {
        int res = courseMapper.quitCourse(clazzMember);
        if(res < 1) {
            return ResultCode.SYSTEM_INNER_ERROR;
        }
        return ResultCode.SUCCESS;
    }
    // 解散班级
    @Transactional
    public ResultCode dissolveCourse(String clazzId) {
        courseMapper.deleteClazzResource(clazzId);
        courseMapper.deleteClazzMember(clazzId);
        courseMapper.deleteClazzNotice(clazzId);
        if(courseMapper.dissolveCourse(clazzId) < 1) {
            return ResultCode.SYSTEM_INNER_ERROR;
        }
        return ResultCode.SUCCESS;
    }
}
