package com.yu.huobi.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: Gosin
 * @Date: 2019/6/18 22:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleTaskTest {

    @Autowired
    ScheduleTask mScheduleTask;

    @Test
    public void subSymbol() {
        mScheduleTask.subSymbol();
    }
}