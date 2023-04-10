package com.techelevator.controller;

import com.techelevator.dao.MemoryCollectionsDao;
import com.techelevator.model.ComicCollections;
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




    private MemoryCollectionsDao memoryCollectionsDao;

    public CollectionController(MemoryCollectionsDao memoryCollectionsDao) {
        this.memoryCollectionsDao = memoryCollectionsDao;
    }


    @PreAuthorize("permitAll")
    @GetMapping(path="/collections")
    public List<ComicCollections> getAllPublicCollections(){

        return memoryCollectionsDao.listAll();
    }



    @PreAuthorize("hasRole('USER')")
    @GetMapping(path="/collections/{userId}")
    public List<ComicCollections> getPrivateCollections(@PathVariable int userId){
        return memoryCollectionsDao.collectionsByUserId(userId);
    }

}
