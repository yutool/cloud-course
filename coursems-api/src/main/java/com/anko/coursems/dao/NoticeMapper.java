package com.anko.coursems.dao;

import com.anko.coursems.entity.Notice;
import org.springframework.stereotype.Repository;


@Repository
public interface NoticeMapper {

    int add(Notice noticeForm);

    int delete(String noticeId);

    int deleteNoticeByCourseId(String courseId);
}
