package com.example.hotelreservation.service;

import com.example.hotelreservation.dto.BookDTO;
import com.example.hotelreservation.dto.LoginDTO;
import com.example.hotelreservation.dto.RoomDTO;
import com.example.hotelreservation.dto.UserDTO;
import com.example.hotelreservation.model.Reservation;
import com.example.hotelreservation.model.Room;
import com.example.hotelreservation.model.Users;
import com.example.hotelreservation.repository.ReservationsRepository;
import com.example.hotelreservation.repository.RoomRepository;
import com.example.hotelreservation.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationsRepository reservationsRepository;

    @Autowired
    private RoomService roomService;

    public String registerUser(UserDTO userDTO){
        Users users = new Users();
        users.setUsername(userDTO.getUsername());
        users.setPassword(userDTO.getPassword());

        usersRepository.save(users);
        return "Successfully saved!";
    }

    public LoginDTO loginUser(UserDTO userDTO) throws Exception {
        Optional<Users> optionalUser = usersRepository.findByUsername(userDTO.getUsername());
        if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(userDTO.getPassword())){
            List<Room> roomList = roomRepository.findAll();
            LoginDTO loginDTO = new LoginDTO();
            List<RoomDTO> roomDTOS = new ArrayList<>();
            for (Room room:roomList) {
                RoomDTO roomDTO = new RoomDTO();
                roomDTO.setPrice(room.getPrice());
                roomDTO.setRoomNumber(room.getRoomNumber());
                roomDTO.setQuality(room.getQuality());
                roomDTO.setIsAvailable(room.getIsAvailable());
                roomDTO.setNumberOfPerson(room.getNumberOfPerson());
                roomDTOS.add(roomDTO);
            }

            loginDTO.setRooms(roomDTOS);
            loginDTO.setUserId(optionalUser.get().getId());
            return loginDTO;
        }
        throw new Exception("Username or password error!");
    }

    public String bookRoom(BookDTO bookDTO) throws Exception {
        Optional<Room> room = roomRepository.findByRoomNumber(bookDTO.getRoomNumber());
        if (room.isEmpty()){
            throw new EntityNotFoundException("No room found with this number");
        }

        if(!roomService.isAvailable(bookDTO.getRoomNumber())){
            throw new Exception("Room already booked recently!");
        }

        Optional<Users> users = usersRepository.findById(bookDTO.getUserId());
        if (users.isEmpty()){
            throw new EntityNotFoundException("No users found with this id");
        }

        Reservation reservation = new Reservation();
        reservation.setUser(users.get());
        reservation.setRoom(room.get());
        reservation.setCheckInDate(bookDTO.getCheckInDate());
        reservation.setCheckOutDate(bookDTO.getCheckOutDate());
        reservationsRepository.save(reservation);
        users.get().getReservations().add(reservation);
        usersRepository.save(users.get());
        room.get().getReservations().add(reservation);
        roomRepository.save(room.get());

        return "Successfully booked!";
    }


}
