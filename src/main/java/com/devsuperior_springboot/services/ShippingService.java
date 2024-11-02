package com.devsuperior_springboot.services;

import com.devsuperior_springboot.entities.Order;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    public double shipment(Order order) {
        Double basic = order.getBasic();

        if (basic < 100.00) {
            return 20.00;
        } else if (basic >= 100.00 && basic <= 200.00) {
            return 12.00;
        }

        return 0.0;
    }
}
