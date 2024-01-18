package com.example.marketsystem.entity;

import com.example.marketsystem.entity.template.AbsEntity;
import com.example.marketsystem.entity.template.PayType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Pay extends AbsEntity {

    @Enumerated(EnumType.STRING)
    private PayType payType;

    private Double price;
}
