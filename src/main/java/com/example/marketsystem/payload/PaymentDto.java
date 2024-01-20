package com.example.marketsystem.payload;

import com.example.marketsystem.entity.Company;
import com.example.marketsystem.entity.Order;
import com.example.marketsystem.entity.Pay;
import com.example.marketsystem.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PaymentDto {
    private Long id;
    private Long userId;
    private User user;
    private List<Order> orders;
    private List<Pay> pays;
    private Long companyId;
    private Company company;
}
