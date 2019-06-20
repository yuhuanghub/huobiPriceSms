package com.yu.huobi.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Gosin
 * @Date: 2019/6/15 19:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MD5UtilsTest {

    @Test
    public void md5Encode() {
        String pw = "yuhuang";
        String utf8 = MD5Utils.md5Encode(pw, "utf8");
        System.out.println(utf8);
    }
}