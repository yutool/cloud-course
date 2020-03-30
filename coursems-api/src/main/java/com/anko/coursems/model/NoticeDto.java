package com.anko.coursems.model;

import com.anko.coursems.entity.Notice;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
public class NoticeDto {
    private String noticeId;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date releaseTime;
    private String author;
    private int type;
    private String clazzId;

    public Notice convertToNotice(){
        NoticeConverter noticeConverter = new NoticeConverter();
        Notice notice = noticeConverter.convert(this);
        return notice;
    }

    public NoticeDto convertFor(Notice notice){
        NoticeConverter noticeConverter = new NoticeConverter();
        NoticeDto noticeDto = noticeConverter.reverse().convert(notice);
        return noticeDto;
    }


    private static class NoticeConverter extends Converter<NoticeDto, Notice> {
        @Override
        protected Notice doForward(NoticeDto noticeDto) {
            Notice notice = new Notice();
            // 使用BeanCopier也可以
            BeanUtils.copyProperties(noticeDto, notice);
            return notice;
        }

        @Override
        protected NoticeDto doBackward(Notice notice) {
            NoticeDto noticeDto = new NoticeDto();
            BeanUtils.copyProperties(notice, noticeDto);
            return noticeDto;
        }

    }
}
