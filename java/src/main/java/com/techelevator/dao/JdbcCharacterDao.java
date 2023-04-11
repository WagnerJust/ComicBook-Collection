package com.techelevator.dao;

import com.techelevator.model.Comic;
import com.techelevator.model.ComicCharacter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcCharacterDao implements CharacterDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcCharacterDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ComicCharacter getCharacterById(int characterId) {
        ComicCharacter character = new ComicCharacter();
        try {
            String sql = "SELECT * FROM character_table WHERE character_id = ?";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, characterId);
        } catch(Exception e) {
            throw new RuntimeException("Failed to find Character");
        }
        return character;
    }

    @Override
    public ComicCharacter getCharacterByAlias(String characterName) {
        ComicCharacter character = new ComicCharacter();
        try {
            String sql = "SELECT * FROM character_table WHERE alias = ?";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, characterName);
            character = mapRowToCharacter(results);
        } catch(Exception e) {
            throw new RuntimeException("Failed to find Character");
        }
        return character;
    }

    @Override
    public List<ComicCharacter> getAllCharacters() {
        List<ComicCharacter> characters = new ArrayList<>();
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(
                    "SELECT * FROM character_table");
            while (results.next()){
                characters.add(mapRowToCharacter(results));
            }
        } catch(Exception e) {
            throw new RuntimeException("Failed to list Characters");
        }
        return characters;
    }

    //todo: get all characters by collection/comic/user?

    private ComicCharacter mapRowToCharacter(SqlRowSet results) {
        ComicCharacter character = new ComicCharacter();
        character.setCharacterId(results.getInt("character_id"));
        character.setMarvelCharacterId(results.getInt("character_id_marvel_api"));
        character.setCharacterRealName(results.getString("real_name"));
        character.setCharacterAlias(results.getString("alias"));
        return character;
    }
}
