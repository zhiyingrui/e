package com.kaikeba.dao.imp;

import com.kaikeba.dao.BaseAdminDao;
import com.kaikeba.util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class AdminDaoMysql implements BaseAdminDao {
    private static final String SQL_UPDATE_LOGIN_TIME = "update eadmin set logintime=?,loginip=? where username=?";
    private static final String SQL_LOGIN = "select id from eadmin where username=? and password=?";

    /**
     * 根据用户名，更新时间和用户ip
     *
     * @param username
     * @param date
     * @param ip
     */
    @Override
    public void updateLoginTime(String username, Date date, String ip) {
        //1.获取链接
        Connection connection = DruidUtil.getConnection();
        PreparedStatement preparedStatement = null;
        //2.获取预编译sql语句
        try {
             preparedStatement = connection.prepareStatement(SQL_UPDATE_LOGIN_TIME);
             //3 填充参数
             preparedStatement.setDate(1,new java.sql.Date(date.getTime()));
             preparedStatement.setString(2,ip);
             preparedStatement.setString(3,username);
             //4执行
             preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(connection,preparedStatement,null);
        }
    }

    /**
     * 管理元根据账号密码登陆
     *
     * @param username 账号
     * @param password 密码
     * @return true 表示成功
     */
    @Override
    public boolean login(String username, String password) {
        //1.获取链接
        Connection connection = DruidUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //2.获取预编译sql语句
        try {
            preparedStatement = connection.prepareStatement(SQL_LOGIN);
            //3 填充参数
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            //4执行
            resultSet = preparedStatement.executeQuery();
            //5.根据查询结果，返回

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(connection,preparedStatement,resultSet) ;
        }
        return false;
    }
}
