package com.techelevator.dao;

import com.techelevator.model.Comic;

import java.util.List;

public interface ComicDao {
    List<Comic> listAllComicsOfCollection (int collectionId);
    Comic getComic (int comicId);
    List<Comic> getComicsByAuthor(String author);
    List<Comic> getComicsByArtist(String artist);
    List<Comic> getComicsByCharacter(int characterId);
    List<Comic> getComicsBySeries(String series);
    Comic addComic (Comic comic);
    void addComicToCollection(int comicId, int collectionId);
    void removeComicFromCollection(int comicId, int collectionId);
    void updateComic (Comic comic);
    void deleteComic (int comicId);
    int countUniqueComicsOfUser(int userId);

}
