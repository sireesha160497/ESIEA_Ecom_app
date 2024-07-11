package com.ecom.order;

import com.ecom.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private List<Product> products;
    private List<OrderObserver> observers;
    private double discount;
    private double shippingCost;

    public Order(int orderId) {
        this.orderId = orderId;
        this.products = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.discount = 0;
        this.shippingCost = 10;
    }

    public void addProduct(Product product) {
        boolean productExists = false;
        for (Product p : products) {
            if (p.getProductID() == product.getProductID()) {
                p.setProductQuantity(p.getProductQuantity() + 1);
                productExists = true;
                break;
            }
        }
        if (!productExists) {
            product.setProductQuantity(1);
            products.add(product);
        }
        notifyObservers();
    }

    public int getTotalQuantity() {
        return products.stream().mapToInt(Product::getProductQuantity).sum();
    }

    public double getTotalPrice() {
        return products.stream().mapToDouble(p -> p.getProductPrice() * p.getProductQuantity()).sum();
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (OrderObserver observer : observers) {
            observer.updateOrder(this);
        }
    }

    public void displayOrderDetails() {
        System.out.println("Order ID: " + orderId);
        products.forEach(Product::displayProductDetails);
        System.out.println("Total Quantity: " + getTotalQuantity());
        System.out.println("Total Price: " + getTotalPrice());
        System.out.println("Discount: " + getDiscount());
        System.out.println("Shipping Cost: " + getShippingCost());
        System.out.println("Final Total: " + (getTotalPrice() - getDiscount() + getShippingCost()));
    }
}
