package com.huanghong.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Category implements Serializable{

    private int categoryId;
    private String categoryName;
    private String description;
    private String active;

    public Category() {
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
    public Category(String categoryName, String description, String active) {
        this.categoryName = categoryName;
        this.description = description;
        this.active = active;
    }
    public int getCategoryId(){return this.categoryId;}
    public void setCategoryId(int categoryId){this.categoryId=categoryId;}
    public String getCategoryName(){return this.categoryName;}
    public void setCategoryName(String categoryName){this.categoryName=categoryName;}
    public String getDescription(){return this.description;}
    public void setDescription(String description){this.description=description;}
    public String getActive(){return  this.active;}
    public void setActive(String active){this.active=active;}




    public static List<Category> findAllCategory(Connection con){
        List<Category> list = new ArrayList<>();
        try {
            String queryString = "select * from Category";
            PreparedStatement pt = con.prepareStatement(queryString);
            ResultSet rs = pt.executeQuery();
            while (rs.next()){
                Category c = new Category();
                c.setCategoryId(rs.getInt("categoryId"));
                c.setCategoryName(rs.getString("categoryName"));
                c.setDescription(rs.getString("description"));
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }



    public static String findByCategoryId(Connection con, int categoryId){
        String categoryName = null;
        String queryString = "select CategoryName from Category where categoryId=?";
        try {
            PreparedStatement statement = con.prepareStatement(queryString);
            statement.setInt(1,categoryId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                categoryName = resultSet.getString("categoryName");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoryName;
    }


}
