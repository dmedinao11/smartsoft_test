package com.smartsoft.test.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceProductDTO {
    private Integer quantity;
    private Double price;
    private String productName;
    private Long productId;
}
