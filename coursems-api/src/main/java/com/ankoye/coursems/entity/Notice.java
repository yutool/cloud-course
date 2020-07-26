package com.ankoye.coursems.entity;

import com.ankoye.coursems.common.support.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author ankoye@qq.com
 */
@Data
@Accessors(chain = true)
public class Notice extends BaseEntity {
//    private static final long serialVersionUID = 3044656695007853904L;

    private String noticeId;

    @Length(min=1, max=100, message = "消息内容长度在1-100范围内")
    private String content;

    @NotNull
    private String author;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date releaseTime;

    private Integer type;

    @NotNull
    private String courseId;
}
