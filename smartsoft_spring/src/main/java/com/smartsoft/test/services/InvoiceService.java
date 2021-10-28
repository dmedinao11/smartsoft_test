package com.smartsoft.test.services;

import com.smartsoft.test.models.Invoice;
import com.smartsoft.test.models.dtos.InvoiceDTO;

public interface InvoiceService {
    Invoice saveInvoice(Invoice invoice);
}
