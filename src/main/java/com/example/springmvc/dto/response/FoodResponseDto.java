package com.example.springmvc.dto.response;

import com.example.springmvc.dto.IngredientDto;
import lombok.Data;

import java.util.List;

@Data
public class FoodResponseDto {
    private double totalCalories;
    private List<IngredientResponseDto> ingredients;
    private IngredientResponseDto mostCaloriesIngredient;
}
