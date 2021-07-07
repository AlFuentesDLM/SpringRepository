package com.example.starwar.repository;

import com.example.springmvc.dto.response.IngredientResponseDto;
import com.example.starwar.dto.CharacterResponseDto;
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
public class StarWarRepository implements  IStarWar{

    @Override
    public CharacterResponseDto getCharacterByString(String name) {
        var characters = getAllCharacters();
        var character = characters.stream().filter(characterResponseDto -> characterResponseDto.getName().contains(name)).findFirst();
        if (character.isPresent()){
            return  character.get();
        }else{
            throw new Error("Character not found");
        }
    }

    private List<CharacterResponseDto> getAllCharacters() {
        var file = loadFile();
        if (Objects.nonNull(file)){
            var characters   = loadJson(file);
            if (Objects.nonNull(characters)){
                return characters;
            }else{
                throw new Error("File doesn't match with the entity class");
            }
        }else{
            throw  new Error("Not file found");
        }
    }

    private List<CharacterResponseDto> loadJson(File file){
        ObjectMapper obj = new ObjectMapper();
        TypeReference<List<CharacterResponseDto>> typeRef = new TypeReference<>(){};
        List<CharacterResponseDto> characters = null;
        try{
            characters = obj.readValue(file,typeRef);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return characters;
    }

    private File loadFile(){
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:starwars.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return file;
    }
}
