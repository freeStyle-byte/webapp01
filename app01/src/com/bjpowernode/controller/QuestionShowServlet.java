package com.bjpowernode.controller;

import com.bjpowernode.dao.QuestionDao;
import com.bjpowernode.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author freeStyle
 * @time 2021/4/23/6:52
 * @project idea-workspace
 */
public class QuestionShowServlet extends HttpServlet {
/*    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }*/

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Question> questions = new ArrayList<>();
        QuestionDao dao = new QuestionDao();
        questions = dao.selectAll();
        request.setAttribute("qList", questions);
        request.getRequestDispatcher("/question-show.jsp").forward(request,response);
    }
}
