package com.smartsoft.test.models.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InvoiceProductDTO {
    private Integer quantity;
    private Double price;
    private String productName;
    private Long productId;
}
