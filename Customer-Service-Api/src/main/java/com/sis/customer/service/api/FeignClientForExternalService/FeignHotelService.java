package com.sis.customer.service.api.FeignClientForExternalService;

import com.sis.customer.service.api.entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface FeignHotelService {

    @GetMapping("api/hotel/{hotelId}")
    public Hotel getHotel(@PathVariable String hotelId);
}
