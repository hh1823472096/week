package com.huanghong.week3;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
        String birthdate= request.getParameter("birthdate");


        PrintWriter writer=response.getWriter();
        writer.println("<br>username:"+username);
        writer.println("<br>password:"+password);
        writer.println("<br>email:"+email);
        writer.println("<br>gender:"+gender);
        writer.println("<br>birthdate:"+birthdate);
        writer.close();
    }
}
