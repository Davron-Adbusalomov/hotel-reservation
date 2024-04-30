package com.example.hotelreservation.service;

import com.example.hotelreservation.dto.ReservationDTO;
import com.example.hotelreservation.model.Reservation;
import com.example.hotelreservation.model.Users;
import com.example.hotelreservation.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private UsersRepository usersRepository;

    public List<ReservationDTO> getReservations(Long userId){

        Optional<Users> users = usersRepository.findById(userId);
        if (users.isEmpty()){
            throw new EntityNotFoundException("No user found with this id");
        }

        List<ReservationDTO> reservationDTOS = new ArrayList<>();
        List<Reservation> reservations = users.get().getReservations();
        for (Reservation r:reservations) {
            ReservationDTO reservationDTO = new ReservationDTO();
            reservationDTO.setRoomNumber(r.getRoom().getRoomNumber());
            reservationDTO.setCheckOutDate(r.getCheckOutDate());
            reservationDTO.setCheckInDate(r.getCheckInDate());
            reservationDTO.setTotalPrice(r.getRoom().getPrice()*(r.getCheckOutDate().getDayOfYear()-r.getCheckInDate().getDayOfYear()));
            reservationDTOS.add(reservationDTO);
        }

        return reservationDTOS;
    }
}
