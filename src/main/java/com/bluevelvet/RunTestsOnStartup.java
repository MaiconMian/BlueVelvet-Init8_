package com.bluevelvet;

import com.bluevelvet.model.*;
import com.bluevelvet.repository.BrandRepository;
import com.bluevelvet.repository.CategoryRepository;
import com.bluevelvet.repository.ProductRepository;
import com.bluevelvet.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//@Component
public class RunTestsOnStartup implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private RoleRepository roleRepository;

//    @Override
    public void run(String... args) throws Exception {
        Product product1 = new Product();
        product1.setName("Produto de Teste 1");
        product1.setShortDescription("Descrição curta do Produto 1");
        product1.setLongDescription("Descrição longa detalhando o Produto de Teste 1");
        product1.setPrice(150.0f);
        product1.setDiscount(5.0f);
        product1.setStatus(true);
        product1.setHasStock(true);
        product1.setWidth(10.0f);
        product1.setLength(20.0f);
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
        product2.setWidth(15.0f);
        product2.setLength(25.0f);
        product2.setHeight(35.0f);
        product2.setCost(150.0f);
        product2.setCreationTime(LocalDateTime.now());
        product2.setUpdateTime(LocalDateTime.now());

        Brand brand = new Brand();
        brand.setBrandName("RGV");
        brandRepository.save(brand);

        Category category = new Category();
        category.setCategoryName("Discos de Vinil");
        categoryRepository.save(category);

        Role role1 = new Role();
        role1.setName("ADMIN");
        role1.setDescription("A role for administrator in BV");

        Role role2 = new Role();
        role2.setName("EDITOR");
        role2.setDescription("A role for editor in BV");

        Role role3 = new Role();
        role3.setName("USER");
        role3.setDescription("A role for user in BV");

        roleRepository.save(role1);
        roleRepository.save(role2);
        roleRepository.save(role3);

        product1.setBrand(brand);
        product2.setBrand(brand);
        productRepository.save(product1);
        productRepository.save(product2);

//        User user = new User();
//        user.setName("myuser");
//        user.setEmail("test@gmail.com");
//        user.setPassword();
    }
}
