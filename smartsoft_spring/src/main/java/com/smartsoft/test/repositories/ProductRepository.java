package com.smartsoft.test.repositories;

import com.smartsoft.test.models.Client;
import com.smartsoft.test.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllByStockGreaterThan(Integer stock);
}
