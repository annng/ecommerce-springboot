package com.bootcamp.ecommerce.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductDto {
    private Long id;

    @NotNull
    @Size(min = 5, max = 100)
    private String title;
    @Size(max = 255)
    private String images;
    @Size(max = 255)
    private String description;
    @NotNull
    @Size(max = 10)
    private Long price;

}
