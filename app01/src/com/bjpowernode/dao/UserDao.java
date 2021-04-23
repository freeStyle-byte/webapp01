package com.bjpowernode.dao;

import com.bjpowernode.entity.User;
import com.bjpowernode.util.JdbcUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private JdbcUtils jdbcUtil = new JdbcUtils();

    //对t_user表进行插入操作
    public int appendOne(User user) {
        String sql = "INSERT INTO t_user(userName,password,gender,email) VALUES(?,?,?,?)";
        PreparedStatement ps = jdbcUtil.createStatement(sql);
        int count = 0;
        try {
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getGender());
            ps.setString(4, user.getEmail());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.closeResource(ps);
        }
        return count;
    }

    public int appendOne(User user, HttpServletRequest request) {
        String sql = "INSERT INTO t_user(userName,password,gender,email) VALUES(?,?,?,?)";
        PreparedStatement ps = jdbcUtil.createStatement(sql, request);

        int count = 0;
        try {
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getGender());
            ps.setString(4, user.getEmail());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.closeResource(ps ,request);
        }
        return count;
    }

    //对t_user表进行删除操作
    public int delete(Integer userId) {
        String sql = "DELETE FROM t_user WHERE userId = ?";
        PreparedStatement ps = jdbcUtil.createStatement(sql);
        int count = 0;
        try {
            ps.setInt(1, userId);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.closeResource(ps);
        }
        return count;
        //System.out.println(count == 1 ? "删除数据成功" : "删除数据失败");
    }


    //对t_user表进行更改操作
    public int update(Integer userId, String userName, String password) {
        String sql = "UPDATE t_user SET userName=?,password=? WHERE userId = ?";
        PreparedStatement ps = null;
        int count = 0;
        try {
            ps = jdbcUtil.createStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);
            ps.setInt(3, userId);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.closeResource(ps);
        }
        return count;
    }


    //对t_user表进行查询操作
    public List<User> selectAll() {
        ResultSet rs = null;
        List<User> userList = new ArrayList<>();
        String sql = "SELECT userId,userName,password,gender,email FROM t_user";
        PreparedStatement ps = jdbcUtil.createStatement(sql);
        try {
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer userId = rs.getInt("userId");
                String userName = rs.getString("userName");
                String password = rs.getString("password");
                String gender = rs.getString("gender");
                String email = rs.getString("email");
                User user = new User(userId, userName, password, gender, email);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.closeResource(rs, ps);
        }
        return userList;
    }

    public int selectOne(String name, String pwd) {
        String sql = "SELECT count(*) FROM t_user WHERE userName = ? and password = ?";
        PreparedStatement ps = jdbcUtil.createStatement(sql);
        ResultSet rs = null;
        int count = 0;
        try {
            ps.setString(1, name);
            ps.setString(2, pwd);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.closeResource(rs, ps);
        }
        return count;
    }
}
