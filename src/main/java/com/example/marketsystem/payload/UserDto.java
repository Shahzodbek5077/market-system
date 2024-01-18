package com.example.marketsystem.payload;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private String address;
    private String password;

}
