package com.kaikeba.service;

import com.kaikeba.bean.Express;
import com.kaikeba.dao.BaseExpressDao;
import com.kaikeba.dao.imp.ExpressDaoMysql;
import com.kaikeba.exception.DuplicateCode;
import com.kaikeba.util.RandomUtil;
import com.mysql.cj.util.StringUtils;

import java.util.List;
import java.util.Map;

public class ExpressService  {
    private static BaseExpressDao dao = new ExpressDaoMysql();
    /**
     * 用于查询数据库中的全部快递（总数+新增），带去见快递（总+新）
     *
     * @return [{size:总数,day:新增},{size:总数,day:新增}]
     */
    public static List<Map<String, Integer>> console() {
        return dao.console();
    }

    /**
     * 用于查询所有快递
     *
     * @param limit      是否分页的标记true表示查询所有
     * @param offset     SQ语句的起始索引
     * @param pageNumber 页查询数量
     * @return 快递的集合
     */
    public static List<Express> findAll(boolean limit, int offset, int pageNumber) {
        return dao.findAll(limit,offset,pageNumber);
    }

    /**
     * 根据快递单号查询快递
     *
     * @param number
     * @return 不存在时返回null
     */
    public static Express findByNumber(String number) {
        return dao.findByNumber(number);
    }

    /**
     * 二维码查
     *
     * @param code
     * @return 不存在是返回null
     */
    public static Express findByCode(String code) {
        return dao.findByCode(code);
    }

    /**
     * 通过电话号码查询
     *
     * @param userPhone 手机号码
     * @return 查询快递信息
     */
    public static List<Express> findByUserPhone(String userPhone) {
        return dao.findByUserPhone(userPhone);
    }

    /**
     * 根据录入的手机号码 查询所有快递
     *
     * @param sysPhone
     * @return
     */
    public static List<Express> findBySysPhone(String sysPhone) {
        return dao.findBySysPhone(sysPhone);
    }

    /**
     * 快递的录入
     *
     * @param express 表示录入的对象
     * @return 录入的结果true表示成功 反之失败
     */
    public static boolean insert(Express express)  {
        express.setCode(RandomUtil.getCode()+"");
        try {
            boolean flag = dao.insert(express);
            if (flag){
                System.out.println("您的验证码是:"+express.getCode());
            }
        } catch (DuplicateCode e) {
          return false;
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
    public static boolean update(int id, Express newExpress) {
        if (StringUtils.isNullOrEmpty(newExpress.getNumber())){
            dao.delete(id);
            return insert(newExpress);
        }else {
            boolean update = dao.update(id, newExpress);
            Express e= dao.findByNumber(newExpress.getNumber());
            if (newExpress.getStatus() == 1){
                updateStatus(e.getCode());
            }
            return update;
        }
    }

    /**
     * 更改快递状态为1 表示取件完成
     *
     * @param code
     * @return
     */
    public static boolean updateStatus(String code) {
        return dao.updateStatus(code);
    }

    /**
     * 根据id 删除快递信息
     *
     * @param id
     * @return
     */
    public static boolean delete(int id) {
        return dao.delete(id);
    }
}
