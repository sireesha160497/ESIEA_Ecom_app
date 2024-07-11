package com.ecom.product;

public class ConcretePriceObserver implements PriceObserver {
    @Override
    public void updateProduct(Product product, double price) {
        product.setProductPrice((int) price);
        System.out.println("Price of product " + product.getProductName() + " updated to: " + price);
    }
}
