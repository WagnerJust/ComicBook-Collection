package com.techelevator.dao;

import com.techelevator.model.Comic;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcComicDao implements ComicDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcComicDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Comic> listAllComicsOfCollection(int collectionId) {
        List<Comic> comics = new ArrayList<>();
        String sql = "SELECT *  \n" +
                "FROM comic_data  \n" +
                "JOIN comic_collection ON comic_collection.comic_data_id = comic_data.comic_data_id  \n" +
                "JOIN collection ON collection.collection_id = comic_collection.collection_id  \n" +
                "WHERE collection.collection_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, collectionId);
        while (results.next()) {
            comics.add(mapRowToComic(results));
        }

        return comics;
    }

    @Override
    public Comic getComic(int comicId) {
        Comic comic = null;
        String sql = "SELECT * " +
                "FROM comic_data " +
                "WHERE comic_data_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, comicId);
        if (results.next()) {
            comic = mapRowToComic(results);
        }

        return comic;
    }

    @Override
    public Comic getComicByUpc(String upc) {
        Comic comic = null;
        String sql = "SELECT * " +
                "FROM comic_data " +
                "WHERE upc = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, upc);
        if (results.next()) {
            comic = mapRowToComic(results);
        }

        return comic;
    }

    @Override
    public List<Comic> getComicsByAuthor(String author) {
        ArrayList<Comic> comics = new ArrayList<>();
        String sql = "SELECT * " +
                "FROM comic_data " +
                "WHERE author ILIKE ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%" + author + "%");
        while (results.next()) {
            comics.add(mapRowToComic(results));
        }

        return comics;
    }

    @Override
    public List<Comic> getComicsByArtist(String artist) {
        ArrayList<Comic> comics = new ArrayList<>();
        String sql = "SELECT * " +
                "FROM comic_data " +
                "WHERE artist ILIKE ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%" + artist + "%");
        while (results.next()) {
            comics.add(mapRowToComic(results));
        }

        return comics;
    }

    @Override
    public List<Comic> getComicsByCharacter(int characterId) {
        ArrayList<Comic> comics = new ArrayList<>();
        String sql = "Select * FROM comic_data " +
                "JOIN character_comic ON character_comic.comic_data_id = comic_data.comic_data_id " +
                "WHERE character_comic.character_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, characterId);
        while (results.next()) {
            comics.add(mapRowToComic(results));
        }

        return comics;
    }

    @Override
    public List<Comic> getComicsBySeries(String series) {
        ArrayList<Comic> comics = new ArrayList<>();
        String sql = "SELECT * " +
                "FROM comic_data " +
                "WHERE series ILIKE ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%" + series + "%");
        while (results.next()) {
            comics.add(mapRowToComic(results));
        }

        return comics;
    }


    @Override
    public Comic addComic(Comic comic) {
        Comic comicNew;
        String sql = "INSERT INTO comic_data (upc, issue_number, series, publish_date, image_url, author, artist) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING comic_data_id;";

        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, comic.getUpc(), comic.getIssueNumber(),
                comic.getSeriesName(), comic.getPublish_date(), comic.getImageURL(), comic.getAuthor(), comic.getArtist());
        comicNew = getComic(newId);

        return comicNew;
    }

    @Override
    public void addComicToCollection(int comicId, int collectionId) {
        String sql = "INSERT INTO comic_collection (comic_data_id, collection_id) " +
                "VALUES (?, ?);";

        jdbcTemplate.update(sql, comicId, collectionId);


    }

    @Override
    public void removeComicFromCollection(int comicId, int collectionId) {
        String sql = "DELETE FROM comic_collection " +
                "WHERE comic_data_id = ?;";

        jdbcTemplate.update(sql, collectionId);

    }

    @Override
    public void updateComic(Comic comic) {
        String sql = "UPDATE comic_data " +
                "SET upc = ?, issue_number = ?, series = ?, publish_date = ?, image_url = ?, author = ?, artist = ? " +
                "WHERE comic_data_id = ?;";

        jdbcTemplate.update(sql, comic.getUpc(), comic.getIssueNumber(), comic.getSeriesName(), comic.getPublish_date(),
                comic.getImageURL(), comic.getAuthor(), comic.getArtist(), comic.getComicId());

    }

    @Override
    public void deleteComic(int comicId) {
        String sql = "DELETE FROM comic_data WHERE comic_data_id = ?;";
        String sqlCollectionJunctionTable = "DELETE FROM comic_collection WHERE comic_data_id = ?;";
        String sqlCharacterJunctionTable = "DELETE FROM character_comic WHERE comic_data_id = ?";

        jdbcTemplate.update(sqlCollectionJunctionTable, comicId);
        jdbcTemplate.update(sqlCharacterJunctionTable, comicId);
        jdbcTemplate.update(sql, comicId);

    }

    @Override
    public int countUniqueComicsOfUser(int userId) {
        int result = -1;
        String sql = "SELECT COUNT(DISTINCT comic_collection.comic_data_id) AS total FROM collection\n" +
                "JOIN comic_collection ON comic_collection.collection_id = collection.collection_id\n" +
                "WHERE collection.user_id = ?;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
        if (rowSet.next()) {
            result = rowSet.getInt("total");
        }
        return result;
    }

    private Comic mapRowToComic(SqlRowSet results) {
        Comic comic = new Comic();
        comic.setImageURL(results.getString("image_url"));
        comic.setIssueNumber(results.getInt("issue_number"));
        comic.setSeriesName(results.getString("series"));
        comic.setUpc(results.getString("upc"));
        comic.setComicId(results.getInt("comic_data_id"));
        comic.setAuthor(results.getString("author"));
        comic.setArtist(results.getString("artist"));
        if (results.getDate("publish_date") != null) {
            comic.setPublish_date(results.getDate("publish_date").toLocalDate());
        }
        return comic;
    }
}
