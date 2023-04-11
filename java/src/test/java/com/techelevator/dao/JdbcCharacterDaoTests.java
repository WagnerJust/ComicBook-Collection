package com.techelevator.dao;

import com.techelevator.model.ComicCharacter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcCharacterDaoTests extends BaseDaoTests{

    protected final ComicCharacter CHARACTER_1 = new ComicCharacter(1,1,  "name1", "alias1");
    protected final ComicCharacter CHARACTER_2 = new ComicCharacter(2,2,  "name2", "alias2");

    private ComicCharacter characterTest;
    private ComicCharacter characterTest2;

    private JdbcCharacterDao sut;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcCharacterDao(jdbcTemplate);
        characterTest = new ComicCharacter(100, 100, "testname", "testalias");
        characterTest2 = new ComicCharacter(101, 101, "testname2", "testalias2");
    }

    @Test
    public void getAllCharactersReturnsAllCharacters(){
        List<ComicCharacter> characters = sut.getAllCharacters();

        Assert.assertEquals(5, characters.size());
    }

    @Test
    public void getCharacterByAliasReturnsCorrectCharacter(){
        ComicCharacter character = sut.getCharacterByAlias(CHARACTER_1.getCharacterAlias());

        //Assert.assertEquals(CHARACTER_1, character);
        assertCharactersMatch(CHARACTER_1, character);
    }

    @Test
    public void getCharacterByIdReturnsCorrectCharacter(){
        ComicCharacter character = sut.getCharacterById(CHARACTER_1.getCharacterId());

        assertCharactersMatch(CHARACTER_1, character);

    }


    public void assertCharactersMatch(ComicCharacter expected, ComicCharacter actual) {
        Assert.assertEquals(expected.getCharacterId(), actual.getCharacterId());
        Assert.assertEquals(expected.getMarvelCharacterId(), actual.getMarvelCharacterId());
        Assert.assertEquals(expected.getCharacterAlias(), actual.getCharacterAlias());
        Assert.assertEquals(expected.getCharacterRealName(), actual.getCharacterRealName());
    }

}
