package com.smartsoft.test.services.impl;

import com.github.javafaker.Faker;
import com.smartsoft.test.models.Client;
import com.smartsoft.test.models.Product;
import com.smartsoft.test.models.dtos.ProductDTO;
import com.smartsoft.test.repositories.ProductRepository;
import com.smartsoft.test.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

    private final Faker faker = new Faker();

    @PostConstruct
    public void postConstruct() {
        LinkedList<Product> productsFaker = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            productsFaker.add(
                    Product.builder()
                            .name(faker.food().ingredient()).stock(100)
                            .price(faker.number().randomDouble(2, 10, 20))
                            .build()
            );
        }
        productRepository.saveAll(productsFaker);
    }

    @Override
    public List<ProductDTO> getAllWithStock() {
        List<Product> savedProducts = productRepository.findAllByStockGreaterThan(0);

        return savedProducts.stream().map(product -> ProductDTO.builder().id(product.getId()).
                name(product.getName()).
                price(product.getPrice()).
                stock(product.getStock()).build()).collect(Collectors.toList());
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
