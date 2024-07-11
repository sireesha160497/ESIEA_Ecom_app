package com.ecom.order;

import com.ecom.product.Product;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderID;
    private List<Product> products;
    private double totalPrice;
    private double shippingCost;

    public Order(int orderID) {
        this.orderID = orderID;
        this.products = new ArrayList<>();
        this.totalPrice = 0.0;
        this.shippingCost = 10.0;
    }

    public int getOrderID() {
        return orderID;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void addProduct(Product product) {
        products.add(product);
        totalPrice += product.getProductPrice();
    }

    public void applyPriceDiscount() {
        if (totalPrice > 200) {
            totalPrice -= 20;
        }
    }

    public void updateShippingCost() {
        int totalQuantity = products.stream().mapToInt(Product::getProductQuantity).sum();
        if (totalQuantity > 5) {
            shippingCost = 0;
        } else {
            shippingCost = 10;
        }
    }

    public void displayOrderDetails() {
        System.out.println("Order ID: " + orderID);
        for (Product product : products) {
            product.displayProductDetails();
        }
        System.out.println("Total Price: " + totalPrice);
        System.out.println("Shipping Cost: " + shippingCost);
    }
}
