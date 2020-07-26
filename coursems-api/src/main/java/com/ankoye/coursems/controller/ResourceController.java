package com.ankoye.coursems.controller;

import com.ankoye.coursems.common.annotation.LogAnnotation;
import com.ankoye.coursems.common.result.Result;
import com.ankoye.coursems.common.result.ResultCode;
import com.ankoye.coursems.common.util.JsonUtils;
import com.ankoye.coursems.common.support.BaseController;
import com.ankoye.coursems.entity.Resource;
import com.ankoye.coursems.model.ResourceDTO;
import com.ankoye.coursems.service.ResourceService;
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
        return handleResult(new ResourceDTO().convertFor(resource));
    }

    @ApiOperation(value = "删除资源")
    @LogAnnotation(operation = "删除资源")
    @DeleteMapping("/{id}")
    public Result deleteResource(@PathVariable String id) {
        return handleResult(resourceService.deleteByExId(id));
    }
}
