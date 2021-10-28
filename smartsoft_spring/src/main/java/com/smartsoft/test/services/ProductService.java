package com.smartsoft.test.services;

import com.smartsoft.test.models.Product;
import com.smartsoft.test.models.dtos.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllWithStock();
    Product getById(Long id);
}
