package com.ecom.product;

import com.ecom.order.Order;

public class QuantityObserverImpl implements QuantityObserver {
    @Override
    public void updateOrder(Order order) {
        if (order.getTotalQuantity() > 5) {
            order.setShippingCost(0);
        } else {
            order.setShippingCost(10);
        }
    }
}
