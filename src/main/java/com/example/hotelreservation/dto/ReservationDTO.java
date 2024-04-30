package com.example.hotelreservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Long roomNumber;

    private double totalPrice;
}
