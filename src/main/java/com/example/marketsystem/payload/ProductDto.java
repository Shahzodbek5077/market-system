package com.example.marketsystem.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    @Schema(hidden = true)
    private Long id;
    @NotBlank(message = "Name bo'sh bo'lmasin")
    private String name;
    @NotNull(message = "Product soni bo'sh bo'lmasin")
    private Double measureCount;
    @NotNull(message = "product narxi bush bulmasin")
    private Double price;
    private Double percentage;
    @NotBlank(message = "Category bo'sh bo'lmasin")
    private Long categoryId;
    @Schema(hidden = true)
    private String categoryName;
    @NotBlank(message = "O'lchov birligi bo'sh bo'lmasin")
    private String measure;
}
