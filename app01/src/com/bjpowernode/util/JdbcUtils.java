package com.bjpowernode.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.Iterator;
import java.util.Map;

/**
 * 将JDBC规范下相关对象的创建与销毁功能封装到方法
 * 1. 注册驱动。
 * 2. 建立连接
 * 3. 获取数据库操作对象
 * 4. 执行sql语句
 * 5. 处理查询结果集
 * 6. 释放资源
 */
public class JdbcUtils {

    private Connection conn = null;

    //静态代码块在类加载时只执行一次，非常符合注册驱动只执行一次的要求
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Driver接口实现类被注册了");
    }

    // 重写获取连接对象的方法
    // 通过全局作用域对象获取
    public Connection getConnection(HttpServletRequest request) {
        ServletContext application = request.getServletContext();
        // 从全局作用域的map盒子中获取可用的连接。
        Map map = (Map) application.getAttribute("connMap");
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            conn = (Connection) it.next();
            Boolean flag = (Boolean) map.get(conn);
            if (flag == true){
                map.put(conn, false);
                break;
            }
        }
        return conn;
    }
    public PreparedStatement createStatement(String sql, HttpServletRequest request) {
        PreparedStatement ps = null;
        try {
            ps = getConnection(request).prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }


    //封装连接对象Connection
    public Connection getConnection() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaweb", "root", "1111");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public PreparedStatement createStatement(String sql) {
        PreparedStatement ps = null;
        try {
            ps = getConnection().prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    public void closeResource(ResultSet rs, Statement ps) {
        closeResult(rs);
        closeStatement(ps);
        closeConnection(conn);
    }

    public void closeResource(Statement ps) {
        closeStatement(ps);
        closeConnection(conn);
    }
    public void closeResource(Statement ps ,HttpServletRequest request) {
        closeStatement(ps);
        closeConnection(conn, request);
    }
    public void setConn(Connection conn) {
        this.conn = conn;
    }

    private void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void closeConnection(Connection conn, HttpServletRequest request) {
        ServletContext application = request.getServletContext();
        Map map = (Map) application.getAttribute("connMap");
        map.put(conn, true);
    }

    private void closeStatement(Statement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void closeResult(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}



