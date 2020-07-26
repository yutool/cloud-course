package com.ankoye.coursems.dao;

import com.ankoye.coursems.common.support.BaseMapper;
import com.ankoye.coursems.entity.Notice;
import org.springframework.stereotype.Repository;

@Repository
@BaseMapper.Meta(table = "t_notice", exId = "notice_id")
public interface NoticeMapper extends BaseMapper<Notice> {

//    @Delete("delete from t_notice" +
//            " where course_id = #{courseId}")
//    int deleteNoticesByCourseId(String courseId);
//
//    @Insert("insert into t_notice(notice_id, content, release_time, author, `type`, course_id)" +
//            " values(#{noticeId}, #{content}, #{releaseTime}, #{author}, #{type}, #{courseId})")
//    int add(Notice noticeForm);
//
//    @Delete("delete from t_notice" +
//            " where notice_id = #{noticeId}")
//    int delete(String noticeId);

}
