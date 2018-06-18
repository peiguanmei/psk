package com.p.psk.service;

import com.p.psk.dao.UserDao;
import com.p.psk.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getUserId(long id) {
        return userDao.getById(id);
    }
}
