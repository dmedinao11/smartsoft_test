package com.smartsoft.test.services.impl;

import com.smartsoft.test.models.Invoice;
import com.smartsoft.test.repositories.InvoiceRepository;
import com.smartsoft.test.services.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Override
    public Invoice saveInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }
}
