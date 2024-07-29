package com.sis.rating.service.api.service.impl;

import com.sis.rating.service.api.entity.Rating;
import com.sis.rating.service.api.exception.RatingNotFoundException;
import com.sis.rating.service.api.repository.RatingRepository;
import com.sis.rating.service.api.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository repository;

    @Autowired
    public RatingServiceImpl(RatingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Rating> addRating(Rating rating) {

        String uuid = UUID.randomUUID().toString();
        rating.setRatingId(uuid);

        return Optional.of(repository.save(rating));
    }

    @Override
    public Optional<Rating> updateRating(Rating rating) {
        return Optional.of(repository.save(rating));
    }

    @Override
    public Optional<Rating> getRating(String ratingId) {

        return Optional.ofNullable(repository.findById(ratingId))
                       .orElseThrow(()->new RatingNotFoundException("Rating Not Found !!"));
    }
    @Override
    public Optional<List<Rating>> getAllRating() {

        List<Rating> ratings = repository.findAll();
        return Optional.ofNullable(Optional.of(ratings).orElseThrow(() -> new RatingNotFoundException("Rating Not Found")));
    }
    @Override
    public Optional<String> deleteRating(String ratingId) {

        Optional.ofNullable(repository.findById(ratingId))
                .orElseThrow(()->new RatingNotFoundException("Rating Not Found !!"));
        repository.deleteById(ratingId);

        return Optional.of("Rating Deleted Successfully !! ");
    }

    @Override
    public Optional<List<Rating>> getRatingByCustomerId(String userId) {

        List<Rating> ratingByUser = repository.findByCustomerId(userId).orElseThrow(() -> new RatingNotFoundException("User id Not found !!"));
        return Optional.ofNullable(ratingByUser);
    }

    @Override
    public Optional<List<Rating>> getRatingByHotelId(String hotelId) {
        List<Rating> ratingsForHotel = repository.findByHotelId(hotelId).orElseThrow(() -> new RatingNotFoundException("Hotel Not Found !!"));
        return Optional.of(ratingsForHotel);
    }
}
