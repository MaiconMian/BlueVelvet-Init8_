package com.bluevelvet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.bluevelvet.model.*;
import com.bluevelvet.repository.*;
import com.bluevelvet.service.*;

import java.time.LocalDateTime;

@SpringBootTest
class BlueVelvetApplicationTests {

	@Autowired
	private ProductRepository productRepository;

	@BeforeEach
	void setUp() {

		Product product1 = new Product();
		product1.setName("Produto de Teste 1");
		product1.setShortDescription("Descrição curta do Produto 1");
		product1.setLongDescription("Descrição longa detalhando o Produto de Teste 1");
		product1.setPrice(150.0f);
		product1.setDiscount(5.0f);
		product1.setStatus(true);
		product1.setHasStock(true);
		product1.setWidht(10.0f);
		product1.setLenght(20.0f);
		product1.setHeight(30.0f);
		product1.setCost(100.0f);
		product1.setCreationTime(LocalDateTime.now());
		product1.setUpdateTime(LocalDateTime.now());

		Product product2 = new Product();
		product2.setName("Produto de Teste 2");
		product2.setShortDescription("Descrição curta do Produto 2");
		product2.setLongDescription("Descrição longa detalhando o Produto de Teste 2");
		product2.setPrice(200.0f);
		product2.setDiscount(10.0f);
		product2.setStatus(true);
		product2.setHasStock(true);
		product2.setWidht(15.0f);
		product2.setLenght(25.0f);
		product2.setHeight(35.0f);
		product2.setCost(150.0f);
		product2.setCreationTime(LocalDateTime.now());
		product2.setUpdateTime(LocalDateTime.now());

		productRepository.save(product1);
		productRepository.save(product2);

	}

}
