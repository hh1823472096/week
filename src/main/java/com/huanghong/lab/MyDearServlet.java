package com.huanghong.lab;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "MyDearServlet", value = "/MyDearServlet")
public class MyDearServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<HTML>");
        writer.println("<head><title>Title</title></head>");
        writer.println("<body>");
        writer.println("<h1>My jsp</h1>");
        writer.println("<table>");
        writer.println("<tr>"+"<td>"+"name:huanghong"+"</td>"+"</tr>");
        writer.println("<tr>"+"<td>"+"sumbit:Send data to server"+"</td>"+"</tr>");
        writer.println("<tr>"+"<td>"+"class:13"+"</td>"+"</tr>");
        writer.println("<tr>"+"<td>"+"id:2020211001001319"+"</td>"+"</tr>");
        writer.println("</table>");
        writer.println("</body>");
        writer.println("</HTML>");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
