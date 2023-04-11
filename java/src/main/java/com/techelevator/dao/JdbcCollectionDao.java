package com.techelevator.dao;

import com.techelevator.model.ComicCollection;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCollectionDao implements CollectionDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcCollectionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ComicCollection createCollection(ComicCollection comicCollection) {
        String sql = "INSERT INTO collection(" +
                "user_id, is_public, collection_name) " +
                "VALUES (?, ?, ?) RETURNING collection_id;";
        try {
            Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, comicCollection.getUserId(),
                    comicCollection.getPublic(), comicCollection.getCollectionName());
            comicCollection.setCollectionId(newId);
        } catch(Exception e) {
            //todo: fix these
            throw new RuntimeException("Failed to create Collection");
        }
        return comicCollection;
    }

    @Override
    public List<ComicCollection> listAllCollections() {
        List<ComicCollection> collections = new ArrayList<>();
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT comic_collection.collection_id, comic_collection.comic_data_id \n" +
                    "FROM comic_collection \n" +
                    "JOIN collection ON comic_collection.collection_id = collection.collection_id\n" +
                    "WHERE collection.public = true;");
            while (results.next()) {
                collections.add(mapRowToCollection(results));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to list Collections");
        }
        return collections;
    }

    @Override
    public List<ComicCollection> listCollectionsByUser(int id) {
        List<ComicCollection> collections = new ArrayList<>();
        try {
            String sql = "SELECT collection.collection_id, collection.user_id, collection.collection_name, users.username\n" +
                    "FROM collection\n" +
                    "JOIN users ON collection.user_id = users.user_id\n" +
                    "WHERE collection.public=true AND users.user_id = ?;";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            while (results.next()) {
                collections.add(mapRowToCollection(results));
            }
        } catch(Exception e) {
            throw new RuntimeException("Failed to list Collections by Id");
        }
        return collections;
    }

    @Override
    //todo: add public toggle
    public ComicCollection updateCollectionName(ComicCollection comicCollectionName) {
        try {
            String sql = "UPDATE collection SET collection_name = ? WHERE collection_id = ?;";
            jdbcTemplate.update(sql, comicCollectionName.getCollectionName(), comicCollectionName.getCollectionId());
        } catch(Exception e) {
            throw new RuntimeException("Failed to update Collection Name");
        }
        return comicCollectionName;
    }

    @Override
    public void deleteCollection(int userId, int collectionId) {
        try {
            String sql = "DELETE FROM collection WHERE user_id = ? AND collection_id = ?";
            jdbcTemplate.update(sql, userId, collectionId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete Collection");
        }
    }

    private ComicCollection mapRowToCollection(SqlRowSet results){
        ComicCollection comicCollection = new ComicCollection();
        comicCollection.setCollectionId(results.getInt("collection_id"));
        comicCollection.setCollectionName(results.getString("collection_name"));
        comicCollection.setPublic(results.getBoolean("is_public"));
        return comicCollection;
    }
}
