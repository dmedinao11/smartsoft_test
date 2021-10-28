package com.smartsoft.test.controllers;

import com.smartsoft.test.models.dtos.ProductDTO;
import com.smartsoft.test.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("all")
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllWithStock(), HttpStatus.OK);
    };
}
