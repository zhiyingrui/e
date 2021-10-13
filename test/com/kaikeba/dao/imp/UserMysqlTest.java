package com.kaikeba.dao.imp;

import com.kaikeba.bean.User;
import com.kaikeba.dao.BaseUserDao;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserMysqlTest {
    BaseUserDao dao  = new UserDaoMysql();
    @Test
    public void console() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findByName() {
    }

    @Test
    public void findByIdNumber() {
    }

    @Test
    public void findByUserPhone() {
    }

    @Test
    public void insert() {
        User user = new User("2","2","2","2");
        boolean insert = dao.insert(user);
        System.out.println(insert);
    }

    @Test
    public void update() {
    }

    @Test
    public void updateLoginTime() {
    }

    @Test
    public void delete() {

    }
}