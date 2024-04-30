package com.example.hotelreservation.service;

import com.example.hotelreservation.dto.ReservationDTO;
import com.example.hotelreservation.dto.RoomDTO;
import com.example.hotelreservation.model.Reservation;
import com.example.hotelreservation.model.Room;
import com.example.hotelreservation.model.Users;
import com.example.hotelreservation.repository.RoomRepository;
import com.example.hotelreservation.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UsersRepository usersRepository;

    public boolean isAvailable(Long roomNumber){
        Optional<Room> room = roomRepository.findByRoomNumber(roomNumber);
        if (room.isEmpty()) throw new EntityNotFoundException("No room found with this number!");

        if (room.get().getReservations().size()==0){
            return true;
        }
        else {
            if (LocalDate.now().isAfter(room.get().getReservations().get(room.get().getReservations().size()-1).getCheckOutDate())){
            room.get().setIsAvailable(true);
            roomRepository.save(room.get());
            return true;
            }else {
                room.get().setIsAvailable(false);
                roomRepository.save(room.get());
            }
        }
        return false;
    }

    public List<RoomDTO> getAllRooms(){
        List<RoomDTO> roomDTOS = new ArrayList<>();
        List<Room> rooms = roomRepository.findAll();

        for (Room r:rooms) {
            isAvailable(r.getId());
            RoomDTO roomDTO = new RoomDTO();
            roomDTO.setRoomNumber(r.getRoomNumber());
            roomDTO.setIsAvailable(r.getIsAvailable());
            roomDTO.setQuality(r.getQuality());
            roomDTO.setNumberOfPerson(r.getNumberOfPerson());
            roomDTO.setPrice(r.getPrice());
            roomDTOS.add(roomDTO);
        }

        return roomDTOS;
    }
}
