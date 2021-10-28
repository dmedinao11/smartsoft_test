package com.smartsoft.test.controllers;

import com.smartsoft.test.models.dtos.ClientDTO;
import com.smartsoft.test.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/client")
public class ClientController {
    private final ClientService clientService;

    @GetMapping("all")
    public ResponseEntity<List<ClientDTO>> getAllClients(){
        return new ResponseEntity<>(clientService.getAll(), HttpStatus.OK);
    };
}
