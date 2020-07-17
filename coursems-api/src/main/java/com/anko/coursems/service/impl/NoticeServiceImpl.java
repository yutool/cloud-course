package com.anko.coursems.service.impl;

import com.anko.coursems.core.BaseService;
import com.anko.coursems.dao.NoticeMapper;
import com.anko.coursems.entity.Notice;
import com.anko.coursems.service.NoticeService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class NoticeServiceImpl extends BaseService<Notice> implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public Notice addNotice(Notice notice) {
        notice.setNoticeId(RandomStringUtils.randomAlphanumeric(20));
        notice.setReleaseTime(new Date());
        noticeMapper.insert(notice);
        return notice;
    }

    @Override
    public int deleteNotice(String id) {
        return noticeMapper.deleteById(id);
    }

}
