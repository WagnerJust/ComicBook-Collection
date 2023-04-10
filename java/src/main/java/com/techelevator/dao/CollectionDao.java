package com.techelevator.dao;

import com.techelevator.model.ComicCollection;
import com.techelevator.model.User;

import java.util.List;

public interface CollectionDao {
    ComicCollection createCollection(ComicCollection comicCollection);
    List<ComicCollection> listComicsInCollection(ComicCollection comicCollectionId);
    List<ComicCollection> listAllCollections();
    List<ComicCollection> listCollectionsByUser(User username);
    ComicCollection updateCollectionName(ComicCollection comicCollectionName);
    void deleteCollection(ComicCollection comicCollectionName);

}
