package com.sis.hotel.service.api.controller;

import com.sis.hotel.service.api.entity.Hotel;
import com.sis.hotel.service.api.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PutExchange;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class HotelController {

    private final HotelService service;

    @Autowired
    public HotelController(HotelService service) {
        this.service = service;
    }

    @PostMapping("/hotel")
    public ResponseEntity<Optional<Hotel>> addHotel(@RequestBody Hotel hotel){

        Optional<Hotel> newHotel = service.createHotel(hotel);
        return new ResponseEntity<>(newHotel, HttpStatus.CREATED);
    }
    @PutMapping("/hotel")
    public ResponseEntity<Optional<Hotel>> updateHotel(@RequestBody Hotel hotel){
        return new ResponseEntity<>(service.updateHotel(hotel),HttpStatus.CREATED);
    }
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<Optional<Hotel>> getSingleHotel(@PathVariable String hotelId){
        return new ResponseEntity<>(service.getHotel(hotelId),HttpStatus.OK);
    }
    @GetMapping("/hotel")
    private ResponseEntity<Optional<List<Hotel>>> getAllHotel(){
        return new ResponseEntity<>(service.getAllHotels(),HttpStatus.OK);
    }
    @DeleteMapping("/hotel/{hotelId}")
    private ResponseEntity<Optional<String>> deleteHotel(@PathVariable String hotelId){
        return new ResponseEntity<>(service.deleteHotel(hotelId),HttpStatus.OK);
    }
}
