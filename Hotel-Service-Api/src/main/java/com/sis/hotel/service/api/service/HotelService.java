package com.sis.hotel.service.api.service;

import com.sis.hotel.service.api.entity.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelService{

    Optional <Hotel> createHotel(Hotel hotel);
    Optional <Hotel> updateHotel(Hotel hotel);
    Optional <Hotel> getHotel(String hotelId);
    Optional <List<Hotel>> getAllHotels();
    Optional<String> deleteHotel(String hotelId);
}
