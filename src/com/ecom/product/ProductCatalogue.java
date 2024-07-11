package com.ecom.product;

public class ProductCatalogue {
    private Product product;

    public ProductCatalogue(Product product) {
        this.product = product;
    }

    public void viewProductDetails() {
        product.displayProductDetails();
    }
}
