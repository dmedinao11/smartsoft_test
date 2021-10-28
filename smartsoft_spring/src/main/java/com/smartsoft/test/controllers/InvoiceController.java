package com.smartsoft.test.controllers;

import com.smartsoft.test.models.dtos.InvoiceDTO;
import com.smartsoft.test.services.DetailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/invoice")
public class InvoiceController {

    private final DetailService detailService;

    @PostMapping
    public ResponseEntity<InvoiceDTO> saveInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        InvoiceDTO savedInvoice = detailService.saveInvoiceWithAllInfo(invoiceDTO);
        return new ResponseEntity<>(savedInvoice, HttpStatus.CREATED);
    }

    ;
}
