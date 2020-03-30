package com.anko.coursems;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.rmi.runtime.Log;

import java.util.logging.Logger;

@SpringBootTest
class CoursemsApplicationTests {
    Logger logger = Logger.getLogger("CoursemsApplicationTests");
    @Test
    void contextLoads() {
    }
    @Test
    void testa() {
        String filename = RandomStringUtils.randomAlphanumeric(10);
        logger.info(filename);
    }

}
