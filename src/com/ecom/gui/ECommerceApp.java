package com.ecom.gui;

import com.ecom.product.*;
import com.ecom.order.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ECommerceApp extends JFrame {
    private ProductCatalogue catalogue;
    private DefaultListModel<Product> productListModel;
    private DefaultListModel<Product> cartListModel;
    private Order order;

    private JLabel quantityLabel;
    private JLabel itemPriceLabel;
    private JLabel discountLabel;
    private JLabel shippingPriceLabel;
    private JLabel totalCostLabel;

    public ECommerceApp() {
        catalogue = new ProductCatalogue();
        productListModel = new DefaultListModel<>();
        cartListModel = new DefaultListModel<>();
        order = new Order(1);

        // Adding observers
        order.addObserver(new PriceObserverImpl());
        order.addObserver(new QuantityObserverImpl());

        // Adding sample products
        Product product1 = new Product(101, "Laptop", "High-performance laptop", 1000, 0);
        Product product2 = new Product(102, "Smartphone", "Latest model smartphone", 800, 0);
        catalogue.addProduct(product1);
        catalogue.addProduct(product2);
        productListModel.addElement(product1);
        productListModel.addElement(product2);

        setTitle("E-Commerce Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JList<Product> productList = new JList<>(productListModel);
        JList<Product> cartList = new JList<>(cartListModel);

        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product selectedProduct = productList.getSelectedValue();
                if (selectedProduct != null) {
                    boolean productInCart = false;
                    for (int i = 0; i < cartListModel.size(); i++) {
                        if (cartListModel.getElementAt(i).getProductID() == selectedProduct.getProductID()) {
                            cartListModel.getElementAt(i).setProductQuantity(cartListModel.getElementAt(i).getProductQuantity() + 1);
                            productInCart = true;
                            break;
                        }
                    }
                    if (!productInCart) {
                        Product productToAdd = new Product(
                                selectedProduct.getProductID(),
                                selectedProduct.getProductName(),
                                selectedProduct.getProductDescription(),
                                selectedProduct.getProductPrice(),
                                1);
                        cartListModel.addElement(productToAdd);
                    }
                    order.addProduct(selectedProduct);
                    updateOrderDetails();
                }
            }
        });

        JButton viewOrderButton = new JButton("View Order");
        viewOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                order.displayOrderDetails();
            }
        });

        JPanel productPanel = new JPanel(new BorderLayout());
        productPanel.add(new JLabel("Products"), BorderLayout.NORTH);
        productPanel.add(new JScrollPane(productList), BorderLayout.CENTER);
        productPanel.add(addToCartButton, BorderLayout.SOUTH);

        JPanel cartPanel = new JPanel(new BorderLayout());
        cartPanel.add(new JLabel("Cart"), BorderLayout.NORTH);
        cartPanel.add(new JScrollPane(cartList), BorderLayout.CENTER);
        cartPanel.add(viewOrderButton, BorderLayout.SOUTH);

        JPanel orderDetailsPanel = new JPanel(new GridLayout(5, 2));
        quantityLabel = new JLabel("Total Quantity: 0");
        itemPriceLabel = new JLabel("Item Price: 0.0");
        discountLabel = new JLabel("Discount: 0.0");
        shippingPriceLabel = new JLabel("Shipping Price: 10.0");
        totalCostLabel = new JLabel("Total Cost: 0.0");

        orderDetailsPanel.add(new JLabel("Total Quantity:"));
        orderDetailsPanel.add(quantityLabel);
        orderDetailsPanel.add(new JLabel("Item Price:"));
        orderDetailsPanel.add(itemPriceLabel);
        orderDetailsPanel.add(new JLabel("Discount:"));
        orderDetailsPanel.add(discountLabel);
        orderDetailsPanel.add(new JLabel("Shipping Price:"));
        orderDetailsPanel.add(shippingPriceLabel);
        orderDetailsPanel.add(new JLabel("Total Cost:"));
        orderDetailsPanel.add(totalCostLabel);

        add(productPanel, BorderLayout.WEST);
        add(cartPanel, BorderLayout.EAST);
        add(orderDetailsPanel, BorderLayout.SOUTH);
    }

    private void updateOrderDetails() {
        int totalQuantity = order.getTotalQuantity();
        double totalPrice = order.getTotalPrice();
        double discount = order.getDiscount();
        double shippingCost = order.getShippingCost();
        double totalCost = totalPrice - discount + shippingCost;

        quantityLabel.setText("Total Quantity: " + totalQuantity);
        itemPriceLabel.setText("Item Price: " + totalPrice);
        discountLabel.setText("Discount: " + discount);
        shippingPriceLabel.setText("Shipping Price: " + shippingCost);
        totalCostLabel.setText("Total Cost: " + totalCost);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ECommerceApp().setVisible(true);
            }
        });
    }
}
