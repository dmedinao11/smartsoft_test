package com.smartsoft.test.models;

import com.smartsoft.test.models.util.DetailKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DETALLE")
@Builder
public class Detail {
    private String numDetail;

    @EmbeddedId
    DetailKey id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("invoiceId")
    @JoinColumn(name = "id_factura")
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("productId")
    @JoinColumn(name = "id_producto")
    private Product product;

    @Column(name = "cantidad")
    private Integer quantity;

    @Column(name = "precio")
    private Double price;
}
