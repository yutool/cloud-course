package com.anko.coursems.service;

import com.anko.coursems.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class UserServiceTests {

    @Autowired
    private UserService userService;

    private BufferedWriter eWriter;
    private BufferedWriter pWriter;
    private BufferedWriter iWriter;

    private int successNum = 0;
    private int failNum = 0;

    @Before
    public void init() throws Exception {
        File file1 = new File("D:\\test\\emailTestResult.txt");
        if (!file1.exists()) {
            file1.createNewFile();
        }
        eWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file1)));

        File file2 = new File("D:\\test\\pwdTestResult.txt");
        if (!file2.exists()) {
            file2.createNewFile();
        }
        pWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2)));

        File file3 = new File("D:\\test\\infoTestResult.txt");
        if (!file3.exists()) {
            file3.createNewFile();
        }
        iWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file3)));
    }

    @After
    public void destroy() throws IOException {
        eWriter.close();
        pWriter.close();
        iWriter.close();
    }

    @Test
    public void testUpdateEmail() throws Exception {
        successNum = failNum = 0;
        String path = "D:\\test\\emailInput.txt";
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] c = formatDate(line);
                String reality = "success";
                try {
                    User user = new User();
                    user.setUserId(c[1]);
                    user.setEmail(c[2]);
                    if (userService.bindEmail(user) == null)
                        reality = "fail";
                } catch (Exception e) {
                    reality = "fail";
                }
                output(c[0], c[3], reality, eWriter);
            }
            eWriter.write("Totally " + (successNum+failNum) + " test cases run, " +
                    successNum + " SUCCESS, " + failNum + " Fail.");
        }
    }

    @Test
    public void testUpdatePassword() throws Exception {
        successNum = failNum = 0;
        String path = "D:\\test\\pwdInput.txt";
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] c = formatDate(line);
                String reality = "success";
                try {
                    User user = new User();
                    user.setUserId(c[1]);
                    user.setPassword(c[2]);
                    if(userService.updatePassword(user) == 0)
                        reality = "fail";
                } catch (Exception e) {
                    reality = "fail";
                }
                output(c[0], c[3], reality, pWriter);
            }
            pWriter.write("Totally " + (successNum+failNum) + " test cases run, " +
                    successNum + " SUCCESS, " + failNum + " Fail.");
        }
    }

    @Test
    public void testUpdateUserInfo() throws Exception {
        successNum = failNum = 0;
        String path = "D:\\test\\infoInput.txt";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] c = formatDate(line);
                String reality = "success";
                try {
                    User user = new User();
                    user.setUserId(c[1]);
                    user.setUserName(c[2]);
                    user.setNickname(c[3]);
                    user.setStuNum(c[4]);
                    user.setGender(c[5]);
                    user.setBirthday(format.parse(c[6]));
                    user.setSignature(c[7]);
                    userService.updateUserInfo(user);
                } catch (Exception e) {
                    reality = "fail";
                }
                output(c[0], c[8], reality, iWriter);
            }
            iWriter.write("Totally " + (successNum+failNum) + " test cases run, " +
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
