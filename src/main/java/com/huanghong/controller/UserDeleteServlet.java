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

@WebServlet("/admin/userDelete")
public class UserDeleteServlet extends HttpServlet {
    private Connection con;

    @Override
    public void init() throws ServletException {
        con = (Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        User user = new User();
        user.setId(Integer.parseInt(userId));
        UserDao dao = new UserDao();
        int result;
        try {
            result=dao.deleteUser(con, user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(result>0){
            String path = "/WEB-INF/views/admin/userList.jsp";
            req.getRequestDispatcher(path).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
