package com.example.hotelreservation.controller;

import com.example.hotelreservation.dto.BookDTO;
import com.example.hotelreservation.dto.UserDTO;
import com.example.hotelreservation.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
@RequestMapping("api/users")
public class UsersController {

    @Autowired
    private UsersService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userLoginDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.loginUser(userLoginDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/bookRoom")
    public ResponseEntity<String> bookRoom(@RequestBody BookDTO bookDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.bookRoom(bookDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
