package com.techelevator.dao;

import com.techelevator.model.ComicCollection;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
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
            throw new RuntimeException("Failed to create Collection");
        }
        return comicCollection;
    }

    @Override
    public List<ComicCollection> listComicsInCollection(ComicCollection comicCollectionId) {
        List<ComicCollection> comics = new ArrayList<>();
        String sql = "SELECT collection_id, comic_id FROM comic_collection WHERE collection_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, comicCollectionId.getCollectionId());
            while (results.next()) {
                ComicCollection comic = mapRowToCollection(results);
                comics.add(comic);
            }
        } catch(Exception e) {
            throw new RuntimeException("Failed to list Comics");
        }
        return comics;
    }

    @Override
    public List<ComicCollection> listAllCollections() {
        List<ComicCollection> collections = new ArrayList<>();
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT comic_collection.collection_id, comic_collection.comic_id \n" +
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
    public List<ComicCollection> listCollectionsByUser(User username) {
        ArrayList<ComicCollection> collections = new ArrayList<>();
        try {
            String sql = "SELECT collection.collection_id, collection.user_id, collection.collection_name, users.username\n" +
                    "FROM collection\n" +
                    "JOIN users ON collection.user_id = users.user_id\n" +
                    "WHERE collection.public=true AND users.username = ?;";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            while (results.next()) {
                collections.add(mapRowToCollection(results));
            }
        } catch(Exception e) {
            throw new RuntimeException("Failed to list Collections by Username");
        }
        return collections;
    }

    @Override
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
    public void deleteCollection(ComicCollection comicCollectionName) {
        try {
            String sql = "DELETE FROM collection WHERE collection_name = ?";
            jdbcTemplate.update(sql, comicCollectionName.getCollectionName());
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
