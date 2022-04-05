package com.huanghong.week5;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.applet.Applet;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "LoginServlet", value = "/Login")
public class LoginServlet extends HttpServlet {
    private Connection con=null;
    private PreparedStatement ps = null;
    private ResultSet rse = null;
    @Override
    public void init() {
//        ServletContext context =this.getServletContext();
//        con=null;
//        String driver=context.getInitParameter("driver");
//        String url = context.getInitParameter("url");
//        String username=context.getInitParameter("username");
//        String password=context.getInitParameter("password");
//        try{
//            Class.forName(driver);
//            con = DriverManager.getConnection(url, username, password);
//            System.out.println("connection: "+con);
//        }catch (ClassNotFoundException| SQLException e){
//            e.printStackTrace();
//        }
        con = (Connection) getServletContext().getAttribute("con");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Applet req = null;
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        PrintStream writer = null;
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String sql = "Select * from usertable where username = ? and password = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rse = ps.executeQuery();
            if (rse.next()){
                //out.print("Login Success!!! <br>");
                //out.print("Welcome,"+ username +" <br>");

                request.setAttribute("id", rse.getInt("id"));
                request.setAttribute("username", username);
                request.setAttribute("password", password);
                request.setAttribute("email", rse.getString("email"));
                request.setAttribute("gender", rse.getString("gender"));
                request.setAttribute("birthdate", rse.getString("birthdate"));

                request.getRequestDispatcher("userInfo.jsp").forward(request, response);

            }else{
                request.setAttribute("message", "username or password Error!!!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        if (rse!=null){
            try {
                rse.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    

    }
}
