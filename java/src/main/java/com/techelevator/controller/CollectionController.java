package com.techelevator.controller;

import com.techelevator.dao.CollectionDao;
import com.techelevator.dao.JdbcCollectionDao;
import com.techelevator.model.ComicCollection;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.security.PermitAll;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@PreAuthorize("isAuthenticated()")
@CrossOrigin
public class CollectionController {




    CollectionDao collectionDao;

    CollectionController(CollectionDao collectionDao){
        this.collectionDao = collectionDao;
    }


    @PreAuthorize("permitAll")
    @GetMapping("/collections")
    public List<ComicCollection> getPublicCollections(){
        List<ComicCollection> collection = collectionDao.listAllCollections();
        if(collection == null || collection.size() == 0){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No public collections available");
        } else return collection;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{userId}/collections")
    public List<ComicCollection> getCollectionsByUserId(@PathVariable int userId){
        List<ComicCollection> collection = collectionDao.listCollectionsByUser(userId);
        if(collection == null || collection.size() == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Collection not found");
        } else return collection;
    }

    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/collections")
    public ComicCollection createCollection(@RequestBody ComicCollection newCollection){
        return collectionDao.createCollection(newCollection);
    }
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/collections")
    public ComicCollection updateCollectionName(@RequestBody ComicCollection comicCollectionToUpdate){
        return collectionDao.updateCollectionName(comicCollectionToUpdate);
    }


}