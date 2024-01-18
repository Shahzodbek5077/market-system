package com.example.marketsystem.entity;

import com.example.marketsystem.entity.template.AbsEntity;
import com.example.marketsystem.entity.template.Measure;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Order extends AbsEntity {

    @OneToOne
    private Product product;

    @Enumerated(EnumType.STRING)
    private Measure measure;

    private Double measureCount;

    private Double totalPrice;

}
