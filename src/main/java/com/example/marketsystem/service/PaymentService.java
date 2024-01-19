package com.example.marketsystem.service;

import com.example.marketsystem.entity.Company;
import com.example.marketsystem.entity.Order;
import com.example.marketsystem.entity.User;
import com.example.marketsystem.exception.GenericNotFoundException;
import com.example.marketsystem.payload.ApiResponse;
import com.example.marketsystem.payload.PaymentDto;
import com.example.marketsystem.repository.CompanyRepository;
import com.example.marketsystem.repository.OrderRepository;
import com.example.marketsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final CompanyRepository companyRepository;

    public ApiResponse<?> save(PaymentDto paymentDto) {
        User user = userRepository.findById(paymentDto.getId()).orElseThrow(() -> GenericNotFoundException.builder().message("User not found").statusCode(404).build());
        Company company = companyRepository.findById(1L).orElseThrow(() -> GenericNotFoundException.builder().message("Company not found").statusCode(404).build());
        List<Order>orders = new ArrayList<>();
        var summa = 0.0;
        paymentDto.getOrders().forEach(order -> {
           orders.add(orderRepository.save( Order.builder()
                    .product(order.getProduct())
                    .measure(order.getMeasure())
                    .measureCount(order.getMeasureCount())
                    .totalPrice(order.getProduct().getPrice() * order.getMeasureCount())
                    .build()));
        });
        //orderRepository.

        return ApiResponse.builder().message("success").build();

    }
}
