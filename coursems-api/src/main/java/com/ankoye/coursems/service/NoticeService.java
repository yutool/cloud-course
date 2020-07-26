package com.ankoye.coursems.service;

import com.ankoye.coursems.common.support.IService;
import com.ankoye.coursems.entity.Notice;

public interface NoticeService extends IService<Notice> {

    /**
     * 添加班级通知
     */
    Notice addNotice(Notice notice);

}
