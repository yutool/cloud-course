package com.anko.coursems.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class Resource implements Serializable {
    private String resId;

    private String resName;

    private Long resSize;

    private String downLink;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uploadTime;

    private Integer experience;

    private String courseId;
}
