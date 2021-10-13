package com.kaikeba.controller;

import com.kaikeba.bean.Message;
import com.kaikeba.mvc.ResponseBody;
import com.kaikeba.service.AdminService;
import com.kaikeba.util.JSONUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class AdminController {

    @ResponseBody("/admin/login.do")
    public String login(HttpServletRequest request, HttpServletResponse response){
        //1.接参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //2调用service传参数
        boolean result = AdminService.login(username, password);
        Message msg = null;
        //3根据不同结果返回数据
        if (result){
            msg=new Message(0,"登陆成功");
            Date date = new Date();
            String ip =request.getRemoteAddr();
            AdminService.UpdateLoginTimeAndIp(username,date,ip);
         //   request.getSession().setAttribute("adminUserName","username");
          //  request.getSession().setAttribute("adminUserName","username");

        }else {
            msg=new Message(-1,"登陆失败");
        }
        //4 将数据转化为Json
        String json = JSONUtil.toJSON(msg);
        //5 json返回给ajax
        return json;
    }
}
