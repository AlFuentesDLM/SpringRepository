package com.example.springmvc.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Validated
public class IngredientDto {
    @NotNull(message = "field name for ingredient can't be null")
    @Size(min = 4,max = 20,message = "the size of the name has to be between 4 - 20 characters")
    private String name;
    @NotNull(message = "The ingredients weight can't be null")
    private int weight;
}
