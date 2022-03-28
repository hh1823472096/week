package com.example._2020211001001319huanghong;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
public class HelloServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        req.getSession().setAttribute("username",username);
        PrintWriter writer = resp.getWriter();
        writer.println("Name:Huang hong");
        writer.println("ID:2020211001001319");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date date = new Date(System.currentTimeMillis());
        simpleDateFormat.format(date);
        writer.println("Date and Time "+date);
        if(username!=null){
            resp.sendRedirect("index.jsp");
        }
    }
}