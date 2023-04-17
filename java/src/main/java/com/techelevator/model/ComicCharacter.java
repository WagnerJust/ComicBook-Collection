package com.techelevator.model;

public class ComicCharacter {
    private int characterId;
    private int marvelCharacterId;
    private String characterName;

    public ComicCharacter() {
    }

    public ComicCharacter(int characterId, int marvelCharacterId, String characterRealName) {
        this.characterId = characterId;
        this.marvelCharacterId = marvelCharacterId;
        this.characterName = characterRealName;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public int getMarvelCharacterId() {
        return marvelCharacterId;
    }

    public void setMarvelCharacterId(int marvelCharacterId) {
        this.marvelCharacterId = marvelCharacterId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }


}
