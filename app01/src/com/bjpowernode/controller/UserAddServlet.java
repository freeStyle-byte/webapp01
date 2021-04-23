package com.bjpowernode.controller;

import com.bjpowernode.dao.UserDao;
import com.bjpowernode.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * @author freeStyle
 * @time 2021/4/14/16:10
 * @project idea-workspace
 */
public class UserAddServlet extends HttpServlet {
  /*  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }*/

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 得到用户注册提交表单的信息
        String userName = request.getParameter("username");
        String password = request.getParameter("userpwd");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        User user = new User(null,userName,password,gender,email);
        // 2. 调用UserDao将用户信息借助JDBC插入到数据库表中。
        UserDao dao = new UserDao();
        Date start = new Date();
        int result = dao.appendOne(user,request);
        Date end = new Date();
        System.out.println("time = " + (end.getTime() - start.getTime()) + "ms");
        // 3. 调用响应对象将处理的结果以二进制的形式写入到响应体中
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if (result == 1) {
            out.println("<font style='color:red;font-size:40'>用户注册成功！</font>");
        }else{
            out.println("<font style='color:red;font-size:40'>用户注册失败！</font>");
        }
        // Tomcat负责销毁【请求对象和响应对象】, 将Http响应协议包推送到浏览器客户端,
        // 浏览器根据响应头中的content-type指定的二进制格式使用指定的编辑器解析二进制,
        // 并将结果显示到浏览器上。

    }
}
