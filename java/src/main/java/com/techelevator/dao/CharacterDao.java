package com.techelevator.dao;

import com.techelevator.model.ComicCharacter;

import java.util.List;

public interface CharacterDao {
    ComicCharacter getCharacterById(int characterId);
    ComicCharacter getCharacterByAlias(String characterName);
    List<ComicCharacter> getAllCharacters();
    ComicCharacter addCharacter(ComicCharacter character);
    boolean updateCharacter(int characterId, ComicCharacter character);
    boolean deleteCharacter(int characterId);
    boolean addCharacterToComic(int characterId, int comicId);
    int countCharactersInCollection(int collectionId, int characterId);
    int countCharactersOfUser(int userId, int characterId);

    //todo: is this needed?
    //boolean removeCharacterFromComic(int characterId, int comicId);

}
