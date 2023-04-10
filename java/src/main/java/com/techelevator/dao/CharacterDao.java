package com.techelevator.dao;

import com.techelevator.model.ComicCharacter;

import java.util.List;

public interface CharacterDao {
    ComicCharacter getCharacterById(ComicCharacter characterId);
    ComicCharacter getCharacterByAlias(ComicCharacter comicCharacter);
    List<ComicCharacter> getAllCharacters();
}
