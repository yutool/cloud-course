package com.ankoye.coursems.controller;

import com.ankoye.coursems.common.util.JsonUtils;
import com.ankoye.coursems.entity.User;
import org.apache.shiro.util.ThreadContext;
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

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebAppConfiguration
public class UserControllerTests {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Resource
    org.apache.shiro.mgt.SecurityManager securityManager;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        ThreadContext.bind(securityManager);

    }

    @Test
    public void testSuccess() throws Exception {
        User user = new User();   // new 的类
        user.setUserId("0b2GIyTjDBLT7mJX");
        user.setPassword("123456789");// 需要的参数

        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders
                .put("/api/v1/users/password") // 请求方式put和地址/api/v1/users/password
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.toString(user))
        );

        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testError() throws Exception {
        User user = new User();   // new 的类
        user.setUserId("0b2GIyTjDBLT7mJX");
        user.setPassword("");// 需要的参数

        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders
                .put("/api/v1/users/password") // 请求方式put和地址/api/v1/users/password
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.toString(user))
        );

        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}
