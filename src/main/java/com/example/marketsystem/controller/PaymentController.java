package com.example.marketsystem.controller;

import com.example.marketsystem.payload.ApiResponse;
import com.example.marketsystem.payload.PaymentDto;
import com.example.marketsystem.repository.PaymentRepository;
import com.example.marketsystem.service.PaymentService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final PaymentRepository paymentRepository;


    @GetMapping
    public HttpEntity<?> getPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                 @RequestParam(value = "size", defaultValue = "10") int size) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.getPagePayments(page,size));
    }

    @GetMapping("/one/payments")
    public HttpEntity<?> getOne(@RequestParam Long id){
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.getPaymentDto(paymentRepository.findById(id).get()));
    }

    @GetMapping("/pdf/payment")
    public void generatePDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        this.paymentService.export(response);
    }

    @PostMapping
    public HttpEntity<?> save(@RequestBody PaymentDto paymentDto){
        ApiResponse<?> apiResponse = paymentService.save(paymentDto);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
