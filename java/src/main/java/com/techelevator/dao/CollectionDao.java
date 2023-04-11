package com.techelevator.dao;

import com.techelevator.model.ComicCollection;
import com.techelevator.model.User;

import java.util.List;

public interface CollectionDao {
    ComicCollection createCollection(ComicCollection comicCollection);
    List<ComicCollection> listAllCollections();
    List<ComicCollection> listCollectionsByUser(int id);
    ComicCollection updateCollectionName(ComicCollection comicCollectionName);
    void deleteCollection(int userId, int collectionId);

}