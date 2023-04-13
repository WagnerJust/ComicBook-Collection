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
    public ComicCharacter getCharacterByAlias(@PathVariable String characterName) {
        ComicCharacter character = characterDao.getCharacterByAlias(characterName);
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
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/characters")
    public ComicCharacter addCharacter(@RequestBody ComicCharacter newCharacter){
        return characterDao.addCharacter(newCharacter);
    }

    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/characters/{characterId}")
    public boolean updateCharacter(@PathVariable int characterId, @RequestBody ComicCharacter updatedCharacter){
        return characterDao.updateCharacter(characterId, updatedCharacter);
    }



    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/characters/delete/{characterId}")
    public boolean deleteCharacter(@PathVariable int characterId){
        return characterDao.deleteCharacter(characterId);
    }

    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping("/comic/{comicId}/characters/{characterId}")
    public boolean addCharacterToComic(@PathVariable int comicId, int characterId){
        return characterDao.addCharacterToComic(characterId,comicId);
    }

    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/collections/{collectionId}/stats")
    public int countCharactersInCollection(int characterId ,@PathVariable int collectionId){
        return characterDao.countCharactersInCollection(collectionId,characterId);
    }

    //todo: change name of function below in characterDao
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/user/{userId}/characters/{characterId}")
    public int countComicsByCharacterId(@PathVariable int userId, int characterId){
        return characterDao.countCharactersOfUser(userId, characterId);
    }





//    @PreAuthorize("hasRole('USER')")
//    @ResponseStatus(value = HttpStatus.OK)
//    @GetMapping("/comics/{comicId}/characters")
//    public List<ComicCharacter> getCharactersByComicId(@PathVariable int comicId){
//        //return characterDao.getCharacterByComic(comicId);
//    }






}
