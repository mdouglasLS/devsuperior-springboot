package com.devsuperior_springboot;

import com.devsuperior_springboot.entities.Order;
import com.devsuperior_springboot.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DevsuperiorSpringbootApplication implements CommandLineRunner {

	@Autowired
	private OrderService orderService;

	public static void main(String[] args) {
		SpringApplication.run(DevsuperiorSpringbootApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1034, 150.00, 20.0));
		orders.add(new Order(2282, 800.00, 10.0));
		orders.add(new Order(1309, 95.90, 0.0));

		orders.forEach(order -> {
			System.out.println("Pedido código: " + order.getCode());
			System.out.println("Valor total: R$ " + String.format("%.2f", orderService.total(order)) + "\n");
		});

	}
}
