package com.bjpowernode.dao;

import com.bjpowernode.entity.Question;
import com.bjpowernode.util.JdbcUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author freeStyle
 * @time 2021/4/22/18:13
 * @project idea-workspace
 */
public class QuestionDao {
    private JdbcUtils jdbcUtils = new JdbcUtils();

    public int add(Question q){
        String sql = "INSERT INTO question(title,optionA,optionB,optionC,optionD,answer) VALUES(?,?,?,?,?,?)";
        PreparedStatement ps = jdbcUtils.createStatement(sql);
        int result = 0;
        try {
            ps.setString(1, q.getTitle());
            ps.setString(2, q.getOptionA());
            ps.setString(3, q.getOptionB());
            ps.setString(4, q.getOptionC());
            ps.setString(5, q.getOptionD());
            ps.setString(6, q.getAnswer());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.closeResource(ps);
        }
        return result;
    }// 向数据库表中添加题目。
    public int add(Question q, HttpServletRequest request){
        String sql = "INSERT INTO question(title,optionA,optionB,optionC,optionD,answer) VALUES(?,?,?,?,?,?)";
        PreparedStatement ps = jdbcUtils.createStatement(sql, request);
        int result = 0;
        try {
            ps.setString(1, q.getTitle());
            ps.setString(2, q.getOptionA());
            ps.setString(3, q.getOptionB());
            ps.setString(4, q.getOptionC());
            ps.setString(5, q.getOptionD());
            ps.setString(6, q.getAnswer());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.closeResource(ps, request);
        }
        return result;
    }// 向数据库表中添加题目。
    public List<Question> selectAll(){
        String sql = "SELECT Id,title,optionA,optionB,optionC,optionD,answer FROM question";
        PreparedStatement ps = jdbcUtils.createStatement(sql);
        List<Question> questions = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("Id");
                String title = rs.getString("title");
                String optionA = rs.getString("optionA");
                String optionB = rs.getString("optionB");
                String optionC = rs.getString("optionC");
                String optionD = rs.getString("optionD");
                String answer = rs.getString("answer");
                Question question = new Question(id,title,optionA,optionB,optionC,optionD,answer);
                questions.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.closeResource(rs,ps);
        }
        return questions;
    }

    public int delete(Integer id){
        String sql = "delete from question where Id = ?";
        PreparedStatement ps = jdbcUtils.createStatement(sql);
        int result = 0;
        try {
            ps.setInt(1, id);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcUtils.closeResource(ps);
        }
        return result;
    }
}
