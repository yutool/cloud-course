package com.anko.coursems.model;

import com.anko.coursems.entity.Resource;
import com.anko.coursems.entity.UserInfo;
import com.google.common.base.Converter;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Date;

@Data
@Builder
public class ResourceDto {
    private String resId;
    private String resName;
    private String downLink;
    private Date uploadTime;
    private String resSize;
    private int experience;
    private String clazzId;

    // 获取服务器下载地址
    public String getDownLink() {
        if(downLink.startsWith("http"))
            return downLink;
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("files/"+downLink).toUriString();
    }

    public Resource convertToUser(){
        ResourceConverter converter = new ResourceConverter();
        Resource resource = converter.convert(this);
        return resource;
    }

    public ResourceDto convertFor(Resource resource){
        ResourceConverter converter = new ResourceConverter();
        ResourceDto resourceDto = converter.reverse().convert(resource);
        return resourceDto;
    }


    private static class ResourceConverter extends Converter<ResourceDto, Resource> {
        @Override
        protected Resource doForward(ResourceDto resourceDto) {
            Resource resource = new Resource();
            // 使用BeanCopier也可以
            BeanUtils.copyProperties(resourceDto, resource);
            return resource;
        }

        @Override
        protected ResourceDto doBackward(Resource resource) {
            ResourceDto resourceDto = ResourceDto.builder().build();
            BeanUtils.copyProperties(resource, resourceDto);
            return resourceDto;
        }

    }
}
