package com.example.marketsystem.controller;

import com.example.marketsystem.payload.ApiResponse;
import com.example.marketsystem.payload.PaymentDto;
import com.example.marketsystem.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;


//    @PostMapping
//    public HttpEntity<?> save(@RequestBody PaymentDto paymentDto){
//        ApiResponse<?> apiResponse = paymentService.save(paymentDto);
//        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
//    }
}
