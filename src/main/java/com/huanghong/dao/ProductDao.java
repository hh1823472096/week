package com.huanghong.dao;

import com.huanghong.model.Product;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements IProductDao{
    @Override
    public int save(Product product, Connection con) throws SQLException {
        int n;
        String sql = "insert into product(ProductName,ProductDescription,Picture,price,CategoryId) values(?,?,?,?,?)";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setString(1, product.getProductName());
        pt.setString(2, product.getProductDescripton());
        if(product.getPicture()!=null) {
            pt.setBinaryStream(3, product.getPicture());
        }
        pt.setDouble(4, product.getPrice());
        pt.setInt(5, product.getCategoryId());
        n = pt.executeUpdate();
        return Math.max(n, 0);
    }//end save

    @Override
    public int delete(Integer productId, Connection con) throws SQLException {
        int n;
        String sql = "delete from product where ProductID = ?";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setInt(1, productId);
        n = pt.executeUpdate();
        return Math.max(n, 0);
    }

    @Override
    public int update(Product instance, Connection con) throws SQLException {
        String sql = "update product set ProductName = ?, ProductDescription = ?, Picture = ?, price = ?, CategoryId = ? where productId = ?";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setString(1, instance.getProductName());
        pt.setString(2, instance.getProductDescripton());
        if(instance.getPicture()!=null) {
            //for sql server
            pt.setBinaryStream(3, instance.getPicture());
            //for mysql
            //   pt.setBlob(3, product.getPicture());
        }
        pt.setDouble(4, instance.getPrice());
        pt.setInt(5, instance.getCategoryId());
        return 0;
    }

    @Override
    public Product findById(Integer productId, Connection con) throws SQLException {
        String sql = "select * from product where productId = ?";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setInt(1, productId);
        ResultSet rs = pt.executeQuery();
        Product product = null;
        if (rs.next()){
            product = new Product();
            product.setProductId(productId);
            product.setProductName(rs.getString("productName"));
            product.setCategory(rs.getInt("categoryId"));
            product.setProductDescripton(rs.getString("productDescription"));
            product.setPrice(rs.getDouble("price"));
            product.setPicture(rs.getBinaryStream("picture"));
        }

        return product;
    }

    @Override
    public List<Product> findByCategoryId(int categoryId, Connection con) throws SQLException {
        String sql = "select * from product where categoryId = ?";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setInt(1, categoryId);
        ResultSet rs = pt.executeQuery();
        List<Product> list = new ArrayList<>();
        while (rs.next()){
            Product product = new Product();
            product.setProductId(rs.getInt("productId"));
            product.setProductName(rs.getString("productName"));
            product.setCategory(categoryId);
            product.setProductDescripton(rs.getString("productDescription"));
            product.setPrice(rs.getDouble("price"));
            product.setPicture(rs.getBinaryStream("picture"));
            list.add(product);
        }
        return list;
    }

    @Override
    public List<Product> findByPrice(double minPrice, double maxPrice, Connection con) throws SQLException {
        String sql = "select * from product where product.Price >= ? and product.Price <= ?";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setDouble(1, minPrice);
        pt.setDouble(2, maxPrice);
        ResultSet rs = pt.executeQuery();
        List<Product> list = new ArrayList<>();
        while (rs.next()){
            Product product = new Product();
            product.setProductId(rs.getInt("productId"));
            product.setProductName(rs.getString("productName"));
            product.setCategory(rs.getInt("categoryId"));
            product.setProductDescripton(rs.getString("productDescription"));
            product.setPrice(rs.getDouble("price"));
            product.setPicture(rs.getBinaryStream("picture"));
            list.add(product);
        }
        return list;
    }

    @Override
    public List<Product> findAll(Connection con) throws SQLException {
        String sql = "select * from product";
        PreparedStatement pt = con.prepareStatement(sql);
        ResultSet rs = pt.executeQuery();
        List<Product> list = new ArrayList<>();
        while (rs.next()){
            Product product = new Product();
            product.setProductId(rs.getInt("productId"));
            product.setProductName(rs.getString("productName"));
            product.setProductId(rs.getInt("categoryId"));
            product.setProductDescripton(rs.getString("productDescription"));
            product.setPrice(rs.getDouble("price"));
            product.setPicture(rs.getBinaryStream("picture"));
            list.add(product);
        }
        return list;
    }

    @Override
    public List<Product> findByProductName(String productName, Connection con) throws SQLException {
        String sql = "select * from product where productName = ?";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setString(1, productName);
        ResultSet rs = pt.executeQuery();
        List<Product> list = new ArrayList<>();
        while (rs.next()){
            Product product = new Product();
            product.setProductId(rs.getInt("productId"));
            product.setProductName(rs.getString("productName"));
            product.setProductId(rs.getInt("categoryId"));
            product.setProductDescripton(rs.getString("productDescription"));
            product.setPrice(rs.getDouble("price"));
            product.setPicture(rs.getBinaryStream("picture"));
            list.add(product);
        }
        return list;
    }

    @Override
    public InputStream getPicture(Integer productId, Connection con) throws SQLException {
        String sql = "select picture from product where productId = ?";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setInt(1, productId);
        ResultSet rs = pt.executeQuery();
        return rs.getBinaryStream("picture");
    }
}