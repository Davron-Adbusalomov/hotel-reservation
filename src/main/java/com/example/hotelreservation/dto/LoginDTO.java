package com.example.hotelreservation.dto;

import com.example.hotelreservation.model.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    private List<RoomDTO> rooms;

    private Long userId;
}

