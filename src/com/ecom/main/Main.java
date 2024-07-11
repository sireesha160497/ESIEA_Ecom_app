package com.ecom.main;

import com.ecom.user.User;
import com.ecom.product.*;
import com.ecom.order.*;

public class Main {
    public static void main(String[] args) {
        // User login
        User user = new User(1, "sireesha", "password123");
        user.login("sireesha", "password123");

        // Product and observers
        Product product1 = new Product(101, "Laptop", "High-performance laptop", 1000, 2);
        Product product2 = new Product(102, "Smartphone", "Latest model smartphone", 800, 4);

        PriceObserver priceObserver = new ConcretePriceObserver();
        QuantityObserver quantityObserver = new ConcreteQuantityObserver();

        // Product Catalogue
        ProductCatalogue catalogue = new ProductCatalogue(product1);
        catalogue.viewProductDetails();

        // Update product price
        priceObserver.updateProduct(product1, 950);
        quantityObserver.updateProductQuantity(product1, 3);

        // View updated product details
        catalogue.viewProductDetails();

        // Order
        Order order = new Order(1);
        order.addProduct(product1);
        order.addProduct(product2);

        // Apply discounts and update shipping cost
        order.applyPriceDiscount();
        order.updateShippingCost();

        // Display order details
        order.displayOrderDetails();
    }
}
