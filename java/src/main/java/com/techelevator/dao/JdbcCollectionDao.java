package com.techelevator.dao;

import com.techelevator.model.ComicCollection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcCollectionDao implements CollectionDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCollectionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //TODO:
    @Override
    public ComicCollection createCollection(ComicCollection comicCollection) {
        String sql = "INSERT INTO collection(" +
                "user_id, public, collection_name) " +
                "VALUES (?, ?, ?) RETURNING collection_id;";

        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, comicCollection.getUserId(),
                comicCollection.getPublic(), comicCollection.getCollectionName());
        comicCollection.setCollectionId(newId);

        return comicCollection;
    }

    @Override
    public ComicCollection getCollectionByCollectionId(int id) {
        ComicCollection collection = null;

        String sql = "SELECT * FROM collection WHERE collection_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            collection = mapRowToCollection(results);
        }
        if (collection == null) {
            return null;
        }

        return collection;
    }


    @Override
    public List<ComicCollection> listAllCollections() {
        List<ComicCollection> collections = new ArrayList<>();

        SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT * \n" +
                "FROM collection \n" +
                "WHERE public = true;");
        while (results.next()) {
            collections.add(mapRowToCollection(results));
        }

        return collections;
    }


    @Override
    public List<ComicCollection> listCollectionsByUser(int id) {
        List<ComicCollection> collections = new ArrayList<>();

        String sql = "SELECT *\n" +
                "FROM collection\n" +
                "WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while (results.next()) {
            collections.add(mapRowToCollection(results));
        }
        if (collections.size() == 0) {
            return null;
        }

        return collections;
    }

    @Override
    public List<ComicCollection> listCollectionsByUserWithoutSpecifiedComic(int userId, String comicId) {
        List<ComicCollection> collections = new ArrayList<>();
        String sql = "SELECT * FROM collection\n" +
                "WHERE user_id = ? AND collection_id NOT IN\n" +
                "(SELECT collection.collection_id FROM collection\n" +
                "JOIN comic_collection ON collection.collection_id = comic_collection.collection_id\n" +
                "JOIN comic_data ON comic_data.comic_data_id = comic_collection.comic_data_id\n" +
                "WHERE collection.user_id = ? AND comic_data.upc = ?)";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, userId, comicId);
        while (results.next()) {
            collections.add(mapRowToCollection(results));
        }
        if (collections.size() == 0) {
            return null;
        }

        return collections;

    }

    @Override
    public ComicCollection updateCollectionName(int collectionId, ComicCollection comicCollectionName) {

        String sql = "UPDATE collection SET collection_name = ?, public = ? WHERE collection_id = ?;";
        jdbcTemplate.update(sql, comicCollectionName.getCollectionName(), comicCollectionName.getPublic(), comicCollectionName.getCollectionId());

        return comicCollectionName;
    }

    @Override
    public void deleteCollection(int userId, int collectionId) {
        String sql = "DELETE FROM comic_collection WHERE collection_id = ?;";
        jdbcTemplate.update(sql, collectionId);

        sql = "DELETE FROM collection WHERE user_id = ? AND collection_id = ?; ";
        jdbcTemplate.update(sql, userId, collectionId);


    }


    private ComicCollection mapRowToCollection(SqlRowSet results) {
        ComicCollection comicCollection = new ComicCollection();
        comicCollection.setUserId(results.getInt("user_id"));
        comicCollection.setCollectionId(results.getInt("collection_id"));
        comicCollection.setCollectionName(results.getString("collection_name"));
        comicCollection.setPublic(results.getBoolean("public"));
        return comicCollection;
    }
}