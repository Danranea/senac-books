package com.senacbooks.senacbooks;

import com.senacbooks.senacbooks.orders.details.OrderDetailsDTO;
import com.senacbooks.senacbooks.orders.details.OrderDetailsEntity;
import com.senacbooks.senacbooks.orders.details.OrderDetailsRepository;
import com.senacbooks.senacbooks.orders.details.OrderDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SenacbooksApplication implements CommandLineRunner {

	@Autowired
	OrderDetailsRepository orderDetailsRepository;

	@Autowired
	OrderDetailsService orderDetailsService;

	public static void main(String[] args) {
		SpringApplication.run(SenacbooksApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		OrderDetailsEntity entity = orderDetailsRepository.findAll().get(0);
		entity.setId(0l);
		entity.setQuantity(10000);
		orderDetailsService.insert(new OrderDetailsDTO(entity));
	}

}
