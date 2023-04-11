package com.techelevator.controller;

import com.techelevator.dao.CollectionDao;
import com.techelevator.dao.JdbcCollectionDao;
import com.techelevator.model.ComicCollection;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import java.security.Principal;
import java.util.List;

@RestController
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
        return collectionDao.listAllCollections();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{userId}/collections")
    public List<ComicCollection> getCollectionsByUserId(@PathVariable int userId){

        return collectionDao.listCollectionsByUser(userId);
    }

}