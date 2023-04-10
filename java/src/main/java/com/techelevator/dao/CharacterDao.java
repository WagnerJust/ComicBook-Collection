package com.techelevator.dao;

import com.techelevator.model.ComicCharacter;

import java.util.List;

public interface CharacterDao {
    ComicCharacter getCharacterById(int characterId);
    ComicCharacter getCharacterByAlias(String characterName);
    List<ComicCharacter> getAllCharacters();
}
