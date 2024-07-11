package com.ecom.product;

public class ConcreteQuantityObserver implements QuantityObserver {
    @Override
    public void updateProductQuantity(Product product, int quantity) {
        product.setProductQuantity(quantity);
        System.out.println("Quantity of product " + product.getProductName() + " updated to: " + quantity);
    }
}
