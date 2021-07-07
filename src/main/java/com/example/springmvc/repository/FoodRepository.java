package com.example.springmvc.repository;

import com.example.springmvc.dto.response.IngredientResponseDto;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Repository
public class FoodRepository implements IFood{

    private List<IngredientResponseDto> getAllIngredients() {
        File file = loadFile();
        if (Objects.nonNull(file)){
            var ingredients = loadJson(file);
            if (Objects.nonNull(ingredients)){
                return ingredients;
            }else{
                throw new Error("File doesn't match with the entity class");
            }
        }else{
            throw  new Error("Not file found");
        }
    }

    private List<IngredientResponseDto> loadJson(File file){
        ObjectMapper obj = new ObjectMapper();
        TypeReference<List<IngredientResponseDto>> typeRef = new TypeReference<>(){};
        List<IngredientResponseDto> ingredients = null;
        try{
            ingredients = obj.readValue(file,typeRef);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ingredients;
    }

    private File loadFile(){
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:food.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return file;
    }

    @Override
    public IngredientResponseDto findIngredientByName(String ingredientName) {
        var ingredients = getAllIngredients();
        var ingredient = ingredients.stream().filter(ingredientResponseDto -> ingredientResponseDto.getName().equals(ingredientName)).findFirst();
        if (ingredient.isPresent()){
            return  ingredient.get();
        }else{
            throw  new Error("Ingredient not found");
        }
    }
}
