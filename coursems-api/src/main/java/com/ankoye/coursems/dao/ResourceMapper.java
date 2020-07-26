package com.ankoye.coursems.dao;

import com.ankoye.coursems.common.support.BaseMapper;
import com.ankoye.coursems.entity.Resource;
import org.springframework.stereotype.Repository;

@Repository
@BaseMapper.Meta(table = "t_resource", exId = "res_id")
public interface ResourceMapper extends BaseMapper<Resource> {

//    @Select("delete from t_resource" +
//            " where course_id = #{courseId}")
//    int deleteResourcesByCourseId(String courseId);
//
//    @Insert("insert into t_resource(res_id, res_name, down_link, upload_time, res_size, experience, course_id)" +
//            " values(#{resId}, #{resName}, #{downLink}, #{uploadTime}, #{resSize}, #{experience}, #{courseId})")
//    int add(Resource resourceForm);
//
//    @Delete("delete from t_resource" +
//            " where res_id = #{redId}")
//    int delete(String resId);

}
