package com.example.starwar.repository;

import com.example.starwar.dto.CharacterResponseDto;

public interface IStarWar {
    CharacterResponseDto getCharacterByString(String name);
}
