package com.anko.coursems.controller;

import com.anko.coursems.CoursemsApplication;
import com.anko.coursems.common.utils.JsonUtils;
import com.anko.coursems.controller.NoticeController;
import com.anko.coursems.controller.UserController;
import com.anko.coursems.entity.Notice;
import com.anko.coursems.entity.User;
import com.anko.coursems.model.LoginForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
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
public class NoticeControllerTests {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

//    @Test
//    public void get() throws Exception {
//        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders
//                .get("/api/v1/1PUQx4zeqoYgHSHE/courses")
//                .contentType(MediaType.APPLICATION_JSON)
//        );
//
//        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
//        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }

    @Test
    public void testSuccess() throws Exception {
        Notice notice = new Notice();
        notice.setCourseId("0b2GIyTjDBLT7mJX");
        notice.setAuthor("abc");
        notice.setContent("消息内容"); // 需要的参数

        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders
                .post("/api/v1/notices") // 请求方式post和地址/api/v1/notices
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.toString(notice))
        );

        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testError() throws Exception {
        Notice notice = new Notice();
        notice.setCourseId("0b2GIyTjDBLT7mJX");
        notice.setAuthor("abc");
        notice.setContent("");  // 测试下空字符串行不行

        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders
                .post("/api/v1/notices") // 请求方式post和地址/api/v1/notices
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.toString(notice))
        );

        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
