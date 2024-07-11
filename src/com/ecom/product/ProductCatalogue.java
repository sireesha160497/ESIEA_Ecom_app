package com.ecom.product;

import java.util.ArrayList;
import java.util.List;

public class ProductCatalogue {
    private List<Product> products;

    public ProductCatalogue() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void displayProductDetails() {
        for (Product product : products) {
            product.displayProductDetails();
        }
    }
}
