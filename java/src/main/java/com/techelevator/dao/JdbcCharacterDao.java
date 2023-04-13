package com.techelevator.dao;

import com.techelevator.model.ComicCharacter;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
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
    public List<ComicCharacter> getCharacterByAlias(String characterName) {
        List<ComicCharacter> characters = new ArrayList<>();
        try {
            String sql = "SELECT * FROM character_table WHERE alias ILIKE ?";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%" + characterName + "%");
            while (results.next()){
                characters.add(mapRowToCharacter(results));
            }
        } catch(Exception e) {
            throw new RuntimeException("Failed to find Character");
        }
        return characters;
    }

    @Override
    public List<ComicCharacter> getCharacterByRealName(String characterName) {
        List<ComicCharacter> characters = new ArrayList<>();
        try {
            String sql = "SELECT * FROM character_table WHERE real_name ILIKE ?";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%" + characterName + "%");
            while (results.next()){
                characters.add(mapRowToCharacter(results));
            }
        } catch(Exception e) {
            throw new RuntimeException("Failed to find Character");
        }
        return characters;
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
    public List<ComicCharacter> getCharactersByComicId(int comicId) {
        List<ComicCharacter> characters = new ArrayList<>();
        try {
            String sql = "SELECT * \n" +
                    "FROM character_table \n" +
                    "JOIN character_comic ON character_comic.character_id = character_table.character_id\n" +
                    "JOIN comic_data ON comic_data.comic_data_id = character_comic.comic_data_id\n" +
                    "WHERE comic_data.comic_data_id = ?";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, comicId);
            while (results.next()){
                characters.add(mapRowToCharacter(results));
            }
        } catch(Exception e) {
            throw new RuntimeException("Failed to find Character");
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
    public boolean updateCharacter(int characterId, ComicCharacter updatedCharacter) {
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

    @Override
    public int countCollectionComicsWithCharacter(int collectionId, int characterId) {
        int result = -1;
        String sql = "SELECT COUNT(*) AS total FROM character_table\n" +
                     "JOIN character_comic ON character_comic.character_id = character_table.character_id\n" +
                     "JOIN comic_collection ON comic_collection.comic_data_id = character_comic.comic_data_id\n" +
                     "JOIN collection ON comic_collection.collection_id = collection.collection_id\n" +
                     "WHERE character_table.character_id = ? AND collection.collection_id = ?;";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, characterId, collectionId);
            if (rowSet.next()){
                result = rowSet.getInt("total");
            }
        }catch (DataAccessException e){
            return -1;
        }
        return result;
    }

    @Override
    public int countUserComicsWithCharacter(int userId, int characterId) {
        int result = -1;
        String sql = "SELECT COUNT(DISTINCT comic_collection.comic_data_id) AS total FROM character_table\n" +
                     "JOIN character_comic ON character_comic.character_id = character_table.character_id\n" +
                     "JOIN comic_collection ON comic_collection.comic_data_id = character_comic.comic_data_id\n" +
                     "JOIN collection ON comic_collection.collection_id = collection.collection_id\n" +
                     "WHERE character_table.character_id = ? AND collection.user_id = ?;";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, characterId, userId);
            if (rowSet.next()){
                result = rowSet.getInt("total");
            }
        }catch (DataAccessException e){
            return -1;
        }
        return result;
    }


    private ComicCharacter mapRowToCharacter(SqlRowSet results) {
        ComicCharacter character = new ComicCharacter();
        character.setCharacterId(results.getInt("character_id"));
        character.setMarvelCharacterId(results.getInt("character_id_marvel_api"));
        character.setCharacterRealName(results.getString("real_name"));
        character.setCharacterAlias(results.getString("alias"));
        return character;
    }
}
