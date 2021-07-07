package com.example.springmvc.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
@Data
@Validated
public class FoodDto {
    @Size(min = 4,max = 20,message = "the size of the name has to be between 4 - 20 characters")
    private String name;
    @NotEmpty(message = "you need at least 1 ingredient")
    private List<@Valid IngredientDto> ingredients;
}
