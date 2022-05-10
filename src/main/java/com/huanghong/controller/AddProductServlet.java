package com.huanghong.controller;

import com.huanghong.dao.ProductDao;
import com.huanghong.model.Category;
import com.huanghong.model.Product;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;


@WebServlet(name = "AddProductServlet", value = "/admin/addProduct")
@MultipartConfig(maxFileSize = 16177215)
public class AddProductServlet extends HttpServlet {
    private Connection con = null;
    private static final Logger log = Logger.getLogger(String.valueOf(AddProductServlet.class));

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        con = (Connection) getServletContext().getAttribute("conn");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoryList = Category.findAllCategory(con);
        request.setAttribute("categoryList", categoryList);
        String path = "/WEB-INF/views/admin/addProduct.jsp";
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get parameters
        String productName = request.getParameter("productName");
        double price = request.getParameter("price")!=null?Double.parseDouble(request.getParameter("price")):0.0;
        int categoryId = request.getParameter("categoryId")!=null?Integer.parseInt(request.getParameter("categoryId")):0;
        String productDescription = request.getParameter("productDescription");
        // picture
        InputStream inputStream = null;
        Part filePart = request.getPart("picture");
        if (filePart != null){
            System.out.println("file name :" + filePart.getName() + "size" + filePart.getSize() + "file type" + filePart.getContentType());
            inputStream = filePart.getInputStream();
        }

        // set in model
        Product product = new Product();
        product.setProductName(productName);
        product.setPrice(price);
        product.setPicture(inputStream);
        product.setProductDescripton(productDescription);
        product.setProductId(categoryId);

        // call same in dao
        ProductDao productDao = new ProductDao();
        try {
            int n = productDao.save(product, con);
            if (n>0){
                response.sendRedirect("productList");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}