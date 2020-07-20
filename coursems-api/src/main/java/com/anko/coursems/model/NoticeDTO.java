package com.anko.coursems.model;

import com.anko.coursems.entity.Notice;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
public class NoticeDTO {
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

    public NoticeDTO convertFor(Notice notice){
        NoticeConverter noticeConverter = new NoticeConverter();
        NoticeDTO noticeDto = noticeConverter.reverse().convert(notice);
        return noticeDto;
    }


    private static class NoticeConverter extends Converter<NoticeDTO, Notice> {
        @Override
        protected Notice doForward(NoticeDTO noticeDto) {
            Notice notice = new Notice();
            // 使用BeanCopier也可以
            BeanUtils.copyProperties(noticeDto, notice);
            return notice;
        }

        @Override
        protected NoticeDTO doBackward(Notice notice) {
            NoticeDTO noticeDto = new NoticeDTO();
            BeanUtils.copyProperties(notice, noticeDto);
            return noticeDto;
        }

    }
}
