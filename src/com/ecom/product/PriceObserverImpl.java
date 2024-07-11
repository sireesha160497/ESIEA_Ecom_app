package com.ecom.product;

import com.ecom.order.Order;

public class PriceObserverImpl implements PriceObserver {
    @Override
    public void updateOrder(Order order) {
        if (order.getTotalPrice() > 200) {
            order.setDiscount(20);
        } else {
            order.setDiscount(0);
        }
    }
}
