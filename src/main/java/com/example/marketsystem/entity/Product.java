package com.example.marketsystem.entity;

import com.example.marketsystem.entity.template.AbsEntity;
import com.example.marketsystem.entity.template.Measure;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Product extends AbsEntity {

    private String  name;

    @NotNull(message = "Measure count bush bulmasin")
    private Double measureCount;

    @NotNull(message = "Price bush bulmasin")
    private Double measureCount;

    private Double price;

    private Double percentage;

    @ManyToOne
    private Category category;

    @Enumerated(EnumType.STRING)
    private Measure measure;

}
