package com.example.marketsystem.payload;

import io.swagger.v3.oas.annotations.media.Schema;
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
    private String measure;
}
