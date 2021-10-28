package com.smartsoft.test.models.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ClientDTO {
    private Long id;
    private String name;
    private String lastName;
    private String address;
    private Date birthday;
    private String phone;
    private String email;
}
