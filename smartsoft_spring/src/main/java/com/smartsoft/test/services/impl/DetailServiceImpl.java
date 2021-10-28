package com.smartsoft.test.services.impl;

import com.smartsoft.test.models.Client;
import com.smartsoft.test.models.Detail;
import com.smartsoft.test.models.Invoice;
import com.smartsoft.test.models.dtos.InvoiceDTO;
import com.smartsoft.test.models.dtos.InvoiceProductDTO;
import com.smartsoft.test.models.dtos.ProductDTO;
import com.smartsoft.test.models.util.DetailKey;
import com.smartsoft.test.repositories.DetailRepository;
import com.smartsoft.test.services.DetailService;
import com.smartsoft.test.services.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class DetailServiceImpl implements DetailService {

    private final InvoiceService invoiceService;
    private final DetailRepository detailRepository;

    @Override
    public InvoiceDTO saveInvoiceWithAllInfo(InvoiceDTO invoiceDTO) {
        Invoice savedInvoice = saveInvoice(invoiceDTO);
        List<Detail> detailsToSave = buildDetailsToSave(invoiceDTO, savedInvoice);
        List<Detail> savedDetails = (List<Detail>) detailRepository.saveAll(detailsToSave);
        return buildDto(savedDetails, savedInvoice);
    }

    private Invoice saveInvoice(InvoiceDTO invoiceDTO) {
        Client client = Client.builder().id(invoiceDTO.getClientId()).build();
        Invoice newInvoice = Invoice.builder().client(client).build();
        return invoiceService.saveInvoice(newInvoice);
    }

    private List<Detail> buildDetailsToSave(InvoiceDTO invoiceDTO, Invoice invoice) {
        return invoiceDTO.getProductDTOList()
                .stream()
                .map(productInInvoice -> {
                    DetailKey detailCompositeKey = DetailKey.builder()
                            .invoiceId(invoice.getId())
                            .productId(productInInvoice.getProductId())
                            .build();
                    return Detail.builder().id(detailCompositeKey)
                            .quantity(productInInvoice.getQuantity())
                            .build();
                })
                .collect(Collectors.toList());
    }

    private InvoiceDTO buildDto(List<Detail> savedDetails, Invoice invoice) {
        List<InvoiceProductDTO> productsInInvoice = savedDetails
                .stream().map(detail -> InvoiceProductDTO.builder()
                        .price(detail.getPrice())
                        .productName(detail.getProduct().getName()).build())
                .collect(Collectors.toList());

        return InvoiceDTO.builder()
                .clientId(invoice.getClient().getId())
                .clientName(invoice.getClient().getName())
                .date(invoice.getDate())
                .productDTOList(productsInInvoice)
                .build();
    }
}
