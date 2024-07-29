package com.sis.rating.service.api.service;

import com.sis.rating.service.api.entity.Rating;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface RatingService {

    Optional<Rating> addRating(Rating rating);
    Optional<Rating> updateRating(Rating rating);
    Optional<Rating> getRating(String ratingId);
    Optional<List<Rating>> getAllRating();
    Optional<String> deleteRating(String ratingId);

    Optional<List<Rating>> getRatingByCustomerId(String userId);
    Optional<List<Rating>> getRatingByHotelId(String hotelId);
}
