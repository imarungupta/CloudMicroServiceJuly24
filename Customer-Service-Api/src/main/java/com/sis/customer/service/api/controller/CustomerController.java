package com.sis.customer.service.api.controller;

import com.sis.customer.service.api.entity.Customer;
import com.sis.customer.service.api.service.CustomerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {



    //@Autowired
    private CustomerService customerService;
    //Constructor DI
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping("/customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){

        Customer newCustomer = customerService.saveCustomer(customer);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("customer/{customerId}").buildAndExpand(customer.getCustomerId()).toUri();
        System.out.println(uri);
        HttpHeaders headers= new HttpHeaders();
        headers.setLocation(uri);
        //return ResponseEntity.status(headers,HttpStatus.CREATED).body(newCustomer);
        newCustomer.setUri(headers.getLocation());
         return new ResponseEntity<>(newCustomer,headers,HttpStatus.CREATED);

    }
    @PutMapping("/customer")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){

        Customer updateCustomer = customerService.updateCustomer(customer);
        return new ResponseEntity<>(updateCustomer,HttpStatus.OK);
    }
    @GetMapping("/customer")
   public ResponseEntity<List<Customer>> getAllCustomer(){
        List<Customer> allCustomer = customerService.getAllCustomer();

        return new ResponseEntity<>(allCustomer,HttpStatus.OK);
    }
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Customer> getSingleCustomer(@PathVariable String customerId){
        Customer customer = customerService.getCustomer(customerId);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }
    @DeleteMapping("/customer/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String customerId){
        String deleteMessage = customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(deleteMessage,HttpStatus.OK);
    }
}
