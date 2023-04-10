package com.techelevator.model;

public class ComicCollection {

    private int userId;
    private int collectionId;
    private String collectionName;
    private Boolean isPublic;


    public ComicCollection() {
    }

    public ComicCollection(int userId, int collectionId, String collectionName, Boolean isPublic) {
        this.userId = userId;
        this.collectionId = collectionId;
        this.collectionName = collectionName;
        this.isPublic = isPublic;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }
}
