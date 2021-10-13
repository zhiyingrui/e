package com.kaikeba.dao.imp;

import com.kaikeba.bean.Express;
import com.kaikeba.dao.BaseExpressDao;
import com.kaikeba.exception.DuplicateCode;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ExpressDaoMysqlTest {
    BaseExpressDao dao= new ExpressDaoMysql();

    @Test
    public void console() {
        List<Map<String, Integer>> console = dao.console();
        System.out.println(console);
    }

    @Test
    public void findAll() {
        List<Express> all = dao.findAll(false,0,0);
        System.out.println(all);
    }

    @Test
    public void findByNumber() {
        Express e = dao.findByNumber("122");
        System.out.println(e);
    }

    @Test
    public void findByCode() {
        Express byCode = dao.findByCode("666666");
        System.out.println(byCode);
    }

    @Test
    public void findByUserPhone() {
        List<Express> express = dao.findByUserPhone("13843838438");
        System.out.println(express);
    }

    @Test
    public void findBySysPhone() {
        List<Express> bySysPhone = dao.findBySysPhone("1888888");
        System.out.println(bySysPhone);
    }

    @Test
    public void insert() {


        Express express = new Express("451","支","13076218343","大大快递","188122222","666668");
        boolean insert = false;
        try {
            insert = dao.insert(express);
        } catch (DuplicateCode e) {
            System.out.println("取件码重复");
        }
        System.out.println(insert);
    }


    @Test
    public void update() {
        Express express = new Express();
        express.setNumber("666");
        express.setCompany("哈哈快递");
        express.setUsername("王二");
        express.setStatus(1);
        boolean update = dao.update(3, express);
        System.out.println(update);
    }

    @Test
    public void updateStatus() {
        boolean b = dao.updateStatus("666666");
        System.out.println(b);
    }

    @Test
    public void delete() {
        boolean delete = dao.delete(1);
        System.out.println(delete);
    }
}