package com.anko.coursems.service;

import com.anko.coursems.common.support.IService;
import com.anko.coursems.entity.Notice;

public interface NoticeService extends IService<Notice> {

    /**
     * 添加班级通知
     */
    Notice addNotice(Notice notice);

}
