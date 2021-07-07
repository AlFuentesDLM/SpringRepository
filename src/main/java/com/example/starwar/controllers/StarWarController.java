package com.example.starwar.controllers;

import com.example.starwar.dto.CharacterRequestDto;
import com.example.starwar.dto.CharacterResponseDto;
import com.example.starwar.services.StarWarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/star-war")
public class StarWarController {
    private StarWarService service;

    public StarWarController(StarWarService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<CharacterResponseDto> findCharacter(@Valid @RequestBody CharacterRequestDto character){
        return new ResponseEntity<>(service.getCharacter(character),HttpStatus.OK)  ;
    }
}
