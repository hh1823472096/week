package com.huanghong.week4;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//@WebServlet(name ="JDBCDemo",urlPatterns = {"/jdbc"},
//        initParams = {
//        @WebInitParam(name="driver",value ="com.microsoft.sqlserver.jdbc.SQLServerDriver" ),
//                @WebInitParam(name="url",value ="jdbc:sqlserver://localhost:1433;database=userdb;encrypt=false" ),
//                @WebInitParam(name="username",value ="sa" ),
//                @WebInitParam(name="password",value ="123456" ),
//        },
//        loadOnStartup = 1
//)
@WebServlet(urlPatterns = {"/jdbc"},loadOnStartup = 1)
public class JDBCDemoServlet extends HttpServlet {
    Connection con=null;
    public void init() throws ServletException{
        super.init();
        String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url="jdbc:sqlserver://localhost:1433;database=userdb;encrypt=false";
        String username="sa";
        String password="123456";

        try{
            Class.forName(driver);
            Connection con= DriverManager.getConnection(url,username,password);
            System.out.println("Connection -->in JDBCDemoServlet"+con);//just
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("connection -->"+con);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            con.close();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
