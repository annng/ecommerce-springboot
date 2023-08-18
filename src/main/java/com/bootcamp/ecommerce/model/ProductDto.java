package com.bootcamp.ecommerce.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private Long id;

    @NotNull
    @Size(min = 5, max = 60)
    private String title;
    private String images;
    @NotNull
    @Size(min = 10, max = 100)
    private String description;
    private Long price;

}
