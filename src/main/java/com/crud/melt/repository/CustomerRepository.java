package com.crud.melt.repository;

import com.crud.melt.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Customer} class.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
