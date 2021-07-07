package com.example.springmvc.controllers;

import com.example.springmvc.dto.FoodDto;
import com.example.springmvc.dto.response.FoodResponseDto;
import com.example.springmvc.services.FoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/food")
public class FoodController {

    private FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping
    public ResponseEntity<FoodResponseDto> getCalories(@Valid @RequestBody FoodDto food){
        return new ResponseEntity<>(foodService.getFoodFromIngredients(food),HttpStatus.OK);
    }
}
