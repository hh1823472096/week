package com.huanghong.week3;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@WebServlet(name = "register", value = "/register")
public class RegisterServlet<connection> extends HttpServlet {
    Connection con=null;


    @Override
    public void init() throws ServletException {
          super.init();
//        ServletContext application = getServletContext();
//        String driver = application.getInitParameter("driver");
//        String url = application.getInitParameter("url");
//        String username = application.getInitParameter("Username");
//        String password = application.getInitParameter("Password");
//        try {
//            Class.forName(driver);
//            conn = DriverManager.getConnection(url, username, password);
//        } catch (ClassNotFoundException | SQLException e) {
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
        String username= request.getParameter("username");//username
        String password= request.getParameter("password");
        String email= request.getParameter("email");
        String gender= request.getParameter("gender");
        String brithdate= request.getParameter("brithdate");
        String sql ="select max(id) min from usertable";
        int id = 0;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                id = resultSet.getInt("min");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql1="insert into usertable(id,username,password,email,gender,brithdate)" +
                "values(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement= con.prepareStatement(sql1);
            preparedStatement.setInt(1,id+1);
            preparedStatement.setString(2,username);
            preparedStatement.setString(3,password);
            preparedStatement.setString(4,email);
            preparedStatement.setString(5, gender);
            preparedStatement.setDate(6, Date.valueOf(brithdate));
            preparedStatement.executeUpdate();
            response.sendRedirect("Login.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        PrintWriter writer=response.getWriter();
//        writer.println("<br>username:"+username);
//        writer.println("<br>password:"+password);
//        writer.println("<br>email:"+email);
//        writer.println("<br>gender:"+gender);
//        writer.println("<br>brithdate:"+brithdate);
//        writer.close();
    }
}
