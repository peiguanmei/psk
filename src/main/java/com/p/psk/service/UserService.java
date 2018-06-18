package com.p.psk.service;

import com.p.psk.dao.UserDao;
import com.p.psk.entity.User;
import com.p.psk.redis.RedisService;
import com.p.psk.redis.UserKey;
import com.p.psk.utils.Md5Util;
import com.p.psk.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisService redisService;

    public User getUserId(long id) {
        User user = redisService.get(UserKey.getById, "" + id, User.class);
        if (user != null) {
            return user;
        }
        user = userDao.getById(id);
        if (user != null) {
            redisService.set(UserKey.getById, "" + id, user);
        }
        return user;
    }

    public String login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null) {
            throw new RuntimeException();
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号码是否存在
        User user = getUserId(Long.parseLong(mobile));
        if (user == null) {
            throw new RuntimeException();
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = Md5Util.formPassToDBPass(formPass, saltDB);
        if (!calcPass.equals(dbPass)) {
            throw new RuntimeException();
        }
        //生成唯一id作为token
        String token = UUID.randomUUID().toString().replace("-", "");
        return token;
    }
}
