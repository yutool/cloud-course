package com.ankoye.coursems.service.impl;

import com.ankoye.coursems.common.support.BaseService;
import com.ankoye.coursems.dao.NoticeMapper;
import com.ankoye.coursems.entity.Notice;
import com.ankoye.coursems.service.NoticeService;
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
}
