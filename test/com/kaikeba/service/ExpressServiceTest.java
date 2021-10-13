package com.kaikeba.service;

import com.kaikeba.bean.Express;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressServiceTest {

    @Test
    public void insert() {
        Express express = new Express("451111","支大","13076218343","无敌快递","188122222","666668");
        boolean insert = ExpressService.insert(express);
        System.out.println(insert);
    }
}