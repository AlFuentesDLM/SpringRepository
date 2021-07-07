package com.example.starwar.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
public class CharacterRequestDto {
    @NotNull(message = "Name cant be null")
    private String name;
}
