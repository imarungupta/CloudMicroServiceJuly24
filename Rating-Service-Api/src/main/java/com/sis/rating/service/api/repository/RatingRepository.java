package com.sis.rating.service.api.repository;

import com.sis.rating.service.api.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating,String> {

   // @Query("select rating from Rating rating where rating.customerId=?1")
    Optional<List<Rating>> findByCustomerId(String userId);
    Optional<List<Rating>> findByHotelId(String hotelId);
}
