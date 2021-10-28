package com.smartsoft.test.models.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class InvoiceDTO {
    @NotEmpty
    private Long clientId;

    @NotEmpty
    private List<InvoiceProductDTO> productDTOList;
    private Date date;
    private String clientName;
}
