package com.sis.hotel.service.api.service.impl;

import com.sis.hotel.service.api.entity.Hotel;
import com.sis.hotel.service.api.exception.HotelNotFoundException;
import com.sis.hotel.service.api.repository.HotelRepository;
import com.sis.hotel.service.api.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HotelImpl implements HotelService {

    private final HotelRepository repository;

    @Autowired
    public HotelImpl(HotelRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Hotel> createHotel(Hotel hotel) {

        String hotelId = UUID.randomUUID().toString();

        hotel.setHotelId(hotelId);
        Hotel newHotel = repository.save(hotel);
        return Optional.of(newHotel);
    }

    @Override
    public Optional<Hotel> updateHotel(Hotel hotel) {

        Hotel updatedHotel = repository.save(hotel);
        return Optional.of(updatedHotel);
    }

    @Override
    public Optional<Hotel> getHotel(String hotelId) {
        Hotel hotel = repository.findById(hotelId).orElseThrow(() -> new HotelNotFoundException("Hotel Not Found !!"));
        return Optional.of(hotel);
    }

    @Override
    public Optional<List<Hotel>> getAllHotels() {
       // List<Hotel> allHotels = repository.findAll();
        List<Hotel> hotels = Optional.ofNullable(repository.findAll()).orElseThrow(() -> new HotelNotFoundException("Hotel Not found !!"));
        return Optional.of(hotels);
    }

    @Override
    public Optional<String> deleteHotel(String hotelId) {

        repository.findById(hotelId).orElseThrow(() -> new HotelNotFoundException("Hotel Not Found !!"));
        repository.deleteById(hotelId);

        return Optional.of("Hotel Deleted Successfully");
    }
}
