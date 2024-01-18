package com.example.marketsystem.entity;

import com.example.marketsystem.entity.template.AbsEntity;
import com.example.marketsystem.entity.template.Measure;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Product extends AbsEntity {

    @NotBlank(message = "Name bo'sh bo'lmasin")
    private String  name;

    @NotBlank(message = "Product soni bo'sh bo'lmasin")
    private Double measureCount;

    @NotBlank(message = "Product narxini kiriting")
    private Double price;

    private Double percentage;

    @ManyToOne
    private Category category;

    @Enumerated(EnumType.STRING)
    private Measure measure;

}
