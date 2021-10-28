package com.smartsoft.test.repositories;

import com.smartsoft.test.models.Client;
import com.smartsoft.test.models.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
}
