package com.huanghong.controller;

import com.huanghong.dao.ProductDao;
import com.huanghong.model.Category;
import com.huanghong.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


@WebServlet( value = "/ProductDetails")
public class ProductDetailsServlet extends HttpServlet {
    Connection con = null;

    @Override
    public void init() throws ServletException {
        con = (Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = req.getParameter("id")!=null?Integer.parseInt(req.getParameter("id")):0;
        ProductDao productDao = new ProductDao();
        if(id==0){
            return ;//error
        }
        List<Category> categoryList = Category.findAllCategory(con);
        req.setAttribute("categoryList",categoryList);

        Product product;
        try {
            product = productDao.findById(id, con);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("p",product);
        String path = "WEB-INF/views/productDetails.jsp";
        req.getRequestDispatcher(path).forward(req,resp);
    }//end doget

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
