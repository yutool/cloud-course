package com.anko.coursems.service;

import com.anko.coursems.entity.Member;
import com.anko.coursems.entity.Notice;
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
public class ClazzServiceTests {

    @Autowired
    private INoticeService noticeService;
    @Autowired
    private IMemberService memberService;

    private BufferedWriter nWriter;
    private BufferedWriter mWriter;
    private BufferedWriter dnWriter;
    private BufferedWriter dmWriter;

    private int successNum = 0;
    private int failNum = 0;

    @Before
    public void init() throws Exception {
        File file1 = new File("D:\\test\\noticeTestResult.txt");
        if (!file1.exists()) {
            file1.createNewFile();
        }
        nWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file1)));

        File file2 = new File("D:\\test\\gradeTestResult.txt");
        if (!file2.exists()) {
            file2.createNewFile();
        }
        mWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2)));

        File fil3 = new File("D:\\test\\dnTestResult.txt");
        if (!fil3.exists()) {
            fil3.createNewFile();
        }
        dnWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fil3)));

        File fil4 = new File("D:\\test\\dmTestResult.txt");
        if (!fil4.exists()) {
            fil4.createNewFile();
        }
        dmWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fil4)));
    }

    @After
    public void destroy() throws IOException {
        nWriter.close();
        mWriter.close();
        dnWriter.close();
        dmWriter.close();
    }

    @Test
    public void testAddNotice() throws Exception {
        successNum = failNum = 0;
        String path = "D:\\test\\noticeInput.txt";
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] c = formatDate(line);
                String reality = "success";
                try {
                    Notice notice = new Notice();
                    notice.setCourseId(c[1]);
                    notice.setAuthor(c[2]);
                    notice.setContent(c[3]);
                    notice.setType(0);
                    noticeService.addNotice(notice);
                } catch (Exception e) {
                    reality = "fail";
                }
                output(c[0], c[4], reality, dnWriter);
            }
            dnWriter.write("Totally " + (successNum+failNum) + " test cases run, " +
                    successNum + " SUCCESS, " + failNum + " Fail.");
        }
    }


    @Test
    public void testGradeStudent() throws Exception {
        successNum = failNum = 0;
        String path = "D:\\test\\gradeInput.txt";
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
                    member.setUser(user);
                    member.setScore(Integer.parseInt(c[3]));
                    member.setRemark(c[4]);
                    memberService.gradeStudent(member);
                } catch (Exception e) {
                    reality = "fail";
                }
                output(c[0], c[5], reality, mWriter);
            }
            mWriter.write("Totally " + (successNum+failNum) + " test cases run, " +
                    successNum + " SUCCESS, " + failNum + " Fail.");
        }
    }

    @Test
    public void testDeleteNotice() throws Exception {
        successNum = failNum = 0;
        String path = "D:\\test\\dnInput.txt";
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] c = formatDate(line);
                String reality = "success";
                try {
                    if(noticeService.deleteNotice(c[1]) == 0) {
                        reality = "fail";
                    }
                } catch (Exception e) {
                    reality = "fail";
                }
                output(c[0], c[2], reality, dnWriter);
            }
            dnWriter.write("Totally " + (successNum+failNum) + " test cases run, " +
                    successNum + " SUCCESS, " + failNum + " Fail.");
        }
    }

    @Test
    public void testDeleteMember() throws Exception {
        successNum = failNum = 0;
        String path = "D:\\test\\dmInput.txt";
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] c = formatDate(line);
                String reality = "success";
                try {
                    Member member = new Member();
                    member.setCourseId(c[1]);
                    User user = new User();
                    user.setUserId(c[2]);
                    member.setUser(user);
                    if(memberService.deleteMember(member) == 0 ) {
                        reality = "fail";
                    }
                } catch (Exception e) {
                    reality = "fail";
                }
                output(c[0], c[3], reality, dmWriter);
            }
            dmWriter.write("Totally " + (successNum+failNum) + " test cases run, " +
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
