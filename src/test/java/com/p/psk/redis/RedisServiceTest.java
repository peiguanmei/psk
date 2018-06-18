package com.p.psk.redis;

import com.p.psk.dao.UserDao;
import com.p.psk.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceTest {

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserDao userDao;

    @Test
    public void get() {
        long id = 18181818181L;
        User user = redisService.get(UserKey.getById, "" + id, User.class);
        System.out.println(user);
    }

    @Test
    public void set() {
        long id = 18181818181L;
        User user = redisService.get(UserKey.getById, "" + id, User.class);
        if (user != null) {
            System.out.println("no cache");
        }
        //取数据库
        user = userDao.getById(id);
        //再存入缓存
        if (user != null) {
            redisService.set(UserKey.getById, "" + id, user);
        }
    }
}