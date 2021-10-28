package com.smartsoft.test.repositories;

import com.smartsoft.test.models.Client;
import com.smartsoft.test.models.Detail;
import com.smartsoft.test.models.util.DetailKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailRepository extends CrudRepository<Detail, DetailKey> {

}
