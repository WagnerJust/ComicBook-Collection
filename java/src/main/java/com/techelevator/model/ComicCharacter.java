package com.techelevator.model;

public class ComicCharacter {
    private int characterId;
    private int marvelCharacterId;
    private String characterRealName;
    private String characterAlias;

    public ComicCharacter() {
    }

    public ComicCharacter(int characterId, int marvelCharacterId, String characterRealName, String characterAlias) {
        this.characterId = characterId;
        this.marvelCharacterId = marvelCharacterId;
        this.characterRealName = characterRealName;
        this.characterAlias = characterAlias;
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

    public String getCharacterRealName() {
        return characterRealName;
    }

    public void setCharacterRealName(String characterRealName) {
        this.characterRealName = characterRealName;
    }

    public String getCharacterAlias() {
        return characterAlias;
    }

    public void setCharacterAlias(String characterAlias) {
        this.characterAlias = characterAlias;
    }
}
