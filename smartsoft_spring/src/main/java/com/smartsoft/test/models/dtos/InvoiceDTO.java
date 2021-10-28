package com.smartsoft.test.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDTO {
    @NotEmpty
    private Long clientId;

    @NotEmpty
    private List<InvoiceProductDTO> productDTOList;
    private Date date;
    private String clientName;
}
