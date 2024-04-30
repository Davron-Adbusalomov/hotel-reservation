package com.example.hotelreservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    private Long roomNumber;

    private Long numberOfPerson;

    private Long price;

    private String quality;

    private Boolean isAvailable;
}
