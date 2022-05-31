package com.huanghong.controller;

import com.huanghong.dao.OrderDao;
import com.huanghong.model.Item;
import com.huanghong.model.Order;
import com.huanghong.model.Payment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@WebServlet("/order")//url
public class OrderServlet extends HttpServlet {

    private Connection con = null;

    @Override
    public void init() throws ServletException {
        con = (Connection) getServletContext().getAttribute("con");
    }

    @Override
    public void destroy() {
        super.destroy();//Just puts "destory" string in log
        //Put your code here
        Logger logger = Logger.getLogger(OrderServlet.class);
        logger.info("destory");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Payment> paymentTypeList = Payment.findAllPayment(con);
        req.setAttribute("paymentTypeList",paymentTypeList);
        String path = "WEB-INF/views/order.jsp";
        req.getRequestDispatcher(path).forward(req,resp);
    }//end doGet

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int customerId = req.getParameter("customerId")!=null?Integer.parseInt(req.getParameter("customerId")) : 0;
        int paymentId = req.getParameter("paymentId")!=null? Integer.parseInt(req.getParameter("paymentId")) : 0;
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String phone = req.getParameter("phone");
        String address1 = req.getParameter("address1");
        String address2 = req.getParameter("address2");
        String postalCode = req.getParameter("postalCode");
        String state = req.getParameter("state");
        String city = req.getParameter("city");
        String country = req.getParameter("country");
        String notes = req.getParameter("notes");
        double orderTotal = req.getParameter("orderTotal")!=null? Double.parseDouble(req.getParameter("orderTotal")) :0;

        String message = null;
        if(customerId==0||
                paymentId==0||
                firstName==null||firstName.trim().length()==0||
                phone==null|| phone.trim().length()==0||
                address1==null||address1.trim().length()==0||
                postalCode==null||postalCode.trim().length()==0) {
            message = "Error! Enter Require(*) Info.";
            req.setAttribute("message", message);
            List<Payment> paymentTypeList = Payment.findAllPayment(con);
            req.setAttribute("paymentTypeList", paymentTypeList);
            String path = "WEB-INF/views/orderSuccess.jsp";
            req.getRequestDispatcher(path).forward(req, resp);
        }

        Order order = new Order();
        order.setCustomerId(customerId);
        order.setPaymentId(paymentId);
        order.setFirstName(firstName);
        order.setLastName(lastName);
        order.setPhone(phone);
        order.setAddress1(address1);
        order.setAddress2(address2);
        order.setCity(city);
        order.setCountry(country);
        order.setState(state);
        order.setNotes(notes);
        order.setPostalCode(postalCode);
        order.setOrderTotal(orderTotal);
        order.setPaymentId(paymentId);
        HttpSession session = req.getSession(false);
        if(session!=null && session.getAttribute("cart")!=null){
            ArrayList<Item> cartItems = (ArrayList<Item>) session.getAttribute("cart");
            order.setOrderDetails(new HashSet<Item>(cartItems));
        }

        OrderDao dao = new OrderDao();
        int n;
        try {
            n = dao.save(con,order);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(n>0){
            String path = "WEB-INF/views/orderSuccess.jsp";
            req.getRequestDispatcher(path).forward(req,resp);
        }
    }//end

}
