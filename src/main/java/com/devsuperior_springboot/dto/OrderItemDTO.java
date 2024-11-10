package com.devsuperior_springboot.dto;

import com.devsuperior_springboot.entities.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {

    private Long productId;
    private String name;
    private Double price;
    private Integer quantity;

    public OrderItemDTO(OrderItem orderItem) {
        productId = orderItem.getProduct().getId();
        name = orderItem.getProduct().getName();
        price = orderItem.getPrice();
        quantity = orderItem.getQuantity();
    }

    public Double getSubTotal() {
        return price * quantity;
    }
}
