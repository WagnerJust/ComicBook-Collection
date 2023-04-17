package com.techelevator.dao;

import com.techelevator.model.Comic;
import com.techelevator.model.ComicCharacter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

public class JdbcCharacterDaoTests extends BaseDaoTests{

    protected final ComicCharacter CHARACTER_1 = new ComicCharacter(1,1,  "name1");
    protected final ComicCharacter CHARACTER_2 = new ComicCharacter(2,2,  "name2");

    protected final Comic COMIC_1 = new Comic("series1", 1, 1, "UPC1", "URL1", LocalDate.parse("2011-01-01"), "author1", "artist1");

    private ComicCharacter characterTest;
    private ComicCharacter characterTest2;

    private JdbcCharacterDao sut;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcCharacterDao(jdbcTemplate);
        characterTest = new ComicCharacter(100, 100, "testname");
        characterTest2 = new ComicCharacter(101, 101, "testname2");
    }

    @Test
    public void getAllCharactersReturnsAllCharacters(){
        List<ComicCharacter> characters = sut.getAllCharacters();

        Assert.assertEquals(5, characters.size());
    }


    @Test
    public void getCharacterByRealNameReturnsCorrectCharacter(){
        List<ComicCharacter> character = sut.getCharacterByRealName(CHARACTER_1.getCharacterName());

        Assert.assertEquals(1, character.size());
    }

    @Test
    public void getCharacterByRealName_returns_void_if_invalid_alias(){
        Assert.assertEquals(null, sut.getCharacterByRealName("z"));
    }

    @Test
    public void getCharacterByComicIdReturnsCorrectCharacter(){
        List<ComicCharacter> character = sut.getCharactersByComicId(COMIC_1.getComicId());

        Assert.assertEquals(2, character.size());
    }

    @Test
    public void getCharacterByComicId_returns_void_if_invalid_id(){
        Assert.assertEquals(null, sut.getCharactersByComicId(-1));
    }

    @Test
    public void getCharacterByIdReturnsCorrectCharacter(){
        ComicCharacter character = sut.getCharacterById(CHARACTER_1.getCharacterId());

        assertCharactersMatch(CHARACTER_1, character);
    }

    @Test
    public void getCharacterById_returns_null_if_invalid_id(){
        Assert.assertEquals(null, sut.getCharacterById(-1));
    }

    @Test
    public void updateCharacterUpdatesCharacter(){
        ComicCharacter characterToUpdate = sut.getCharacterById(CHARACTER_1.getCharacterId());

        characterToUpdate.setMarvelCharacterId(10);
        characterToUpdate.setCharacterName("updatedRealName");
        sut.updateCharacter(characterToUpdate.getCharacterId(), characterToUpdate);

        ComicCharacter retrievedCharacter = sut.getCharacterById(CHARACTER_1.getCharacterId());
        assertCharactersMatch(characterToUpdate, retrievedCharacter);
    }

    @Test
    public void addCharacterAddsCharacter(){
        ComicCharacter newCharacter = sut.addCharacter(characterTest);

        int newId = newCharacter.getCharacterId();
        Assert.assertTrue(newId > 0);

        characterTest.setCharacterId(newId);
        assertCharactersMatch(characterTest, newCharacter);
    }

    @Test
    public void deletedCharacterCantBeRetrieved() {
        sut.deleteCharacter(1);

        ComicCharacter retrievedCharacter = sut.getCharacterById(1);
        Assert.assertNull(retrievedCharacter);
    }

    @Test
    public void countCharactersInCollection_returns_correct_number() {
        Assert.assertEquals(3, sut.numberComicsInCollectionWithCharacter(1, 1));
    }

    @Test
    public void countCharactersOfUser_returns_correct_number(){
        Assert.assertEquals(5, sut.numberComicsWithCharacterTotal(1, 1));
        Assert.assertEquals(2, sut.numberComicsWithCharacterTotal(1, 2));
    }

    @Test
    public void addCharacterToComic_adds_to_comic(){
        sut.addCharacterToComic(1, 6);
        assertCharactersMatch(CHARACTER_1, sut.getCharactersByComicId(6).get(0));
    }

    @Test
    public void getCharactersByCollectionId_gets_correct_list(){
        Assert.assertEquals(3, sut.getCharactersByCollectionId(1).size());
        Assert.assertEquals(2, sut.getCharactersByCollectionId(2).size());
        assertCharactersMatch(CHARACTER_1, sut.getCharactersByCollectionId(1).get(0));
    }


    public void assertCharactersMatch(ComicCharacter expected, ComicCharacter actual) {
        Assert.assertEquals(expected.getCharacterId(), actual.getCharacterId());
        Assert.assertEquals(expected.getMarvelCharacterId(), actual.getMarvelCharacterId());
        Assert.assertEquals(expected.getCharacterName(), actual.getCharacterName());
    }

}
