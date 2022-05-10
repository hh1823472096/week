package com.huanghong.model;
import java.io.InputStream;

public class Product {
    private  Integer productId;
    private Integer categoryId;
    private  String productName;
    private  String productDescripton;
    private  InputStream picture;
    private Double price;

    public  Product(){

    }
    public Product(Integer categoryId,String productName, String productDescripton,InputStream picture,Double price){
        this.categoryId=categoryId;
        this.productName=productName;
        this.productDescripton=productDescripton;
        this.picture=picture;
        this.price=price;
    }
    public  Integer getProductId(){return this.productId;}
    public void setProductId(Integer productId){this.productId=productId;}
    public  Integer getCategoryId(){return this.categoryId;}
    public void setCategory(Integer categoryId){this.categoryId=categoryId;}
    public String getProductName(){return this.productName;}
    public void setProductName(String productName){this.productName=productName;}
    public String getProductDescripton(){return this.productDescripton;}
    public void setProductDescripton(String productDescripton){this.productDescripton=productDescripton;}
    public InputStream getPicture(){return this.picture;}
    public void setPicture(InputStream picture){this.picture=picture;}
    public Double getPrice(){return this.price;}
    public void setPrice(Double price){this.price=price;}

    @Override
    public  String toString(){
        return "ProductId {productId="+productId+",categoryId="+categoryId
        +",productName="+productName+",productDescription="
        +productDescripton+",picture="+picture+",price="
        +price+"}";
    }
}
