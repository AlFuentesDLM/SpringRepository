package com.example.springmvc.services;

import com.example.springmvc.dto.FoodDto;
import com.example.springmvc.dto.IngredientDto;
import com.example.springmvc.dto.response.FoodResponseDto;
import com.example.springmvc.dto.response.IngredientResponseDto;
import com.example.springmvc.repository.IFood;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class FoodService {
    private final IFood foodRepository;

    public FoodService(IFood foodRepository) {
        this.foodRepository = foodRepository;
    }

    public FoodResponseDto getFoodFromIngredients(FoodDto food){
        FoodResponseDto foodResponse = new FoodResponseDto();
        List<IngredientResponseDto> ingredients = new ArrayList<>();
        var totalCalories = 0;
        for (IngredientDto ingredient: food.getIngredients()) {
          var responseIngredient =  foodRepository.findIngredientByName(ingredient.getName());
          responseIngredient.setCalories(calculateCalories(ingredient,responseIngredient));
          ingredients.add(responseIngredient);
          totalCalories += responseIngredient.getCalories();
        }
        foodResponse.setIngredients(ingredients);
        foodResponse.setMostCaloriesIngredient(findIngredientWithMoreCalories(foodResponse.getIngredients()));
        foodResponse.setTotalCalories(totalCalories);
        return foodResponse;
    }

    private double calculateCalories(IngredientDto ingredient, IngredientResponseDto ingredientResponse){
        return (ingredient.getWeight()/100)*ingredientResponse.getCalories();
    }

    private IngredientResponseDto findIngredientWithMoreCalories(List<IngredientResponseDto> ingredients){
        var ingredient = ingredients.stream().max(Comparator.comparing(IngredientResponseDto::getCalories));
        if (ingredient.isPresent()){
            return ingredient.get();
        }else{
            throw new Error("Cant find the maximum calories of null");
        }
    }

}
