package com.example.springmvc.repository;

import com.example.springmvc.dto.IngredientDto;
import com.example.springmvc.dto.response.IngredientResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface IFood {
    IngredientResponseDto findIngredientByName(String IngredientName);
}
