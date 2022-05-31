package com.huanghong.controller;

import com.huanghong.dao.OrderDao;
import com.huanghong.model.Order;
import com.huanghong.model.Payment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/admin/orderList")
public class AdminOrderServlet extends HttpServlet {
    private Connection con = null;

    @Override
    public void destroy() {
        super.destroy();//just puts "destroy" string in log
    }

    @Override
    public void init() throws ServletException {
        con = (Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Payment> paymentTypeList = Payment.findAllPayment(con);
        req.setAttribute("paymentTypeList", paymentTypeList);
        OrderDao orderDao = new OrderDao();
        List<Order> orderList = orderDao.findAll(con);
        req.setAttribute("orderList", orderList);
        String path = "/WEB-INF/views/admin/orderList.jsp";
        req.getRequestDispatcher(path).forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
