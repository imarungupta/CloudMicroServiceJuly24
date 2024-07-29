package com.sis.customer.service.api.repository;

import com.sis.customer.service.api.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
