package com.example.starwar.services;

import com.example.starwar.dto.CharacterRequestDto;
import com.example.starwar.dto.CharacterResponseDto;
import com.example.starwar.repository.IStarWar;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StarWarService {
    private IStarWar starWarRepository;

    public StarWarService(IStarWar starWarRepository) {
        this.starWarRepository = starWarRepository;
    }

    public CharacterResponseDto getCharacter(CharacterRequestDto character){
        return starWarRepository.getCharacterByString(character.getName());
    }
}
