package com.example.marketsystem.service;

import com.example.marketsystem.entity.*;
import com.example.marketsystem.entity.template.PayType;
import com.example.marketsystem.entity.template.RoleName;
import com.example.marketsystem.exception.GenericNotFoundException;
import com.example.marketsystem.payload.ApiResponse;
import com.example.marketsystem.payload.PaymentDto;
import com.example.marketsystem.repository.*;
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
    private final PayRepository payRepository;
    private final ProductRepository productRepository;

    public ApiResponse<?> save(PaymentDto paymentDto) {
        User user = userRepository.findById(paymentDto.getId()).orElseThrow(() -> GenericNotFoundException.builder().message("User not found").statusCode(404).build());
        Company company = companyRepository.findById(1L).orElseThrow(() -> GenericNotFoundException.builder().message("Company not found").statusCode(404).build());
        List<Order> orders = new ArrayList<>();
        List<Pay> pays = new ArrayList<>();
        var summa = 0.0;
        var clientTotalPrice = 0.0;
        paymentDto.getOrders().forEach(order -> {
           orders.add(orderRepository.save( Order.builder()
                    .product(order.getProduct())
                    .measure(order.getMeasure())
                    .measureCount(order.getMeasureCount())
                    .totalPrice(order.getProduct().getPrice() * order.getMeasureCount())
                    .build()));
        });
        paymentDto.getPays().forEach(pay -> {
            pays.add(payRepository.save( Pay.builder()
                            .payType(pay.getPayType())
                            .price(pay.getPrice())
                    .build()));
        });
        for (Order order : orders) {
             summa += order.getTotalPrice();
            Product product = order.getProduct();
            product.setMeasureCount(product.getMeasureCount()-order.getMeasureCount());
            productRepository.save(product);
        }
        for (Pay pay : pays) {
            clientTotalPrice += pay.getPrice();
        }
        double clientDebt = summa - clientTotalPrice;
        user.setDebit(clientDebt);
        userRepository.save(user);
        Payment.builder()
                .user(user)
                .orders(orders)
                .pays(pays)
                .company(company)
                .summa(summa)
                .build();
        return ApiResponse.builder().message("success").build();
    }


}
