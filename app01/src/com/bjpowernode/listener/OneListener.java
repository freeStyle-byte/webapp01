package com.bjpowernode.listener;

import com.bjpowernode.util.JdbcUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author freeStyle
 * @time 2021/4/21/9:26
 * @project idea-workspace
 */
public class OneListener implements ServletContextListener {
    // 在Tomcat启动的时候会预先创建20个与数据库的Connection连接
    // 在UserDao.add方法的执行时，将预先创建的连接交给add方法。
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        JdbcUtils jdbcUtils = new JdbcUtils();
        HashMap<Connection, Boolean> map = new HashMap<>();
        for (int i = 0; i < 20; i++) {
            Connection connection = jdbcUtils.getConnection();
            System.out.println(i+1 + "--> connection = " + connection);
            // true表示这个连接处于空闲状态，false这表示正在被使用
            map.put(connection, true);
            //为了在服务器运行期间一直能够使用20个连接的集合！！
            //所以将这些连接集合存储在全局作用域变量中。共其它Servlet资源共享。
            application.setAttribute("connMap", map);
        }
    }// 方法结束：map集合被销毁。

    @Override
    // 在Http服务器关闭的时候，一定要把全局作用域对象中的连接集合map都销毁掉
    public void contextDestroyed(ServletContextEvent sce) {
        // 获取全局作用域对象
        JdbcUtils jdbcUtils = new JdbcUtils();
        ServletContext application = sce.getServletContext();
        // 获取连接池
        Map map = (Map) application.getAttribute("connMap");
        Iterator iterator = map.keySet().iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Connection conn = (Connection) iterator.next();
            if (conn != null) {
                System.out.println(i+1 + "--> connection = " + conn);
                jdbcUtils.setConn(conn);
                jdbcUtils.closeResource(null);
            }
            i += 1;
        }
    }
}
