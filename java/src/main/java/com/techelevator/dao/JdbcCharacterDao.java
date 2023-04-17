package com.techelevator.dao;

import com.techelevator.model.ComicCharacter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcCharacterDao implements CharacterDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCharacterDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //todo: get all characters by collection/comic/user?
    @Override
    public ComicCharacter getCharacterById(int characterId) {
        ComicCharacter character = null;
        String sql = "SELECT * FROM character_table WHERE character_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, characterId);
        if (results.next()) {
            character = mapRowToCharacter(results);
        }
        if (character == null) {
            return null;
        }

        return character;
    }

//    @Override
//    public List<ComicCharacter> getCharacterByAlias(String characterName) {
//        List<ComicCharacter> characters = new ArrayList<>();
//        String sql = "SELECT * FROM character_table WHERE alias ILIKE ?";
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%" + characterName + "%");
//        while (results.next()) {
//            characters.add(mapRowToCharacter(results));
//        }
//        if (characters.size() == 0) {
//            return null;
//        }
//
//        return characters;
//    }

    @Override
    public List<ComicCharacter> getCharacterByRealName(String characterName) {
        List<ComicCharacter> characters = new ArrayList<>();
        String sql = "SELECT * FROM character_table WHERE name ILIKE ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%" + characterName + "%");
        while (results.next()) {
            characters.add(mapRowToCharacter(results));
        }
        if (characters.size() == 0) {
            return null;
        }
        return characters;
    }

    @Override
    public List<ComicCharacter> getAllCharacters() {
        List<ComicCharacter> characters = new ArrayList<>();

        SqlRowSet results = jdbcTemplate.queryForRowSet(
                "SELECT * FROM character_table");
        while (results.next()) {
            characters.add(mapRowToCharacter(results));
        }

        return characters;
    }

    @Override
    public List<ComicCharacter> getCharactersByComicId(int comicId) {
        List<ComicCharacter> characters = new ArrayList<>();

        String sql = "SELECT * \n" +
                "FROM character_table \n" +
                "JOIN character_comic ON character_comic.character_id = character_table.character_id\n" +
                "JOIN comic_data ON comic_data.comic_data_id = character_comic.comic_data_id\n" +
                "WHERE comic_data.comic_data_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, comicId);
        while (results.next()) {
            characters.add(mapRowToCharacter(results));
        }
        if (characters.size() == 0) {
            return null;
        }

        return characters;
    }

    @Override
    public List<ComicCharacter> getCharactersByCollectionId(int collectionId){
        List<ComicCharacter> charactersList = new ArrayList<>();
        String sql = "select distinct character_table.character_id, character_id_marvel_api, name\n" +
                "from character_table\n" +
                "join character_comic ON character_comic.character_id = character_table.character_id\n" +
                "join comic_data ON comic_data.comic_data_id = character_comic.comic_data_id\n" +
                "join comic_collection ON comic_collection.comic_data_id = comic_data.comic_data_id\n" +
                "where comic_collection.collection_id = ?" +
                "order by name asc";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, collectionId);
        while (results.next()){
            charactersList.add(mapRowToCharacter(results));
        }
        if (charactersList.size() == 0) {
            return null;
        }
        return charactersList;
    }

    @Override
    public ComicCharacter addCharacter(ComicCharacter newCharacter) {
        ComicCharacter comicCharacter;
        String sql = "INSERT INTO character_table (character_id_marvel_api, name) " +
                "VALUES (?, ?) RETURNING character_id;";

        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, newCharacter.getMarvelCharacterId(),
                newCharacter.getCharacterName());
        comicCharacter = getCharacterById(newId);

        return comicCharacter;
    }

    @Override
    public void updateCharacter(int characterId, ComicCharacter updatedCharacter) {
        String sql = "UPDATE character_table " +
                "SET character_id_marvel_api = ?, name = ? " +
                "WHERE character_id = ?;";

        jdbcTemplate.update(sql, updatedCharacter.getMarvelCharacterId(),
                updatedCharacter.getCharacterName(), updatedCharacter.getCharacterId());
    }

    @Override
    public void deleteCharacter(int characterId) {
        String sql = "DELETE FROM character_table " +
                "WHERE character_id = ?;";
        String sqlJoinTable = "DELETE FROM character_comic " +
                "WHERE character_id = ?;";

        jdbcTemplate.update(sqlJoinTable, characterId);
        jdbcTemplate.update(sql, characterId);

    }

    @Override
    public void addCharacterToComic(int characterId, int comicId) {
        String sql = "INSERT INTO character_comic (character_id, comic_data_id) " +
                "VALUES(?, ?);";

        jdbcTemplate.update(sql, characterId, comicId);

    }

    @Override
    public int numberComicsInCollectionWithCharacter(int collectionId, int characterId) {
        int result = -1;
        String sql = "SELECT COUNT(*) AS total FROM character_table\n" +
                "JOIN character_comic ON character_comic.character_id = character_table.character_id\n" +
                "JOIN comic_collection ON comic_collection.comic_data_id = character_comic.comic_data_id\n" +
                "JOIN collection ON comic_collection.collection_id = collection.collection_id\n" +
                "WHERE character_table.character_id = ? AND collection.collection_id = ?;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, characterId, collectionId);
        if (rowSet.next()) {
            result = rowSet.getInt("total");
        }

        return result;
    }

    @Override
    public int numberComicsWithCharacterTotal(int userId, int characterId) {
        int result = -1;
        String sql = "SELECT COUNT(DISTINCT comic_collection.comic_data_id) AS total FROM character_table\n" +
                "JOIN character_comic ON character_comic.character_id = character_table.character_id\n" +
                "JOIN comic_collection ON comic_collection.comic_data_id = character_comic.comic_data_id\n" +
                "JOIN collection ON comic_collection.collection_id = collection.collection_id\n" +
                "WHERE character_table.character_id = ? AND collection.user_id = ?;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, characterId, userId);
        if (rowSet.next()) {
            result = rowSet.getInt("total");
        }
        return result;
    }


    private ComicCharacter mapRowToCharacter(SqlRowSet results) {
        ComicCharacter character = new ComicCharacter();
        character.setCharacterId(results.getInt("character_id"));
        character.setMarvelCharacterId(results.getInt("character_id_marvel_api"));
        character.setCharacterName(results.getString("name"));
        return character;
    }
}
