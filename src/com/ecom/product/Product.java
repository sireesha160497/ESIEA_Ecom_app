package com.ecom.product;

public class Product {
    private int productID;
    private String productName;
    private String productDescription;
    private double productPrice;
    private int productQuantity;

    public Product(int productID, String productName, String productDescription, double productPrice, int productQuantity) {
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void displayProductDetails() {
        System.out.println("Product ID: " + productID);
        System.out.println("Product Name: " + productName);
        System.out.println("Product Description: " + productDescription);
        System.out.println("Product Price: " + productPrice);
        System.out.println("Product Quantity: " + productQuantity);
    }

    @Override
    public String toString() {
        return productName + " - $" + productPrice;
    }
}
