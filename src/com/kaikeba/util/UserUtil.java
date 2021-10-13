package com.kaikeba.util;

import javax.servlet.http.HttpSession;

public class UserUtil {
    public static String getUserName(HttpSession session) {
       // return (String) session.getAttribute("adminUserName");
        return "";
    }
    public static String getUserPhone(HttpSession session) {
        return "12222222";
    }
}
