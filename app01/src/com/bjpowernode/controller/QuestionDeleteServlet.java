package com.bjpowernode.controller;

import com.bjpowernode.dao.QuestionDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author freeStyle
 * @time 2021/4/23/16:55
 * @project idea-workspace
 */
public class QuestionDeleteServlet extends HttpServlet {
/*
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
*/

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 请求参数中存在着要删除题目的唯一标识Id值
        String Id;
        Id = request.getParameter("Id");
        QuestionDao dao = new QuestionDao();
        int result = dao.delete(Integer.valueOf(Id));
        if (result == 1) {
            request.setAttribute("info", "试题删除成功！");
        }else{
            request.setAttribute("info", "试题删除失败！");
        }
        request.getRequestDispatcher("/info_2.jsp").forward(request,response);
    }
}
