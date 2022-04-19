package com.huanghong.controller;

import com.huanghong.dao.UserDao;
import com.huanghong.model.User;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(name = "UpdateUserServlet", value = "/updateUser")
public class UpdateUserServlet extends HttpServlet {
    Connection con;
    @Override
    public void init() throws ServletException {
        con=(Connection) getServletContext().getAttribute("con");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/updateUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        String username = request.getParameter("Username");
        String password = request.getParameter("password");
        String email = request.getParameter("Email");
        Date brithdate = Date.valueOf(request.getParameter("Birthdate"));
        String gender=request.getParameter("gender");
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword((password));
        user.setEmail(email);
        user.setBrithdate(brithdate);
        user.setGender(gender);
        UserDao userDao = new UserDao();
        try {
            int result = userDao.updateUser(con, user);
            if(result>0){
                HttpSession session = request.getSession();
                session.setAttribute("user",user);
                request.getRequestDispatcher("/WEB-INF/views/userInfo.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("/WEB-INF/views/updateUser.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
