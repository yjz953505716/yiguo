package com.qfedu.yiguo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
@RunWith(SpringRunner.class)
@SpringBootTest
public class YiguoApplicationTests {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void contextLoads() {
    }

}
