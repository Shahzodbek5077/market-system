package com.example.marketsystem.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    @Schema(hidden = true)
    private Long id;
    private String name;
}
