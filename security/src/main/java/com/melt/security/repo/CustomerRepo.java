package com.melt.security.repo;

import com.melt.security.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, Long> {

    Customer findByUsername(String name);
}
