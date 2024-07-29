package com.sis.customer.service.api.service;

import com.sis.customer.service.api.entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer saveCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    List<Customer> getAllCustomer();
    Customer getCustomer(String customerId);
    String deleteCustomer(String customerId);

}
