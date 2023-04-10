package com.techelevator.model;

public class ComicCharacter {
    private int characterId;
    private String characterRealName;
    private String characterAlias;

    public ComicCharacter() {
    }

    public ComicCharacter(int characterId, String characterRealName, String characterAlias) {
        this.characterId = characterId;
        this.characterRealName = characterRealName;
        this.characterAlias = characterAlias;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
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
