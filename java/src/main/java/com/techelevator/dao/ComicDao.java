package com.techelevator.dao;

import com.techelevator.model.Comic;

import java.util.List;

public interface ComicDao {
    public List<Comic> listAllComicsOfCollection (int collectionId);
    public Comic getComic (int comicId);
    public List<Comic> getComicsByAuthor(String author);
    public List<Comic> getComicsByArtist(String artist);
    public Comic addComic (Comic comic);
    public boolean addComicToCollection(int comicId, int collectionId);
    public boolean removeComicFromCollection(int comicId, int collectionId);
    public boolean updateComic (Comic comic);
    public boolean deleteComic (int comicId);
    public int countUniqueComicsOfUser(int userId);

}
