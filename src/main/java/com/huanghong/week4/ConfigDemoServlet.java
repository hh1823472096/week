package com.huanghong.week4;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "ConfigDemoServlet", urlPatterns = {"/Config"},
        initParams = {
                @WebInitParam(name = "name", value = "LiZhihui"),
                @WebInitParam(name = "studentId", value = "2020211001001308")
        }
)
public class ConfigDemoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=this.getServletConfig().getInitParameter("name");
        String id=this.getServletConfig().getInitParameter("studentId");
        PrintWriter writer=response.getWriter();
        writer.println("<HTML>");
        writer.println("<HEAD>");
        writer.println("</HEAD>");
        writer.println("<BODY>");
        writer.println("<p>name:"+name+"</p>");
        writer.println("<p>studentId:"+id+"</p>");
        writer.println("</BODY>");
        writer.println("</HTML>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
