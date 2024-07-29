package com.sis.customer.service.api.service.impl;

import com.sis.customer.service.api.FeignClientForExternalService.FeignHotelService;
import com.sis.customer.service.api.FeignClientForExternalService.FeignRatingService;
import com.sis.customer.service.api.entity.Customer;
import com.sis.customer.service.api.entity.Hotel;
import com.sis.customer.service.api.entity.Rating;
import com.sis.customer.service.api.exception.ResourceNotFoundException;
import com.sis.customer.service.api.repository.CustomerRepository;
import com.sis.customer.service.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    //@Autowired
    private CustomerRepository repository;
    private RestTemplate restTemplate;
    private FeignHotelService hotelFeignClient;
    private FeignRatingService feignRatingClient;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository, RestTemplate restTemplate, FeignHotelService hotelFeignClient,FeignRatingService feignRatingClient) {
        this.repository = repository;
        this.restTemplate=restTemplate;
        this.hotelFeignClient=hotelFeignClient;
        this.feignRatingClient=feignRatingClient;
    }


    @Override
    public Customer saveCustomer(Customer customer) {
        String randomUserId = UUID.randomUUID().toString();
        customer.setCustomerId(randomUserId);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("customer/"+customer.getCustomerId()).buildAndExpand(customer.getCustomerId()).toUri();
        System.out.println(uri);
        customer.setUri(uri);

        return repository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return repository.findAll();
    }

    @Override
    public Customer getCustomer(String customerId) {

        Customer customer = repository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer Not found"));
        // Call the Rating Service to fetch the rating per user. In the Rating Service we have one method whose URl is
        // ==> api/rating/customer/{customerId}. So we will call this service to fetch rating per customer
        // Here we can use RestTemplate or Feign Client for external service call
        //String URI="http://localhost:8082/api/rating/customer/08bce012-b7a7-4f2f-9ec9-edb02eac2168";

        //String URI="http://localhost:8082/api/rating/customer/"+customer.getCustomerId();

        String URI="http://RATING-SERVICE/api/rating/customer/"+customer.getCustomerId();
        System.out.println("URI-"+URI);
        //ArrayList<Rating> customerRatingList = restTemplate.getForObject(URI, ArrayList.class); // Here were are getting Class cast exception

        // Let's use the getForObject of RestTemplate to extenal service

        //Rating[] customerRatingList = restTemplate.getForObject(URI, Rating[].class); // Here we have passed URI and response type
       // List<Rating> ratingList1 = Arrays.asList(customerRatingList);                 // Converted the array into list

       // System.out.println("customerRatingList::"+customerRatingList);

        // Call external service using Feign Client
        List<Rating> ratingListByCustomerId = feignRatingClient.getRatingByCustomerId(customer.getCustomerId());
        System.out.println("Using Feign-client::"+ratingListByCustomerId);
        //customer.setRatingList(ratingList1);
       // List<Rating> ratingList= ratingList1.stream().map(rating->{
            List<Rating> ratingList= ratingListByCustomerId.stream().map(rating->{

                //String hotelUri= "http://localhost:8083/api/hotel/"+rating.getHotelId();
                // Let's use service name

                //1-  Call the service using RestTemplate
                String hotelUri= "http://HOTEL-SERVICE/api/hotel/"+rating.getHotelId();
               /* ResponseEntity<Hotel> forEntity = restTemplate.getForEntity(hotelUri, Hotel.class);
                Hotel hotelByRestTemplate = forEntity.getBody();*/

                //2-  call the service using Feign client
                Hotel hotelFeignClientHotel = hotelFeignClient.getHotel(rating.getHotelId());


                //rating.setHotel(hotelByRestTemplate);
                rating.setHotel(hotelFeignClientHotel);
                return rating;
            }).collect(Collectors.toList());
            customer.setRatingList(ratingList);
        return customer;
    }

    @Override
    public String deleteCustomer(String customerId) {
         repository.deleteById(customerId);
         return "Customer Deleted Successfully";
    }
}
