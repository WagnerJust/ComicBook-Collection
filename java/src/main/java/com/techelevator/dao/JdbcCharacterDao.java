package com.techelevator.dao;

import com.techelevator.model.Comic;
import com.techelevator.model.ComicCharacter;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcCharacterDao implements CharacterDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcCharacterDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //todo: get all characters by collection/comic/user?
    @Override
    public ComicCharacter getCharacterById(int characterId) {
        ComicCharacter character = new ComicCharacter();
        try {
            String sql = "SELECT * FROM character_table WHERE character_id = ?";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, characterId);
            if(results.next()){
                character = mapRowToCharacter(results);
            }
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
            if(results.next()) {
                character = mapRowToCharacter(results);
            }
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

    @Override
    public ComicCharacter addCharacter(ComicCharacter newCharacter) {
        ComicCharacter comicCharacter;
        String sql = "INSERT INTO character_table (character_id_marvel_api, alias, real_name) " +
                "VALUES (?, ?, ?) RETURNING character_id;";
        try{
            Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, newCharacter.getMarvelCharacterId(),
                    newCharacter.getCharacterAlias(), newCharacter.getCharacterRealName());
            comicCharacter = getCharacterById(newId);
        } catch (DataAccessException e){
            return null;
        }
        return comicCharacter;
    }

    @Override
    public boolean updateCharacter(ComicCharacter updatedCharacter) {
        String sql = "UPDATE character_table " +
                "SET character_id_marvel_api = ?, alias = ?, real_name = ? " +
                "WHERE character_id = ?;";
        try{
            jdbcTemplate.update(sql, updatedCharacter.getMarvelCharacterId(), updatedCharacter.getCharacterAlias(),
                    updatedCharacter.getCharacterRealName(), updatedCharacter.getCharacterId());
        }catch (DataAccessException e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteCharacter(int characterId) {
        String sql = "DELETE FROM character_table " +
                "WHERE character_id = ?;";
        String sqlJoinTable = "DELETE FROM character_comic " +
                "WHERE character_id = ?;";
        try{
            jdbcTemplate.update(sql, characterId);
            jdbcTemplate.update(sqlJoinTable, characterId);
        }catch (DataAccessException e){
            return false;
        }
        return true;
    }

    @Override
    public boolean addCharacterToComic(int characterId, int comicId) {
        String sql = "INSERT INTO character_comic (character_id, comic_data_id) " +
                "VALUES(?, ?);";
        try{
            jdbcTemplate.update(sql, characterId, comicId);
        }catch (DataAccessException e){
            return false;
        }
        return true;
    }


    //todo: get all characters by comic

    private ComicCharacter mapRowToCharacter(SqlRowSet results) {
        ComicCharacter character = new ComicCharacter();
        character.setCharacterId(results.getInt("character_id"));
        character.setMarvelCharacterId(results.getInt("character_id_marvel_api"));
        character.setCharacterRealName(results.getString("real_name"));
        character.setCharacterAlias(results.getString("alias"));
        return character;
    }
}
