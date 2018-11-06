package com.accenture.be.repository;

import com.accenture.be.entity.customer.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
