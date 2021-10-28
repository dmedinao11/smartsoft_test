package com.smartsoft.test.repositories;

import com.smartsoft.test.models.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client,Long> {
    List<Client> findAll();
}
