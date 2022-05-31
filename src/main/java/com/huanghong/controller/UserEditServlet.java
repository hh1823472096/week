package com.huanghong.controller;

import com.huanghong.dao.UserDao;
import com.huanghong.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/admin/userEdit")
public class UserEditServlet extends HttpServlet {
    private Connection con = null;

    @Override
    public void init() throws ServletException {
        con = (Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        if(userId == null){
            return;//error
        }
        UserDao userDao = new UserDao();
        User user = new User();
        try {
            user = userDao.findById(con, Integer.valueOf(userId));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("userbyId", user);
        String path = "/WEB-INF/views/admin/userEdit.jsp";
        req.getRequestDispatcher(path).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
