package com.accenture.be.repository;

import com.accenture.be.entity.customer.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 * Репозиторий для работы с сущностями Customer
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
