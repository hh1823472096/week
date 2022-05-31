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
import java.sql.Date;
import java.sql.SQLException;

@WebServlet("/admin/updateUser")
public class UserUpdateServlet extends HttpServlet {
    private Connection con;

    @Override
    public void init() throws ServletException {
        con = (Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        String id = req.getParameter("id");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String gender = req.getParameter("gender");
        String birthday = req.getParameter("birthday");
        user.setId(Long.parseLong(id));
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setGender(String.valueOf(Long.parseLong(gender)));
        user.setBrithdate(Date.valueOf(birthday));
        UserDao dao = new UserDao();
        try {
            dao.updateUser(con,user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String path = "/WEB-INF/views/admin/userList.jsp";
        req.getRequestDispatcher(path).forward(req, resp);
    }
}
