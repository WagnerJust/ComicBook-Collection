package com.techelevator.dao;

import com.techelevator.model.ComicCharacter;

import java.util.List;

public interface CharacterDao {
    ComicCharacter getCharacterById(int characterId);
    List<ComicCharacter> getCharacterByAlias(String characterName);
    List<ComicCharacter> getCharacterByRealName(String characterName);
    List<ComicCharacter> getAllCharacters();
    List<ComicCharacter> getCharactersByComicId(int comicId);
    ComicCharacter addCharacter(ComicCharacter character);
    void updateCharacter(int characterId, ComicCharacter character);
    void deleteCharacter(int characterId);
    void addCharacterToComic(int characterId, int comicId);
    int countCollectionComicsWithCharacter(int collectionId, int characterId);
    int countUserComicsWithCharacter(int userId, int characterId);

    //todo: is this needed?
    //boolean removeCharacterFromComic(int characterId, int comicId);

}
