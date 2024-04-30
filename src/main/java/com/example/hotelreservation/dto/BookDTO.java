package com.example.hotelreservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private Long roomNumber;

    private Long userId;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;
}
