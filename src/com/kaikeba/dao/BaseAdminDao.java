package com.kaikeba.dao;

import java.util.Date;

/**
 * 用于定义eadmin表格操作规范
 */
public interface BaseAdminDao {
    /**
     * 根据用户名，更新时间和用户ip
     * @param username
     */
    void updateLoginTime(String username, Date data,String ip);

    /**
     * 管理元根据账号密码登陆
     * @param username 账号
     * @param password 密码
     * @return true 表示成功
     */
    boolean login(String username,String password);
}
