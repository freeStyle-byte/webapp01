package com.bjpowernode.controller;

import com.bjpowernode.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author freeStyle
 * @time 2021/4/17/16:51
 * @project idea-workspace
 */
public class UserLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 通知请求对象对请求信息重新以utf-8的编码方式再次编译
        request.setCharacterEncoding("utf-8");
        // 2. 调用请求对象获取请求参数信息
        String userName,password;
        userName = request.getParameter("userName");
        password = request.getParameter("password");
        // 3. 使用Dao查找t_user表中的信息匹配
        UserDao userDao = new UserDao();
        int exeCount = userDao.selectOne(userName, password);
        // 4. 调用响应体，将验证的结果将不同的资源地址写入响应头，交给服务器发送给请求客户端。
        if (exeCount == 1){
            HttpSession session = request.getSession();
            response.sendRedirect("index.html");
        }else{
            response.sendRedirect("login-error.html");
        }
    }

    /*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }*/
}
