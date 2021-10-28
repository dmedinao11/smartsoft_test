package com.smartsoft.test.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CLIENTE")
@Builder
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "apellido")
    private String lastName;

    @Column(name = "dirección")
    private String address;

    @Column(name = "fecha_nacimiento")
    private Date birthday;

    @Column(name = "telefono")
    private String phone;

    private String email;
}
