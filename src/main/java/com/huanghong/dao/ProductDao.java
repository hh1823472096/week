package com.huanghong.dao;

import com.huanghong.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements  IProductDao{
    private Blob Picture;

    @Override
    public int save(Product product, Connection con) throws SQLException {
        int n;
        String sql = "insert into product(ProductName,ProductDescription,Picture,price,CategoryID) values(?,?,?,?,?)";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setString(1, product.getProductName());
        pt.setString(2, product.getProductDescripton());
        if(Picture!=null) {
            //for sql server
            pt.setBlob(3, Picture);
            //   for mysql
            //   pt.setBlob(3, product.getPicture());
        }
        pt.setDouble(4, product.getPrice());
        pt.setInt(5, product.getCategoryId());
        n = pt.executeUpdate();
        return Math.max(n, 0);
    }//end save

    @Override
    public int delete(Integer productId, Connection con) {
        return 0;
    }

    @Override
    public int update(Product instance, Connection con) {
        return 0;
    }

    @Override
    public Product findById(Integer productId, Connection con) throws SQLException {
        String queryString = "select * from product where productId=?";
        PreparedStatement preparedStatement = con.prepareStatement(queryString);
        preparedStatement.setInt(1,productId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Product product = new Product();
        while(resultSet.next()){
            product.setProductId(resultSet.getInt("ProductId"));
            product.setProductName(resultSet.getString("ProductName"));
            product.setProductDescripton(resultSet.getString("ProductDescription"));
            product.setPrice(resultSet.getDouble("Price"));
            product.setCategory(resultSet.getInt("CategoryId"));
        }
        return product;
    }

    @Override
    public List<Product> findByCategoryId(int categoryId, Connection con) {
        return null;
    }

    @Override
    public List<Product> findByPrice(double minPrice, double maxPrice, Connection con) throws SQLException {
        return null;
    }

    @Override
    public List<Product> findAll(Connection con) throws SQLException {
        List<Product> list = new ArrayList<>();
        String queryString = "select * from product";
        PreparedStatement preparedStatement = con.prepareStatement(queryString);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Product product = new Product();
            product.setProductId(resultSet.getInt("ProductId"));
            product.setProductName(resultSet.getString("ProductName"));
            product.setProductDescripton(resultSet.getString("ProductDescription"));
            product.setPrice(resultSet.getDouble("Price"));
            product.setCategory(resultSet.getInt("CategoryId"));
            list.add(product);
        }
        System.out.print("successful");
        return list;
    }

    @Override
    public List<Product> findByProductName(String productName, Connection con) throws SQLException {
        return null;
    }

    @Override
    public byte[] getPicture(Integer productId, Connection con) throws SQLException {
        return null;
    }

    @Override
    public byte[] getPictureById(Integer productId, Connection con) throws SQLException {
        byte[] imgByte = null;
        String sql = "select picture from product where productId=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1,productId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Blob picture = resultSet.getBlob("picture");
            imgByte = picture.getBytes(1L, (int) picture.length());
        }
        return imgByte;
    }
}