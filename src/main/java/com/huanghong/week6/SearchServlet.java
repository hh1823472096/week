package com.huanghong.week6;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String txt = request.getParameter("txt");
        String search = request.getParameter("search");

        if (txt == null){
            response.sendRedirect(request.getContextPath() + "/week2/index.jsp");
        }else {
            if ("baidu".equals(search)){
                response.sendRedirect("https://www.baidu.com/s?wd=" + txt);
            }else if ("bing".equals(search)){
                response.sendRedirect("https://cn.bing.com/search?q=" + txt);
            }else if ("google".equals(search)){
                response.sendRedirect("https://www.google.com/search?q=" + txt);
            }
        }
    }
}
