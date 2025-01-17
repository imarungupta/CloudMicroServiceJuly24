package com.sis.customer.service.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    private String ratingId;
    private String customerId;
    private String hotelId;
    private int rating;
    private String feedBack;

    private Hotel hotel;


}
