package com.smartsoft.test.services.impl;

import com.github.javafaker.Faker;
import com.smartsoft.test.models.Client;
import com.smartsoft.test.models.dtos.ClientDTO;
import com.smartsoft.test.repositories.ClientRepository;
import com.smartsoft.test.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final Faker faker = new Faker();

    @PostConstruct
    public void postConstruct(){
        LinkedList<Client> clientsFaker = new LinkedList<>();
        for (int i = 0; i < 10; i++){
            clientsFaker.add(Client.builder()
                            .address(faker.address().fullAddress())
                            .name(faker.name().name())
                            .lastName(faker.name().lastName())
                            .email(faker.bothify("????##@gmail.com"))
                            .phone(faker.phoneNumber().phoneNumber())
                            .birthday(faker.date().birthday())
                    .build()
            );
        }
        clientRepository.saveAll(clientsFaker);
    }

    @Override
    public List<ClientDTO> getAll() {
        List<Client> saveClients = clientRepository.findAll();

        return saveClients.stream().map(client -> ClientDTO.builder().
                id(client.getId()).
                name(client.getName()).
                lastName(client.getLastName()).
                address(client.getAddress()).
                birthday(client.getBirthday()).
                phone(client.getPhone()).
                email(client.getEmail()).build()).collect(Collectors.toList());
    }
}
