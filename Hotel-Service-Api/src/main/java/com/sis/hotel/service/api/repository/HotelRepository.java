package com.sis.hotel.service.api.repository;

import com.sis.hotel.service.api.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,String> {
}
