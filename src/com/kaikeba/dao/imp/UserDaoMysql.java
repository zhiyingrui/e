package com.kaikeba.dao.imp;

import com.kaikeba.bean.User;
import com.kaikeba.dao.BaseUserDao;
import com.kaikeba.util.DruidUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoMysql implements BaseUserDao {
    /**
     * 查询用户（总数+新增）
     */
    public static final String SQL_CONSOLE = "SELECT " +
            "COUNT(ID) data_size," +
            "COUNT(TO_DAYS(ENROLLTIME)=TO_DAYS(NOW()) OR NULL) data_day " +
            "FROM USER";
    /**
     * 查询数据库中所有的用户信息
     */
    public static final String SQL_FIND_ALL = "SELECT * FROM USER";
    /**
     * 分页查询数据库中所有用户信息
     */
    public static final String SQL_FIND_LIMIT = "SELECT * FROM USER LIMIT ?,?";
    /**
     * 通过用户名查询用户信息
     */
    public static final String SQL_FIND_BY_USER_NAME = "SELECT * FROM USER WHERE USERNAME=?";
    /**
     * 通过手机号查询用户信息
     */
    public static final String SQL_FIND_BY_USER_PHONE = "SELECT * FROM USER WHERE USERPHONE=?";

    public static final String SQL_FIND_BY_USER_ID_NUMBER = "SELECT * FROM USER WHERE USERIDNUMBER=?";
    /**
     * 录入用户
     */
    public static final String SQL_INSERT = "INSERT INTO USER(USERNAME,USERPHONE,USERIDNUMBER,USERPASSWORD,ENROLLTIME,LOGINTIME) VALUES(?,?,?,?,NOW(),NOW())";
    /**
     * 修改用户
     */
    public static final String SQL_UPDATE = "UPDATE USER SET USERNAME=?,USERPHONE=?,USERIDNUMBER=?,USERPASSWORD=? WHERE ID=?";
    /**
     * 修改用户登录时间
     */
    public static final String SQL_UPDATE_LOGIN_TIME = "UPDATE USER SET LOGINTIME=NOW() WHERE ID=?";
    /**
     * 用户的删除
     */
    public static final String SQL_DELETE = "DELETE FROM USER WHERE ID=?";


    /**
     * 用于查询数据库中的全部用户（总数，当日新增）
     *
     * @return [{size:总数, day:新增}]
     */
    @Override
    public List<Map<String, Integer>> console() {
        ArrayList<Map<String, Integer>> data = new ArrayList<>();
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            state = conn.prepareStatement(SQL_CONSOLE);
            result = state.executeQuery();
            if (result.next()){
                int data_size = result.getInt("data_size");
                int data_day = result.getInt("data_day");
                Map<String,Integer> data1 = new HashMap<>();
                data1.put("data_size",data_size);
                data1.put("data_day",data_day);
                data.add(data1);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,result);
        }
        return data;
    }

    /**
     * 查询所有用户
     *
     * @param limit      是否分页的标记，true：分页，false：查询所有
     * @param offset     sql语句的起始索引
     * @param pageNumber 每一页查询的数量
     * @return 用户的集合
     */
    @Override
    public List<User> findAll(boolean limit, int offset, int pageNumber) {
        ArrayList<User> data = new ArrayList<>();
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            if (limit){
                state = conn.prepareStatement(SQL_FIND_LIMIT);
                state.setInt(1,offset);
                state.setInt(2,pageNumber);
            }else {
                state = conn.prepareStatement(SQL_FIND_ALL);
            }
            result = state.executeQuery();

            while (result.next()){
                int id = result.getInt("id");
                String userName = result.getString("userName");
                String userPhone = result.getString("userPhone");
                String userIdNumber = result.getString("userIdNumber");
                String userPassword = result.getString("userPassword");
                Timestamp enrollTime = result.getTimestamp("enrollTime");
                Timestamp loginTime = result.getTimestamp("loginTime");

                User u = new User(id, userName, userPhone, userIdNumber, userPassword, enrollTime, loginTime);
                data.add(u);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,result);
        }
        return data;
    }

    /**
     * 根据用户名查询用户
     *
     * @param userName 用户名
     * @return
     */
    @Override
    public List<User> findByName(String userName) {
        ArrayList<User> data = new ArrayList<>();
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            state = conn.prepareStatement(SQL_FIND_BY_USER_NAME);
            state.setString(1,userName);
            result = state.executeQuery();
            while (result.next()){
                int id = result.getInt("id");
                String userPhone = result.getString("userPhone");
                String userIdNumber = result.getString("userIdNumber");
                String userPassword = result.getString("userPassword");
                Timestamp enrollTime = result.getTimestamp("enrollTime");
                Timestamp loginTime = result.getTimestamp("loginTime");

                User u = new User(id, userName, userPhone, userIdNumber, userPassword, enrollTime, loginTime);
                data.add(u);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,result);
        }
        return data;
    }

    /**
     * 根据身份证号查询用户
     *
     * @param userIdNumber 身份证号
     * @return 查询结果
     */
    @Override
    public User findByIdNumber(String userIdNumber) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            state = conn.prepareStatement(SQL_FIND_BY_USER_ID_NUMBER);
            state.setString(1, userIdNumber);
            result = state.executeQuery();

            if (result.next()){
                int id = result.getInt("id");
                String userName = result.getString("userName");
                String userPhone = result.getString("userPhone");
                String userPassword = result.getString("userPassword");
                Timestamp enrollTime = result.getTimestamp("enrollTime");
                Timestamp loginTime = result.getTimestamp("loginTime");

                User u = new User(id, userName, userPhone, userIdNumber, userPassword, enrollTime, loginTime);
                return u;
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,result);
        }
        return null;
    }

    /**
     * 根据手机号查询用户
     *
     * @param userPhone 手机号
     * @return
     */
    @Override
    public User findByUserPhone(String userPhone) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            state = conn.prepareStatement(SQL_FIND_BY_USER_PHONE);
            state.setString(1, userPhone);
            result = state.executeQuery();
            if (result.next()){
                int id = result.getInt("id");
                String userName = result.getString("userName");
                String userIdNumber = result.getString("userIdNumber");
                String userPassword = result.getString("userPassword");
                Timestamp enrollTime = result.getTimestamp("enrollTime");
                Timestamp loginTime = result.getTimestamp("loginTime");

                User u = new User(id, userName, userPhone, userIdNumber, userPassword, enrollTime, loginTime);
                return u;
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,result);
        }
        return null;
    }

    /**
     * 用户录入
     *
     * @param u 要录入的对象
     * @return 录入的结果
     */
    @Override
    public boolean insert(User u) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        try {
            state = conn.prepareStatement(SQL_INSERT);
            state.setString(1,u.getUserName());
            state.setString(2,u.getUserPhone());
            state.setString(3,u.getUserIdNumber());
            state.setString(4,u.getUserPassword());
            return (state.executeUpdate() > 0);
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,null);
        }
        return false;
    }

    /**
     * 用户的修改
     *
     * @param id      要修改用户的id
     * @param newUser 新的用户对象
     * @return 修改的结果
     */
    @Override
    public boolean update(int id, User newUser) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        try {
            state = conn.prepareStatement(SQL_UPDATE);
            state.setString(1,newUser.getUserName());
            state.setString(2,newUser.getUserPhone());
            state.setString(3,newUser.getUserIdNumber());
            state.setString(4,newUser.getUserPassword());
            state.setInt(5,id);
            return state.executeUpdate() > 0;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,null);
        }
        return false;
    }

    /**
     * 更新登录时间
     *
     * @param id
     * @return
     */
    @Override
    public boolean updateLoginTime(int id) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        try {
            state = conn.prepareStatement(SQL_UPDATE_LOGIN_TIME);
            state.setInt(1,id);
            return state.executeUpdate() > 0;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,null);
        }
        return false;
    }

    /**
     * 根据id，删除单个用户信息
     *
     * @param id 要删除的用户信息
     * @return 删除结果
     */
    @Override
    public boolean delete(int id) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        try {
            state = conn.prepareStatement(SQL_DELETE);
            state.setInt(1, id);
            return state.executeUpdate() > 0;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,null);
        }
        return false;
    }
}
