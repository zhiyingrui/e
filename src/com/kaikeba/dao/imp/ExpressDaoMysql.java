package com.kaikeba.dao.imp;

import com.kaikeba.bean.Express;
import com.kaikeba.dao.BaseExpressDao;
import com.kaikeba.exception.DuplicateCode;
import com.kaikeba.util.DruidUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpressDaoMysql implements BaseExpressDao {
    //用于查询数据库中的全部快递（总数+新增），待取件快递（总数+新增）
    public static final String SQL_CONSOLE = "select count(id) data1_size,count(TO_DAYS(intime)=TO_DAYS(NOW()) or null) data1_day,count(status = 0 or null) data2_size,count(TO_DAYS(intime)=TO_DAYS(NOW()) and status = 0 or null) data2_day from express";
    //用于分页查询数据库中的快递信息
    public static final String SQL_FIND_LIMIT = "select * from express limit ?,?";
    //查询数据库所有信息
    public static final String SQL_FIND_ALL = "select * from express";
    //通过取件码查询快递信息
    public static final String SQL_FIND_BY_CODE = "select * from express where code=?";
    public static final String SQL_FIND_BY_NUMBER = "select * from express where number=?";
    public static final String SQL_FIND_BY_SYSPHONE = "select * from express where sysphone=?";
    public static final String SQL_FIND_BY_USERPHONE = "select * from express where userphone=?";
    //录入快递
    public static final String SQL_INSERT = "insert into express (number,username,userphone,company,code,intime,status,sysphone) values(?,?,?,?,?,NOW(),0,?)";
   //修改快递
    public static final String SQL_UPDATE = "update express set number=?,username=?,company=?,status=? where id=?";
    //取件
    public static final String SQL_UPDATE_STATUS = "update express set status=1,outtime=NOW(),code=null where code=?";
    public static final String SQL_DELETE = "delete from express where id=?";


    /**
     * 用于查询数据库中的全部快递（总数+新增），带去见快递（总+新）
     *
     * @return [{size:总数,day:新增},{size:总数,day:新增}]
     */
    @Override
    public List<Map<String, Integer>> console() {
        ArrayList<Map<String,Integer>> data = new ArrayList<>();
        //1 获取数据库的连接
        Connection connection = DruidUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //2 预编译sql语句
        try {
             preparedStatement = connection.prepareStatement(SQL_CONSOLE);
            //3填充参数
            //4执行sql语句
             resultSet = preparedStatement.executeQuery();
             //5获取执行的结果
            if (resultSet.next()){
                int data1_size = resultSet.getInt("data1_size");
                int data1_day = resultSet.getInt("data1_day");
                int data2_size = resultSet.getInt("data2_size");
                int data2_day = resultSet.getInt("data2_day");
                Map data1= new HashMap();
                data1.put("data1_size",data1_size);
                data1.put("data1_day",data1_day);
                Map data2= new HashMap();
                data2.put("data2_size",data2_size);
                data2.put("data2_day",data2_day);
                data.add(data1);
                data.add(data2);
            }
            //6资源的释放
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(connection,preparedStatement,resultSet);
        }

        return data;
    }

    /**
     * 用于查询所有快递
     *
     * @param limit      是否分页的标记true表示查询所有
     * @param offset     SQ语句的起始索引
     * @param pageNumber 页查询数量
     * @return 快递的集合
     */
    @Override
    public List<Express> findAll(boolean limit, int offset, int pageNumber) {
        ArrayList<Express> data=new ArrayList<>();
        //1 获取数据库的连接
        Connection connection = DruidUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //2 预编译sql语句
        try {
            if (limit) {
                preparedStatement = connection.prepareStatement(SQL_FIND_LIMIT);
                preparedStatement.setInt(1,offset);
                preparedStatement.setInt(2,pageNumber);
            }else {
                preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            }
            //3填充参数
            //4执行sql语句
            resultSet = preparedStatement.executeQuery();
            //5获取执行的结果
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String number = resultSet.getString("number");
                String username= resultSet.getString("username");
                String userphone=resultSet.getString("userphone");
                String company=resultSet.getString("company");
                String code =resultSet.getString("code");
                Timestamp inTime =resultSet.getTimestamp("inTime");
                Timestamp outTime=resultSet.getTimestamp("outTime");
                int status =resultSet.getInt("status");
                String sysPhone=resultSet.getString("sysPhone");
                Express e= new Express( id,number,username,userphone,company, code, inTime, outTime,  status, sysPhone);
                data.add(e);
            }
            //6资源的释放
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(connection,preparedStatement,resultSet);
        }
        return data;
    }

    /**
     * 根据快递单号查询快递
     *
     * @param number
     * @return 不存在时返回null
     */
    @Override
    public Express findByNumber(String number) {
        //1 获取数据库的连接
        Connection connection = DruidUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //2 预编译sql语句
        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_NUMBER);
            //3填充参数
            preparedStatement.setString(1,number);
            //4执行sql语句
            resultSet = preparedStatement.executeQuery();

            //5获取执行的结果
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String userphone = resultSet.getString("userphone");
                String company = resultSet.getString("company");
                String code = resultSet.getString("code");
                Timestamp inTime = resultSet.getTimestamp("inTime");
                Timestamp outTime = resultSet.getTimestamp("outTime");
                int status = resultSet.getInt("status");
                String sysPhone = resultSet.getString("sysPhone");
                Express e = new Express(id, number, username, userphone, company, code, inTime, outTime, status, sysPhone);
                return e;
            }
            //6资源的释放
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(connection,preparedStatement,resultSet);
        }
        return null;
    }

    /**
     * 取件码查
     *
     * @param code
     * @return 不存在是返回null
     */
    @Override
    public Express findByCode(String code) {
        //1 获取数据库的连接
        Connection connection = DruidUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //2 预编译sql语句
        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_CODE);
            //3填充参数
            preparedStatement.setString(1,code);
            //4执行sql语句
            resultSet = preparedStatement.executeQuery();

            //5获取执行的结果
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String number = resultSet.getString("number");
                String userphone = resultSet.getString("userphone");
                String company = resultSet.getString("company");
                Timestamp inTime = resultSet.getTimestamp("inTime");
                Timestamp outTime = resultSet.getTimestamp("outTime");
                int status = resultSet.getInt("status");
                String sysPhone = resultSet.getString("sysPhone");
                Express e = new Express(id, number, username, userphone, company, code, inTime, outTime, status, sysPhone);
                return e;
            }

            //6资源的释放
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(connection,preparedStatement,resultSet);
        }
        return null;
    }

    /**
     * 通过电话号码查询
     *
     * @param userPhone 手机号码
     * @return 查询快递信息
     */
    @Override
    public List<Express> findByUserPhone(String userPhone) {
        ArrayList<Express> data = new ArrayList<>();
        //1 获取数据库的连接
        Connection connection = DruidUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //2 预编译sql语句
        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_USERPHONE);
            //3填充参数
            preparedStatement.setString(1,userPhone);
            //4执行sql语句
            resultSet = preparedStatement.executeQuery();

            //5获取执行的结果
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String number = resultSet.getString("number");
                String username= resultSet.getString("username");
                String userphone=resultSet.getString("userphone");
                String company=resultSet.getString("company");
                String code =resultSet.getString("code");
                Timestamp inTime =resultSet.getTimestamp("inTime");
                Timestamp outTime=resultSet.getTimestamp("outTime");
                int status =resultSet.getInt("status");
                String sysPhone=resultSet.getString("sysPhone");
                Express e= new Express( id,number,username,userphone,company, code, inTime, outTime,  status, sysPhone);
                data.add(e);
            }
            //6资源的释放
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(connection,preparedStatement,resultSet);
        }
        return data;
    }

    /**
     * 根据录入的手机号码 查询所有快递
     *
     * @param sysPhone
     * @return
     */
    @Override
    public List<Express> findBySysPhone(String sysPhone) {
        ArrayList<Express> data = new ArrayList<>();
        //1 获取数据库的连接
        Connection connection = DruidUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //2 预编译sql语句
        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_SYSPHONE);
            //3填充参数
            preparedStatement.setString(1,sysPhone);
            //4执行sql语句
            resultSet = preparedStatement.executeQuery();

            //5获取执行的结果
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String number = resultSet.getString("number");
                String username= resultSet.getString("username");
                String userphone=resultSet.getString("userphone");
                String company=resultSet.getString("company");
                String code =resultSet.getString("code");
                Timestamp inTime =resultSet.getTimestamp("inTime");
                Timestamp outTime=resultSet.getTimestamp("outTime");
                int status =resultSet.getInt("status");
                Express e= new Express( id,number,username,userphone,company, code, inTime, outTime,  status, sysPhone);
                data.add(e);
            }
            //6资源的释放
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(connection,preparedStatement,resultSet);
        }
        return data;
    }

    /**
     * 快递的录入
     *
     * @param express 表示录入的对象
     * @return 录入的结果true表示成功 反之失败
     */
    @Override
    public boolean insert(Express express) throws DuplicateCode{
        //1 连接的获取
        Connection connection = DruidUtil.getConnection();
        //2 预编译sql语句
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            //3 填充参数
            preparedStatement.setString(1,express.getNumber());
            preparedStatement.setString(2,express.getUsername());
            preparedStatement.setString(3,express.getUserphone());
            preparedStatement.setString(4,express.getCompany());
            preparedStatement.setString(5,express.getCode());
            preparedStatement.setString(6,express.getSysPhone());

            //4执行sql语句 并获取执行结果
            return preparedStatement.executeUpdate()>0?true:false;
            //5 释放资源
        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
            if (e1.getMessage().endsWith("for key 'express.number'")){
                DuplicateCode e2 = new DuplicateCode(e1.getMessage());
                throw e2;
            }else {
                e1.printStackTrace();
            }
        }finally {
            DruidUtil.close(connection,preparedStatement,null);
        }
        return false;
    }

    /**
     * 快递的修改 通过id
     *
     * @param id         要修改快递的id
     * @param newExpress 新的快递对象(number,company,username,userPhone)
     * @return
     */
    @Override
    public boolean update(int id, Express newExpress) {
        //1 获取连接
        Connection connection = DruidUtil.getConnection();
        PreparedStatement preparedStatement = null;
        //2 预编译sql语句
        try {
             preparedStatement = connection.prepareStatement(SQL_UPDATE);
             //3 填充参数
             preparedStatement.setString(1,newExpress.getNumber());
             preparedStatement.setString(2,newExpress.getUsername());
             preparedStatement.setString(3,newExpress.getCompany());
             preparedStatement.setInt(4,newExpress.getStatus());
             preparedStatement.setInt(5,id);
             return preparedStatement.executeUpdate()>0?true:false;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(connection,preparedStatement,null);
        }
        return false;
    }

    /**
     * 更改快递状态为1 表示取件完成
     *
     * @param code
     * @return
     */
    @Override
    public boolean updateStatus(String code) {
        //1 获取连接
        Connection connection = DruidUtil.getConnection();
        PreparedStatement preparedStatement = null;
        //2 预编译sql语句
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_STATUS);
            //3 填充参数
            preparedStatement.setString(1,code);
            return preparedStatement.executeUpdate()>0?true:false;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(connection,preparedStatement,null);
        }
        return false;
    }

    /**
     * 根据id 删除快递信息
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(int id) {
        //1 获取连接
        Connection connection = DruidUtil.getConnection();
        PreparedStatement preparedStatement = null;
        //2 预编译sql语句
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE);
            //3 填充参数
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate()>0?true:false;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(connection,preparedStatement,null);
        }
        return false;
    }
}
