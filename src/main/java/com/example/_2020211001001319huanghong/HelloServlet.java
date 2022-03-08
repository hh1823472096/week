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
        PrintWriter writer = resp.getWriter();
        writer.println("name:huanghong");
        writer.println("id:2020211001001319");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-mm-dd");
        Date date = new Date(System.currentTimeMillis());
        simpleDateFormat.format(date);
        writer.println("date and time"+date);
    }
}