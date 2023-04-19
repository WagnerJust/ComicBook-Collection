package com.techelevator.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.techelevator.dao.CharacterDao;
import com.techelevator.dao.ComicDao;
import com.techelevator.model.Comic;
import com.techelevator.model.ComicCharacter;
import com.techelevator.services.RestMarvelService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
@PreAuthorize("isAuthenticated()")
@CrossOrigin
public class ComicController {

    ComicDao comicDao;
    RestMarvelService marvelService;
    CharacterDao characterDao;

    public ComicController(ComicDao comicDao, RestMarvelService marvelService, CharacterDao characterDao) {
        this.comicDao = comicDao;
        this.marvelService = marvelService;
        this.characterDao = characterDao;
    }



    @PreAuthorize("permitAll")
    @GetMapping("/collections/{collectionId}/comics")
    public List<Comic> getComicsByCollectionId(@PathVariable int collectionId){
        List<Comic> comicsList = comicDao.listAllComicsOfCollection(collectionId);
        if(comicsList == null || comicsList.size() == 0){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No comics are in this collection");
        } else return comicsList;
    }

    @PreAuthorize("permitAll")
    @GetMapping("/comics/upc/{upc}")
    public Comic getComicByUpc(@PathVariable String upc){
        Comic comic = comicDao.getComicByUpc(upc);
        if(comic == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find comic");
        } else return comic;
    }

    @PreAuthorize("hasAnyRole('USER','PREMIUM')")
    @GetMapping("/comics/{comicId}")
    public Comic getComicById(@PathVariable int comicId){
        Comic comic = comicDao.getComic(comicId);
        if(comic == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find comic");
        } else return comic;
    }

    @PreAuthorize("hasAnyRole('USER','PREMIUM')")
    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/collections/{collectionId}/add")
    public void addComicToCollection(@PathVariable int collectionId, @RequestBody Comic comicToAdd, Authentication authentication){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_PREMIUM"))) {
            comicDao.addComicToCollection(comicToAdd.getComicId(), collectionId);
        }
        else if (comicDao.listAllComicsOfCollection(collectionId).size() >= 100){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Standard user collections are limited to 100 entries");
        } else {
            comicDao.addComicToCollection(comicToAdd.getComicId(), collectionId);
        }
    }

    @PreAuthorize("hasAnyRole('USER','PREMIUM')")
    @PostMapping("/comics")
    public Comic addComic(@RequestBody Comic comic) throws JsonProcessingException {
        Comic newComic = null;
        if (comicDao.getComicByUpc(comic.getUpc()) != null){
            return null;
        }else{
            newComic = comicDao.addComic(comic);
            List<ComicCharacter> characters = marvelService.getCharactersByComicUpc(newComic.getUpc());
            if (characters != null) {
                for (ComicCharacter character : characters) {
                    characterDao.addCharacterToComic(characterDao.addCharacter(character).getCharacterId(), newComic.getComicId());
                }
            }
        }
        return newComic;
    }

    @PreAuthorize("hasAnyRole('USER','PREMIUM')")
    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/collections/{collectionId}/comics/{comicId}")
    public void removeComicFromCollection(@PathVariable int collectionId, @PathVariable int comicId){
        comicDao.removeComicFromCollection(collectionId, comicId);
    }

    @PreAuthorize("hasAnyRole('USER','PREMIUM')")
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/comics/author/{authorName}")
    public List<Comic> getComicsByAuthor(@PathVariable String authorName){
        return comicDao.getComicsByAuthor(authorName);
    }

    @PreAuthorize("hasAnyRole('USER','PREMIUM')")
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/comics/author/{artistName}")
    public List<Comic> getComicsByArtist(@PathVariable String artistName){
        return comicDao.getComicsByArtist(artistName);
    }

    @PreAuthorize("hasAnyRole('USER','PREMIUM')")
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/comics/series/{seriesName}")
    public List<Comic> getComicsBySeries(@PathVariable String seriesName){
        return comicDao.getComicsBySeries(seriesName);
    }

//    @PreAuthorize("hasAnyRole('USER','PREMIUM')")
//    @ResponseStatus(value = HttpStatus.OK)
//    @GetMapping("/comics/characters/real/{name}")
//    public List<Comic> getComicsByCharacterName(@PathVariable String name){
//        return comicDao.getComicsByCharacterName(name);
//    }
//
//    @PreAuthorize("hasAnyRole('USER','PREMIUM')")
//    @ResponseStatus(value = HttpStatus.OK)
//    @GetMapping("/comics/characters/alias/{alias}")
//    public List<Comic> getComicsByCharacterAlias(@PathVariable String alias){
//        return comicDao.getComicsByCharacterAlias(alias);
//    }



}
