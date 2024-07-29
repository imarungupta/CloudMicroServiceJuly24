package com.sis.hotel.service.api.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "hotels_tbl")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    @Id
    private String hotelId;
    @Column(name = "hotel_name")
    private String hotelName;
    @Column(name = "hotel_location")
    private String hotelLocation;
    @Column(name = "about_hotel")
    private String aboutHotel;

}
