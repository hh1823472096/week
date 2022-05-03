package com.huanghong.controller;


import com.huanghong.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "AdminHomeServlet", value = {"/admin/home", "/admin/login"})
public class AdminHomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            if ("admin".equals(user.getUsername())) {
                request.getRequestDispatcher("../WEB-INF/views/admin/index.jsp").forward(request, response);
            } else {
                //have session but it's not admin user
                session.invalidate();// kill session
                request.setAttribute("message", "Unauthorized Access admin module!!!");
                request.getRequestDispatcher("../WEB-INF/views/login.jsp").forward(request, response);
            }
        } else {
            // don't have session or user is null
            request.setAttribute("message", "Please login as admin!!!");
            request.getRequestDispatcher("../WEB-INF/views/login.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
