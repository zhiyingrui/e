package com.kaikeba.controller;

import com.kaikeba.bean.BootStrapTableCourier;
import com.kaikeba.bean.Courier;
import com.kaikeba.bean.Message;
import com.kaikeba.bean.ResultData;
import com.kaikeba.mvc.ResponseBody;
import com.kaikeba.service.CourierService;
import com.kaikeba.util.DateFormatUtil;
import com.kaikeba.util.JSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CourierController {
    @ResponseBody("/courier/console.do")
    public String console(HttpServletRequest request, HttpServletResponse response){

        List<Map<String, Integer>> data = CourierService.console();
        Message msg = new Message();
        if (data.size() == 0){

            msg.setStatus(-1);
        }else {

            msg.setStatus(0);
        }
        msg.setData(data);
        String json = JSONUtil.toJSON(msg);
        return json;
    }
    @ResponseBody("/courier/list.do")
    public String list(HttpServletRequest request, HttpServletResponse response){

        int offset = Integer.parseInt(request.getParameter("offset"));
        // 获取当前页要查询的数据量
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        List<Courier> list = CourierService.findAll(true, offset, pageNumber);
        List<BootStrapTableCourier> list2 = new ArrayList<>();
        for (Courier c : list){

            String enrollTime = DateFormatUtil.format(c.getEnrollTime());
            String loginTime = c.getLoginTime()==null?"未登录":DateFormatUtil.format(c.getLoginTime());
            BootStrapTableCourier c2 = new BootStrapTableCourier(c.getId(),c.getCourierName(),c.getCourierPhone(),"******",c.getCourierPassword(),c.getCourierSendNumber(),enrollTime,loginTime);
            list2.add(c2);
        }
        List<Map<String, Integer>> console = CourierService.console();
        Integer total = console.get(0).get("data_size");
        ResultData<BootStrapTableCourier> data = new ResultData<>();
        data.setRows(list2);
        data.setTotal(total);
        String json = JSONUtil.toJSON(data);
        return json;
    }
    @ResponseBody("/courier/insert.do")
    public String insert(HttpServletRequest request, HttpServletResponse response){

        String courierName = request.getParameter("courierName");
        String courierPhone = request.getParameter("courierPhone");
        String courierIdNumber = request.getParameter("courierIdNumber");
        String courierPassword = request.getParameter("courierPassword");
        Courier c = new Courier(courierName,courierPhone,courierIdNumber,courierPassword);
        boolean flag = CourierService.insert(c);
        Message msg = new Message();
        if (flag){

            msg.setStatus(0);
            msg.setResult("快递员录入成功");
        }else {

            msg.setStatus(-1);
            msg.setResult("快递员录入失败");
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }
    @ResponseBody("/courier/find.do")
    public String find(HttpServletRequest request, HttpServletResponse response){

        String courierPhone = request.getParameter("courierPhone");
        Courier c = CourierService.findByPhone(courierPhone);
        Message msg = new Message();

        if (c == null){

            msg.setStatus(-1);
            msg.setResult("手机号不存在");
        }else {

            msg.setStatus(0);
            msg.setResult("查询成功");
            msg.setData(c);
        }

        String json = JSONUtil.toJSON(msg);
        return json;
    }
    @ResponseBody("/courier/update.do")
    public String update(HttpServletRequest request, HttpServletResponse response){

        int id = Integer.parseInt(request.getParameter("id"));
        String courierName = request.getParameter("courierName");
        String courierPhone = request.getParameter("courierPhone");
        String courierIdNumber = request.getParameter("courierIdNumber");
        String courierPassword = request.getParameter("courierPassword");

        Courier newCourier = new Courier(courierName,courierPhone,courierIdNumber,courierPassword);
        Message msg = new Message();
        boolean flag = CourierService.update(id, newCourier);
        if (flag){

            msg.setStatus(0);
            msg.setResult("修改成功");
        }else {

            msg.setStatus(-1);
            msg.setResult("修改失败");
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }
    @ResponseBody("/courier/delete.do")
    public String delete(HttpServletRequest request, HttpServletResponse response){

        int id = Integer.parseInt(request.getParameter("id"));
        boolean flag = CourierService.delete(id);
        Message msg = new Message();
        if (flag){

            msg.setStatus(0);
            msg.setResult("删除成功");
        }else {

            msg.setStatus(-1);
            msg.setResult("删除失败");
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }
}
