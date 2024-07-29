package com.sis.rating.service.api.controller;

import com.sis.rating.service.api.entity.Rating;
import com.sis.rating.service.api.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RatingController {

    private final RatingService service;

    @Autowired
    public RatingController(RatingService service) {
        this.service = service;
    }
    @PostMapping("/rating")
    public ResponseEntity<Optional<Rating>> createRating(@RequestBody Rating rating){
        return new ResponseEntity<>(service.addRating(rating), HttpStatus.CREATED);
    }
    @PutMapping("/rating")
    public ResponseEntity<Optional<Rating>> updateRating(@RequestBody Rating rating){
        return new ResponseEntity<>(service.updateRating(rating),HttpStatus.OK);
    }
    @GetMapping("/rating/{ratingId}")
    public ResponseEntity<Optional<Rating>> getSingleRating(@PathVariable String ratingId){
        return new ResponseEntity<>(service.getRating(ratingId),HttpStatus.OK);
    }
    @GetMapping("/rating")
    public ResponseEntity<Optional<List<Rating>>> getAllRating(){
        return new ResponseEntity<>(service.getAllRating(),HttpStatus.OK);
    }
    @DeleteMapping("/rating/{ratingId}")
    private ResponseEntity<Optional<String>> deleteRating(@PathVariable String ratingId){
        return new ResponseEntity<>(service.deleteRating(ratingId),HttpStatus.OK);
    }
    @GetMapping("/rating/customer/{customerId}")
    private ResponseEntity<Optional<List<Rating>>> getRatingByUserId(@PathVariable String customerId){
        return new ResponseEntity<>(service.getRatingByCustomerId(customerId),HttpStatus.OK);
    }
    @GetMapping("/rating/hotel/{hotelId}")
    private ResponseEntity<Optional<List<Rating>>> getRatingByHotel(@PathVariable String hotelId){
        return new ResponseEntity<>(service.getRatingByHotelId(hotelId),HttpStatus.OK);
    }
}
