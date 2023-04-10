package com.techelevator.dao;

import com.techelevator.model.Comic;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcComicDao implements ComicDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcComicDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public List<Comic> listAllComicsOfCollection(int collectionId) {
        List<Comic> comics = new ArrayList<>();
        String sql = "SELECT issue_id, image_url, series, upc, issue_number " +
                     "FROM comic_data " +
                     "JOIN collection ON collection.collection_id = comic_collection.collection_id " +
                     "JOIN comic_collection ON comic_collection.comic_id = user_comic.comic_id " +
                     "JOIN user_comic ON user_comic.issue_id = comic_data.issue_id " +
                     "WHERE collection_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, collectionId);
            while (results.next()) {
                comics.add(mapRowToComic(results));
            }
        } catch (DataAccessException e){
            return null;
        }
        return comics;
    }

    @Override
    public Comic getComic(int comicId) {
        Comic comic = null;
        String sql = "SELECT issue_id, image_url, series, upc, issue_number " +
                     "FROM comic_data " +
                     "WHERE comic_id = ?;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, comicId);
            if (results.next()) {
                comic = mapRowToComic(results);
            }
        } catch (DataAccessException e){
            return null;
        }
        return comic;
    }

    @Override
    public Comic addComic(Comic comic) {
        Comic comicNew;
        String sql = "INSERT INTO comic_data (upc, issue_number, series, publish_date, image_url) " +
                     "VALUES (?, ?, ?, ?, ?) RETURNING comic_id;";
        try{
            Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, comic.getUpc(), comic.getIssueNumber(),
                    comic.getSeriesName(), comic.getPublish_date(), comic.getImageURL());
            comicNew = getComic(newId);
        } catch (DataAccessException e){
            return null;
        }
        return comicNew;
    }

    @Override
    public boolean addComicToCollection(int comicId, int collectionId) {
        String sql = "INSERT INTO comic_collection (comic_id, collection_id) " +
                     "VALUES (?, ?);";
        try{
            jdbcTemplate.update(sql, comicId, collectionId);
        }catch (DataAccessException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean removeComicFromCollection(int comicId, int collectionId) {
        String sql = "DELETE FROM comic_collection " +
                     "WHERE comic_id = ?;";
        try{
            jdbcTemplate.update(sql, collectionId);
        }catch (DataAccessException e){
            return false;
        }
        return true;
    }

    @Override
    public boolean updateComic(Comic comic) {
        String sql = "UPDATE comic_data " +
                     "SET upc = ?, issue_number = ?, series = ?, publish_date = ?, image_url = ? " +
                     "WHERE comic_id = ?;";
        try{
            jdbcTemplate.update(sql, comic.getUpc(), comic.getIssueNumber(), comic.getSeriesName(), comic.getPublish_date(),
                     comic.getImageURL(), comic.getComicId());
        }catch (DataAccessException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteComic(int comicId) {
        String sql = "DELETE FROM comic_data WHERE comic_data_id = ?;";
        String sqlJoinTable = "DELETE FROM comic_collection WHERE comic_data_id = ?;";
        try{
            jdbcTemplate.update(sql, comicId);
            jdbcTemplate.update(sql, comicId);
        }catch (DataAccessException e){
            return false;
        }
        return true;
    }

    private Comic mapRowToComic(SqlRowSet results) {
        Comic comic = new Comic();
        comic.setImageURL(results.getString("image_url"));
        comic.setIssueNumber(results.getInt("issue_number"));
        comic.setSeriesName(results.getString("series"));
        comic.setUpc(results.getString("upc"));
        comic.setComicId(results.getInt("comic_id"));
        return comic;
    }
}
