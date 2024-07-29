package com.sis.hotel.service.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HotelStaffController {

    @GetMapping("/staff")
    public ResponseEntity<List<String>> getHotelStaff(){

        List<String> staffList = Arrays.asList("Ram", "Shyam", "Kishan");
        return new ResponseEntity<>(staffList, HttpStatus.OK);

    }
}
