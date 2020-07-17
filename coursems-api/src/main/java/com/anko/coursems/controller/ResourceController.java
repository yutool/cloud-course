package com.anko.coursems.controller;

import com.anko.coursems.common.annotation.LogAnnotation;
import com.anko.coursems.common.result.Result;
import com.anko.coursems.common.result.ResultCode;
import com.anko.coursems.common.utils.JsonUtils;
import com.anko.coursems.core.BaseController;
import com.anko.coursems.entity.Resource;
import com.anko.coursems.model.ResourceDto;
import com.anko.coursems.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "班级资源管理")
@RestController
@RequestMapping("/api/v1/resources")
public class ResourceController extends BaseController {
    @Autowired
    private ResourceService resourceService;

    @ApiOperation(value = "添加资源")
    @LogAnnotation(operation = "添加资源")
    @PostMapping
    public Result addResource(@RequestParam("info") String resourceForm , @RequestParam("file") MultipartFile file) {
        Resource resource = JsonUtils.toBean(resourceForm, Resource.class);
        if(resource == null) {
            return Result.error(ResultCode.PARAM_IS_INVALID);
        }
        resource =  resourceService.addResource(resource, file);
        return Result.success(new ResourceDto().convertFor(resource));
    }

    @ApiOperation(value = "删除资源")
    @LogAnnotation(operation = "删除资源")
    @DeleteMapping("/{id}")
    public Result deleteResource(@PathVariable String id) {
        return handleResult(resourceService.deleteResource(id));
    }
}
