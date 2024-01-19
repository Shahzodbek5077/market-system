package com.example.marketsystem.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    @Schema(hidden = true)
    private Long id;
    private String name;
    private Double measureCount;
    private Double price;
    private Double percentage;
    private Long categoryId;
    @NotBlank(message = "Name bo'sh bo'lmasin")
    private String name;
    @NotBlank(message = "Product soni bo'sh bo'lmasin")
    private Double measureCount;
    @NotBlank(message = "Product narxini kiriting")
    private Double price;
    private Double percentage;
    @NotBlank(message = "Category bo'sh bo'lmasin")
    private Long categoryId;
    @Schema(hidden = true)
    private String categoryName;
    @NotBlank(message = "O'lchov birligi bo'sh bo'lmasin")
    private String measure;
}
