package com.smartsoft.test.services;

import com.smartsoft.test.models.dtos.InvoiceDTO;

public interface DetailService {
    InvoiceDTO saveInvoiceWithAllInfo(InvoiceDTO invoiceDTO);
}
