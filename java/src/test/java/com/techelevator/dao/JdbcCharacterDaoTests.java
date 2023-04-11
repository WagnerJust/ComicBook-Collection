package com.techelevator.dao;

import com.techelevator.model.ComicCharacter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcCharacterDaoTests extends BaseDaoTests{

    protected final ComicCharacter CHARACTER_1 = new ComicCharacter(1,1,  "Peter Parker", "Spiderman");
    protected final ComicCharacter CHARACTER_2 = new ComicCharacter(2,2,  "Bruce Banner", "Hulk");

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


}
