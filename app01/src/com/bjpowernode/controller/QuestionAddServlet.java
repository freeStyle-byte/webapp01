package com.bjpowernode.controller;

import com.bjpowernode.dao.QuestionDao;
import com.bjpowernode.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author freeStyle
 * @time 2021/4/22/18:09
 * @project idea-workspace
 */
public class QuestionAddServlet extends HttpServlet {
/*    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }*/

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 调用请求参数获取参数中题目的信息。
        String title,optionA,optionB,optionC,optionD,answer;
        title = request.getParameter("title");
        optionA = request.getParameter("optionA");
        optionB = request.getParameter("optionB");
        optionC = request.getParameter("optionC");
        optionD = request.getParameter("optionD");
        answer = request.getParameter("answer");
        //2. 调用QuestionDao对象对将信息插入到数据库question表中
        QuestionDao dao = new QuestionDao();
        Question question = new Question(null,title,optionA,optionB,optionC,optionD,answer);
        long start = new Date().getTime();
        int result = dao.add(question,request);
        long end = new Date().getTime();
        System.out.println("times = " + (end - start) + "ms");
        if (result == 1){
            request.setAttribute("info", "数据添加成功!");
        }else{
            request.setAttribute("info", "数据添加失败!");
        }
        request.getRequestDispatcher("/info.jsp").forward(request, response);
    }
}
