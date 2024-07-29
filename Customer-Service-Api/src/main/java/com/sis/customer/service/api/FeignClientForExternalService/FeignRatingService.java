package com.sis.customer.service.api.FeignClientForExternalService;

import com.sis.customer.service.api.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface FeignRatingService {

    @GetMapping("api/rating/customer/{customerId}")
    List<Rating> getRatingByCustomerId(@PathVariable String customerId);
}
