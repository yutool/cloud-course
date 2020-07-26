package com.ankoye.coursems.controller;

import com.ankoye.coursems.common.util.JsonUtils;
import com.ankoye.coursems.model.LoginForm;
import com.ankoye.coursems.model.RegisterForm;
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
public class AccountControllerTests {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Resource
    private org.apache.shiro.mgt.SecurityManager securityManager;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        ThreadContext.bind(securityManager);
    }

    @Test
    public void testRegisterSuccess() throws Exception {
        RegisterForm register = new RegisterForm();
        register.setEmail("abc@qq.com");
        register.setPassword("123456");
        register.setUserName("abc");
        register.setVerifyCode("123456");

        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders
                .post("/api/v1/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.toString(register))
        );

        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testRegisterError() throws Exception{
        RegisterForm register = new RegisterForm();
        register.setEmail("");
        register.setPassword("");
        register.setUserName("");
        register.setVerifyCode("");

        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders
                .post("/api/v1/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.toString(register))
        );

        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testLoginSuccess() throws Exception {
        LoginForm login = new LoginForm();
        login.setAccount("user@qq.com");
        login.setPassword("123456");

        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders
                .post("/api/v1/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.toString(login))
        );

        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testLoginError() throws Exception{
        LoginForm login = new LoginForm();
        login.setAccount("");
        login.setPassword("123456");

        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders
                .post("/api/v1/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.toString(login))
        );

        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testResetPwdSuccess() throws Exception {
        RegisterForm register = new RegisterForm();
        register.setEmail("abc@qq.com");
        register.setPassword("123456");
        register.setUserName("abc");

        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders
                .put("/api/v1/reset-password")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.toString(register))
        );

        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testResetPwdError() throws Exception {
        RegisterForm register = new RegisterForm();
        register.setEmail("");
        register.setPassword("");
        register.setUserName("");

        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders
                .put("/api/v1/reset-password")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.toString(register))
        );

        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
