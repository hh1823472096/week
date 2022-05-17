package com.huanghong.controller;

import com.huanghong.dao.ProductDao;
import com.huanghong.model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductListServlet", value = "/admin/ProductList")
public class ProductListServlet extends HttpServlet {
    Connection con=null;
    @Override
    public void init() throws ServletException {
        con=(Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDao = new ProductDao();
        List<Product> productList = null;
        try {
            productList = productDao.findAll(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("productList",productList);
        String path="/WEB-INF/view/admin/productList.jsp";
        req.getRequestDispatcher(path).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req,resp);

    }
}
