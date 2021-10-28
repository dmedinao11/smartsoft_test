package com.smartsoft.test.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FACTURA")
@Builder
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_factura")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    @Column(name = "fecha")
    private Date date = new Date();

}
