package com.example.marketsystem.entity;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthLoginDto {
    private String phoneNumber;
    private String password;
}
