package com.anko.coursems.controller;

import com.anko.coursems.common.utils.JsonUtils;
import com.anko.coursems.model.CourseDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class CourseControllerTests {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testSuccess() throws Exception {
        CourseDto course = new CourseDto();   // new 的类
        course.setTeacherId("0b2GIyTjDBLT7mJX");
        course.setClazzName("计科1班");
        course.setCourseName("软件测试");// 需要的参数
        course.setTerm("2019-2020");
        course.setSynopsis("班级描述");


        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders
                .post("/api/v1/courses") // 请求方式post和地址/api/v1/courses
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.toString(course))
        );

        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void testError() throws Exception {
        CourseDto course = new CourseDto();   // new 的类
        course.setTeacherId("");
        course.setClazzName("");
        course.setCourseName("");// 需要的参数
        course.setTerm("");
        course.setSynopsis("");


        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders
                .post("/api/v1/courses") // 请求方式post和地址/api/v1/courses
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.toString(course))
        );

        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}
