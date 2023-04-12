package com.techelevator.controller;


import com.techelevator.dao.ComicDao;
import com.techelevator.model.Comic;
import com.techelevator.model.ComicCollection;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
@PreAuthorize("isAuthenticated()")
@CrossOrigin
public class ComicController {

    ComicDao comicDao;

    public ComicController(ComicDao comicDao) {
        this.comicDao = comicDao;
    }



    @PreAuthorize("hasRole('USER')")
    @GetMapping("/collections/{collectionId}/comics")
    public List<Comic> getComicsByCollectionId(@PathVariable int collectionId){
        List<Comic> comicsList = comicDao.listAllComicsOfCollection(collectionId);
        if(comicsList == null || comicsList.size() == 0){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No comics are in this collection");
        } else return comicsList;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/comics/{comicId}")
    public Comic getComicById(@PathVariable int comicId){
        Comic comic = comicDao.getComic(comicId);
        if(comic == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find comic");
        } else return comic;
    }

    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/collections/{collectionId}/add")
    public boolean addComicToCollection(@PathVariable int collectionId, @RequestBody Comic comicToAdd){
        return comicDao.addComicToCollection(comicToAdd.getComicId(), collectionId);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/comics")
    public Comic addComic(@RequestBody Comic comic){
        return comicDao.addComic(comic);
    }


    //todo: remove comic from collection

    //todo: get comics by character

    //todo: get comics by author

    //todo: get comic by title




}
