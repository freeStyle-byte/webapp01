package com.bjpowernode.controller;

import com.bjpowernode.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author freeStyle
 * @time 2021/4/17/15:56
 * @project idea-workspace
 */
public class UserDeleteServlet extends HttpServlet {
/*    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           this.deGet(request,response);
    }*/

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 获取请求参数中的userId的值
        String userId = request.getParameter("userId");
        // 2. 根据这个key值使用Dao封装类操作数据库删除对应的信息。
        UserDao userDao = new UserDao();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        int deleteCount = userDao.delete(Integer.valueOf(userId));
        if (deleteCount == 1){
            // 删除成功刷新信息。这里我只做简单的处理。
            out.println("<font style='color:red;font-size:40'>删除用户成功！</font>");
        }else{
            out.println("<font style='color:red;font-size:40'>删除用户失败！</font>");
        }
    }
}
