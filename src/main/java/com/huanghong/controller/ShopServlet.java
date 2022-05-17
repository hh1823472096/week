package com.huanghong.controller;

import com.huanghong.dao.ProductDao;
import com.huanghong.model.Category;
import com.huanghong.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ShopServlet", value = "/Shop")
public class ShopServlet extends HttpServlet {
    Connection con = null;

    @Override
    public void init() throws ServletException {
        con = (Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categoryList = Category.findAllCategory(con);
        req.setAttribute("categoryList",categoryList);

        ProductDao productDao = new ProductDao();
        List<Product> productList = null;
        try{
            if(req.getParameter("categoryId")==null){
                productList = productDao.findAll(con);
            }else{
                int categoryId = Integer.parseInt(req.getParameter("categoryId"));
                productList = productDao.findByCategoryId(categoryId,con);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        req.setAttribute("productList",productList);
        String path = "/WEB-INF/views/shop.jsp";
        req.getRequestDispatcher(path).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
