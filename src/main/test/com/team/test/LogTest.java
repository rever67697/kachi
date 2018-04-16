package com.team.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 下午3:45 2018/4/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogTest {

    private Logger logger = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void testLog(){

        logger.debug("dubug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");

    }

}
