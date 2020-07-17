package com.anko.coursems.service;

import com.anko.coursems.entity.Course;
import com.anko.coursems.entity.Member;
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

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class CourseServiceTests {

    @Autowired
    private CourseService courseService;
    @Autowired
    private MemberService memberService;

    private BufferedWriter cWriter;
    private BufferedWriter mWriter;

    private int successNum = 0;
    private int failNum = 0;

    @Before
    public void init() throws Exception {
        File file1 = new File("D:\\test\\createTestResult.txt");
        if (!file1.exists()) {
            file1.createNewFile();
        }
        cWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file1)));

        File file2 = new File("D:\\test\\addTestResult.txt");
        if (!file2.exists()) {
            file2.createNewFile();
        }
        mWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2)));
    }

    @After
    public void destroy() throws IOException {
        cWriter.close();
        mWriter.close();
    }

    @Test
    public void testCreateCourse() throws Exception {
        successNum = failNum = 0;
        String path = "D:\\test\\createInput.txt";
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] c = formatDate(line);
                String reality = "success";
                try {
                    Course course = new Course();
                    course.setClazzName(c[1]);
                    course.setCourseName(c[2]);
                    course.setTerm(c[3]);
                    course.setSynopsis(c[4]);
                    User user = new User();
                    user.setUserId(c[5]);
                    courseService.createCourse(course);
                } catch (Exception e) {
                    reality = "fail";
                }
                output(c[0], c[6], reality, cWriter);
            }
            cWriter.write("Totally " + (successNum+failNum) + " test cases run, " +
                    successNum + " SUCCESS, " + failNum + " Fail.");
        }
    }

    @Test
    public void testAddMember() throws Exception {
        successNum = failNum = 0;
        String path = "D:\\test\\addInput.txt";
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] c = formatDate(line);
                String reality = "success";
                try {
                    Member member = new Member();
                    User user = new User();
                    member.setCourseId(c[1]);
                    user.setUserId(c[2]);
                    memberService.addMember(member);
                } catch (Exception e) {
                    reality = "fail";
                }
                output(c[0], c[3], reality, mWriter);
            }
            mWriter.write("Totally " + (successNum+failNum) + " test cases run, " +
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
