package com.huanghong.controller;

import com.huanghong.dao.OrderDao;
import com.huanghong.dao.UserDao;
import com.huanghong.model.Order;
import com.huanghong.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/accountDetails")
public class AccountDetailsServlet extends HttpServlet {
    private Connection con = null;

    @Override
    public void init() throws ServletException {
        con = (Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session!=null && session.getAttribute("user")!=null){
            User user = (User) session.getAttribute("user");
            int id = (int) user.getId();
            UserDao dao = new UserDao();
            try {
                user = dao.findById(con,id);
                req.setAttribute("user",user);
                OrderDao orderDao = new OrderDao();
                List<Order> orderList = orderDao.findByUserId(con, id);
                req.setAttribute("orderList",orderList);
                req.getRequestDispatcher("WEB-INF/views/accountDetails.jsp").forward(req,resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            resp.sendRedirect("login");
        }
    }//end doGet
}//end