package com.bjpowernode.controller;

import com.bjpowernode.dao.UserDao;
import com.bjpowernode.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author freeStyle
 * @time 2021/4/17/14:23
 * @project idea-workspace
 */
public class UserFindServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 使用DAO类将查询命令调用数据库得到所有用户信息
        UserDao dao = new UserDao();
        List<User> users = dao.selectAll();
        /*
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("/app01/login-error.html");
            return ;
        }*/
        // 2. 调用响应对象将用户信息结合table标签以二进制形式写入响应体。
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<table border='2' align='center'>");
        out.print("<tr>" +
                        "<td>用户名称</td>" +
                        "<td>用户密码</td>" +
                        "<td>用户性别</td>" +
                        "<td>用户邮箱</td>" +
                        "<td>操作</td>" +
                  "</tr>");
        for (User user : users) {
            out.print("<tr>" +
                            "<td>" + user.getUserName() + " </td>" +
                            "<td>" + "******" + "</td>" +
                            "<td>" + user.getGender() + "</td>" +
                            "<td>" + user.getEmail() + "</td>" +
                            "<td><a href='http://localhost:8080/app01/user/delete?userId=" + user.getUserId() + "'>删除</a></td>" +
                      "</tr>");
        }
        out.print("</table>");
    }
}
