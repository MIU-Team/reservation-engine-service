package edu.miu.cs544.group4.engine.configuration;

import javax.servlet.http.HttpServletRequest;


public class CurrentUser {
    public static String getFullName(HttpServletRequest request) {
        return request.getHeader("fullname");
    }

    public static String getEmail(HttpServletRequest request) {
        return request.getHeader("email");
    }

    public static String getUserId(HttpServletRequest request) {
        return request.getHeader("userid");
    }

    public static String getPhoneNumber(HttpServletRequest request) {
        return request.getHeader("phonenumber");
    }

    public static String getUsername(HttpServletRequest request) {
        return request.getHeader("username");
    }
}
