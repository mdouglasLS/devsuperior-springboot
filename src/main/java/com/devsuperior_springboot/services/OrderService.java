package com.devsuperior_springboot.services;

import com.devsuperior_springboot.entities.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final ShippingService shippingService;

    public OrderService(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    public double total(Order order) {
        double shipment = shippingService.shipment(order);

        Double discount = (order.getBasic() * order.getDiscount()) / 100;

        return (order.getBasic() + shipment) - discount;
    }
}
