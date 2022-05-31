package com.huanghong.controller;

import com.huanghong.dao.ProductDao;
import com.huanghong.model.Item;
import com.huanghong.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private Connection con = null;

    @Override
    public void init() throws ServletException {
        con = (Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session != null && session.getAttribute("user") != null){
            if(req.getParameter("action")==null){
                displayCart(req,resp);
            }else if(req.getParameter("action").equals("add")){
                try{
                    buy(req,resp);
                }catch (SQLException e){
                    e.printStackTrace();
                }
            } else if (req.getParameter("action").equals("remove")) {
                remove(req,resp);
            }else{
                resp.sendRedirect("login");
            }
        }
    }

    private void remove(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        int id = 0;
        if(req.getParameter("productId") != null){
            id = Integer.parseInt(req.getParameter("productId"));
        }
        int index = isExisting(id,cart);
        cart.remove(index);
        session.setAttribute("cart",cart);
        resp.sendRedirect(req.getContextPath()+"/cart");
    }

    private void buy(HttpServletRequest req,HttpServletResponse resp) throws SQLException, IOException {
        HttpSession session = req.getSession();
        int id = req.getParameter("productId")!=null? Integer.parseInt(req.getParameter("productId")):0;
        int quantityParam = req.getParameter("quantity")!=null? Integer.parseInt(req.getParameter("quantity")) :1;
        ProductDao dao = new ProductDao();
        if(session.getAttribute("cart") == null){
            //new Cart
            List<Item> cart = new ArrayList<>();
            Product p = dao.findById(id, con);
            cart.add(new Item(p,quantityParam));
            session.setAttribute("cart",cart);
        }else{
            //existing Cart
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            int index = isExisting(id,cart);
            if(index == -1){
                //add new Item
                cart.add(new Item(dao.findById(id,con),1));
            }else{
                //add quantity of exiting item
                int quantity = cart.get(index).getQuantity()+1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart",cart);
        }
        resp.sendRedirect(req.getContextPath()+"/cart");
    }

    private int isExisting(int id,List<Item> cart){
        for(int i = 0;i < cart.size(); i++){
            if(cart.get(i).getProduct().getProductId()==id){
                return i;
            }
        }
        return -1;
    }

    private void displayCart(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message","Your Cart");
        req.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

}
