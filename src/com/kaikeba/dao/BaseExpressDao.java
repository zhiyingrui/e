package com.kaikeba.dao;

import com.kaikeba.bean.Express;
import com.kaikeba.exception.DuplicateCode;

import java.util.List;
import java.util.Map;

public interface BaseExpressDao {
    /**
     * 用于查询数据库中的全部快递（总数+新增），带去见快递（总+新）
     * @return [{size:总数,day:新增},{size:总数,day:新增}]
     */
    List<Map<String,Integer>> console();

    /**
     * 用于查询所有快递
     * @param limit 是否分页的标记true表示查询所有
     * @param offset SQ语句的起始索引
     * @param pageNumber 页查询数量
     * @return 快递的集合
     */
    List<Express> findAll(boolean limit,int offset,int pageNumber);

    /**
     * 根据快递单号查询快递
     * @param number
     * @return 不存在时返回null
     */
    Express findByNumber(String number);

    /**
     * 二维码查
     * @param code
     * @return 不存在是返回null
     */
    Express findByCode(String code);

    /**
     * 通过电话号码查询
     * @param userPhone 手机号码
     * @return 查询快递信息
     */
    List<Express> findByUserPhone(String userPhone);

    /**
     * 根据录入的手机号码 查询所有快递
     * @param sysPhone
     * @return
     */
    List<Express> findBySysPhone(String sysPhone);

    /**
     * 快递的录入
     * @param express 表示录入的对象
     * @return 录入的结果true表示成功 反之失败
     */
    boolean insert(Express express) throws DuplicateCode;

    /**
     * 快递的修改 通过id
     * @param id 要修改快递的id
     * @param newExpress 新的快递对象(number,company,username,userPhone)
     * @return
     */
    boolean update(int id,Express newExpress);

    /**
     * 更改快递状态为1 表示取件完成
     * @param code
     * @return
     */
    boolean updateStatus(String code);

    /**
     * 根据id 删除快递信息
     * @param id
     * @return
     */
    boolean delete(int id);

}
