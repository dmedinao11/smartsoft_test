package com.smartsoft.test.models.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailKey implements Serializable {
    @Column(name = "id_factura")
    private Long invoiceId;

    @Column(name = "id_producto")
    private Long productId;
}
