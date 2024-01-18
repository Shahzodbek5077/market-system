package com.example.marketsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin
public class UserController {


    @GetMapping
    public ResponseEntity<?> get(){
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }
}
