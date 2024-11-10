package com.devsuperior_springboot.repositories;

import com.devsuperior_springboot.entities.OrderItem;
import com.devsuperior_springboot.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
