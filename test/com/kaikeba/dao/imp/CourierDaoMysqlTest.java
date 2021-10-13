package com.kaikeba.dao.imp;

import com.kaikeba.bean.Courier;
import com.kaikeba.dao.BaseCourierDao;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CourierDaoMysqlTest {
    BaseCourierDao dao = new CourierDaoMysql();

    @Test
    public void findAll() {
        List<Courier> list = dao.findAll(false,0,0);
        System.out.println(list);
    }
    @Test
    public void findByNumber() {
        Courier courier = dao.findByIdNumber("1");
        System.out.println(courier);
    }
}