package com.devsuperior_springboot.services;

import com.devsuperior_springboot.dto.OrderDTO;
import com.devsuperior_springboot.dto.OrderItemDTO;
import com.devsuperior_springboot.entities.Order;
import com.devsuperior_springboot.entities.OrderItem;
import com.devsuperior_springboot.entities.OrderStatus;
import com.devsuperior_springboot.entities.Product;
import com.devsuperior_springboot.repositories.OrderItemRepository;
import com.devsuperior_springboot.repositories.OrderRepository;
import com.devsuperior_springboot.repositories.ProductRepository;
import com.devsuperior_springboot.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Transactional(readOnly = true)
@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public OrderDTO findById(Long id) {
        Order order = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));

        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto) {
        Order order = copyDtoToEntity(dto);

        repository.save(order);
        orderItemRepository.saveAll(order.getItems());

        return new OrderDTO(order);
    }

    private Order copyDtoToEntity(OrderDTO dto) {
        Order order = new Order();
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);
        order.setClient(userService.authenticated());

        for (OrderItemDTO itemDTO : dto.getItems()) {
            Product product = productRepository.getReferenceById(itemDTO.getProductId());
            OrderItem item = new OrderItem(order, product, itemDTO.getQuantity(), product.getPrice());
            order.getItems().add(item);
        }

        return order;
    }
}
