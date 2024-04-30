package com.example.hotelreservation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long roomNumber;

    private Long numberOfPerson;

    private Long price;

    private String quality;

    private Boolean isAvailable;

    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations;
}
