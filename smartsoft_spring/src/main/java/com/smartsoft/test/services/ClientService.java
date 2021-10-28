package com.smartsoft.test.services;

import com.smartsoft.test.models.dtos.ClientDTO;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getAll();
}
