package com.ankoye.coursems.model;

import com.ankoye.coursems.common.util.FileUrlUtils;
import com.ankoye.coursems.entity.Resource;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
public class ResourceDTO {
    private String resId;

    private String resName;

    private Long resSize;

    private String downLink;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uploadTime;

    private int experience;

    private String courseId;

    // 获取服务器下载地址
    public String getDownLink() {
        return FileUrlUtils.toDownloadUrl(downLink);
    }

    public Resource convertToResource(){
        ResourceConverter converter = new ResourceConverter();
        Resource resource = converter.convert(this);
        return resource;
    }

    public ResourceDTO convertFor(Resource resource){
        ResourceConverter converter = new ResourceConverter();
        ResourceDTO resourceDto = converter.reverse().convert(resource);
        return resourceDto;
    }


    private static class ResourceConverter extends Converter<ResourceDTO, Resource> {
        @Override
        protected Resource doForward(ResourceDTO resourceDto) {
            Resource resource = new Resource();
            // 使用BeanCopier也可以
            BeanUtils.copyProperties(resourceDto, resource);
            return resource;
        }

        @Override
        protected ResourceDTO doBackward(Resource resource) {
            ResourceDTO resourceDto = new ResourceDTO();
            BeanUtils.copyProperties(resource, resourceDto);
            return resourceDto;
        }

    }
}
