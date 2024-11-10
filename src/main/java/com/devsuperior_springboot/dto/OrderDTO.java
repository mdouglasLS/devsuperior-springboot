package com.devsuperior_springboot.dto;

import com.devsuperior_springboot.entities.Order;
import com.devsuperior_springboot.entities.OrderItem;
import com.devsuperior_springboot.entities.OrderStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long id;
    private Instant moment;
    private OrderStatus status;
    private ClientDTO client;
    private PaymentDTO payment;

    @NotEmpty(message = "Deve ter pelo menos um item")
    private List<OrderItemDTO> items = new ArrayList<>();

    public OrderDTO(Order order) {
        id = order.getId();
        moment = order.getMoment();
        status = order.getStatus();
        client = new ClientDTO(order.getClient());
        payment = (order.getPayment() == null) ? null : new PaymentDTO(order.getPayment());
        for(OrderItem item : order.getItems()) {
            items.add(new OrderItemDTO(item));
        }
    }

    public Double getTotal() {
        double sum = 0.0;
        for (OrderItemDTO item : items) {
            sum += item.getSubTotal();
        }
        return sum;
    }

}
