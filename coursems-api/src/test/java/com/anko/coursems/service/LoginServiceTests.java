package com.anko.coursems.service;

import com.anko.coursems.entity.User;
import com.anko.coursems.model.LoginForm;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class LoginServiceTests {
    @Autowired
    private UserService userService;

    private BufferedWriter lWriter;
    private BufferedWriter rWriter;
    private BufferedWriter rpWriter;

    private int successNum = 0;
    private int failNum = 0;

    @Before
    public void init() throws Exception {
        File Register = new File("D:\\test\\registerOutput.txt");
        if (!Register.exists()) {
            Register.createNewFile();
        }
        rWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Register)));

        File loginFile = new File("D:\\test\\loginOutput.txt");
        if (!loginFile.exists()) {
            loginFile.createNewFile();
        }
        lWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(loginFile)));

        File ResetPassword = new File("D:\\test\\resetPasswordOutput.txt");
        if (!ResetPassword.exists()) {
            ResetPassword.createNewFile();
        }
        rpWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ResetPassword)));
    }

    @After
    public void destroy() throws IOException {
        rWriter.close();
        lWriter.close();
        rpWriter.close();
    }

    @Test
    public void testRegister() throws Exception {
        successNum = failNum = 0;
        String path = "D:\\test\\registerInput.txt";
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] c = formatDate(line);
                String reality = "success";
                try {
                    User user = new User();
                    user.setUserName(c[1]);
                    user.setEmail(c[2]);
                    user.setPassword(c[3]);
                    userService.register(user);
                } catch (Exception e) {
                    reality = "fail";
                }
                output(c[0], c[4], reality, rWriter);
            }
            rWriter.write("Totally " + (successNum+failNum) + " test cases run, " +
                    successNum + " SUCCESS, " + failNum + " Fail.");
        }
    }

    @Test
    public void testLogin() throws Exception {
        successNum = failNum = 0;
        String path = "D:\\test\\loginInput.txt";
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] c = formatDate(line);
                String reality = "success";
                try {
                    LoginForm user = new LoginForm();
                    user.setAccount(c[1]);
                    user.setPassword(c[2]);
                    if (userService.login(user) == null) {
                        reality = "fail";
                    }
                } catch (Exception e) {
                    reality = "fail";
                }
                output(c[0], c[3], reality, lWriter);
            }
            lWriter.write("Totally " + (successNum+failNum) + " test cases run, " +
                    successNum + " SUCCESS, " + failNum + " Fail.");
        }
    }

    @Test
    public void testResetPassword() throws Exception {
        successNum = failNum = 0;
        String path = "D:\\test\\resetPasswordInput.txt";
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] c = formatDate(line);
                String reality = "success";
                try {
                    User user = new User();
                    user.setEmail(c[1]);
                    user.setPassword(c[2]);
                    if(userService.resetPassword(user) == 0)
                        reality = "fail";
                } catch (Exception e) {
                    reality = "fail";
                }
                output(c[0], c[3], reality, rpWriter);
            }
            rpWriter.write("Totally " + (successNum+failNum) + " test cases run, " +
                    successNum + " SUCCESS, " + failNum + " Fail.");
        }
    }

    /**
     * @param num 序号
     * @param expect 预期结果
     * @param reality 实际结果
     */
    private void output(String num, String expect, String reality, BufferedWriter bw) throws IOException {
        String str;
        if (expect.toUpperCase().equals(reality.toUpperCase())) {
            str = "TestCase " + num + ", SUCCESS";
            successNum++;
        } else {
            str = "TestCase " + num + ", FAIL";
            failNum++;
        }
        bw.write(str);
        bw.newLine();
        log.info(str);
    }

    private String[] formatDate(String str) {
        String[] res = str.split(" ");
        for (int i = 0; i < res.length; i++) {
            if ("null".equals(res[i])) {
                res[i] = null;
            }
        }
        return res;
    }
}
