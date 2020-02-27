package com.anko.coursems.model;

import com.anko.coursems.common.utils.FileUrlUtils;
import com.anko.coursems.entity.Resource;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
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
        return FileUrlUtils.toDownloadUrl(downLink);
    }

    public Resource convertToResource(){
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
            ResourceDto resourceDto = new ResourceDto();
            BeanUtils.copyProperties(resource, resourceDto);
            return resourceDto;
        }

    }
}
