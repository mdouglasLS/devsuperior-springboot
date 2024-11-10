package com.devsuperior_springboot.services;

import com.devsuperior_springboot.dto.OrderDTO;
import com.devsuperior_springboot.entities.Order;
import com.devsuperior_springboot.repositories.OrderRepository;
import com.devsuperior_springboot.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public OrderDTO findById(Long id) {
        Order order = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));

        return new OrderDTO(order);
    }
}
