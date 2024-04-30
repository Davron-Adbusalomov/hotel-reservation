package com.example.hotelreservation.controller;

import com.example.hotelreservation.service.ReservationService;
import com.example.hotelreservation.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("api/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("getAllReservations/{userId}")
    public ResponseEntity<?> getAll(@PathVariable Long userId){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(reservationService.getReservations(userId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
