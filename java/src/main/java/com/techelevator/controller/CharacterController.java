package com.techelevator.controller;

import com.techelevator.dao.CharacterDao;
import com.techelevator.model.ComicCharacter;
import com.techelevator.model.ComicCollection;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
@PreAuthorize("isAuthenticated()")
@CrossOrigin
public class CharacterController {

    CharacterDao characterDao;

    public CharacterController(CharacterDao characterDao){
        this.characterDao = characterDao;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/characters/{characterId}")
    public ComicCharacter getCharacterById(@PathVariable int characterId) {
        ComicCharacter character = characterDao.getCharacterById(characterId);
        if(character == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find Character");
        }
        else return character;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/characters/alias/{characterName}")
    public List<ComicCharacter> getCharacterByAlias(@PathVariable String characterName) {
        List<ComicCharacter> character = characterDao.getCharacterByAlias(characterName);
        if(character == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find Character");
        }
        else return character;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/characters")
    public List<ComicCharacter> getAllCharacters(){
        List<ComicCharacter> characterList = characterDao.getAllCharacters();
        if(characterList == null || characterList.size() == 0){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No characters found");
        } else return characterList;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/collections/{collectionId}/characters")
    public List<ComicCharacter> getCharactersInCollection(@PathVariable int  collectionId){
        List<ComicCharacter> characterList = characterDao.getCharactersByCollectionId(collectionId);
        if(characterList == null || characterList.size() == 0){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No characters found in collection");
        } else return characterList;
    }



    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/characters/add")
    public ComicCharacter addCharacter(@RequestBody ComicCharacter newCharacter){
        return characterDao.addCharacter(newCharacter);
    }

    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/characters/{characterId}")
    public void updateCharacter(@PathVariable int characterId, @RequestBody ComicCharacter updatedCharacter){
        characterDao.updateCharacter(characterId, updatedCharacter);
    }



    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/characters/delete/{characterId}")
    public void deleteCharacter(@PathVariable int characterId){
        characterDao.deleteCharacter(characterId);
    }

    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping("/comics/{comicId}/characters/{characterId}")
    public void addCharacterToComic(@PathVariable int comicId, int characterId){
        characterDao.addCharacterToComic(characterId,comicId);
    }

    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/collections/{collectionId}/stats/{characterId}")
    public int numberComicsInCollectionWithCharacter(@PathVariable int characterId ,@PathVariable int collectionId){
        return characterDao.numberComicsInCollectionWithCharacter(collectionId,characterId);
    }

    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/user/{userId}/characters/{characterId}")
    public int numberComicsWithCharacterTotal(@PathVariable int userId, @PathVariable int characterId){
        return characterDao.numberComicsWithCharacterTotal(userId, characterId);
    }



    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/comics/{comicId}/characters")
    public List<ComicCharacter> getCharactersByComicId(@PathVariable int comicId){
        return characterDao.getCharactersByComicId(comicId);
    }






}
