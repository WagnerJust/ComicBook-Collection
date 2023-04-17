package com.techelevator.dao;

import com.techelevator.model.ComicCharacter;
import com.techelevator.model.Statistics;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcStatsDao implements StatsDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcStatsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Statistics> collectionCharacterStats(int collectionId) {
        List<Statistics> statisticsList = new ArrayList<>();
        String sql = "select count(comic_data.author) as num_comics_author, comic_data.author\n" +
                "from comic_data\n" +
                "join comic_collection ON comic_collection.comic_data_id = comic_data.comic_data_id\n" +
                "join collection ON collection.collection_id = comic_collection.collection_id\n" +
                "where collection.collection_id = ?\n" +
                "group by comic_data.author\n" +
                "order by 1 desc;";

        SqlRowSet results =  jdbcTemplate.queryForRowSet(sql, collectionId);

        while(results.next()){
            statisticsList.add(mapRowToStatistics(results, "character"));
        }
        return statisticsList;
    }

    @Override
    public List<Statistics> collectionAuthorStats(int collectionId) {
        List<Statistics> statisticsList = new ArrayList<>();


        String sql = "select count(comic_data.author) as num_comics_author, comic_data.author\n" +
                "from comic_data\n" +
                "join comic_collection ON comic_collection.comic_data_id = comic_data.comic_data_id\n" +
                "join collection ON collection.collection_id = comic_collection.collection_id\n" +
                "where collection.collection_id = ?\n" +
                "group by comic_data.author\n" +
                "order by 1 desc;";

        SqlRowSet results =  jdbcTemplate.queryForRowSet(sql, collectionId);
        while(results.next()){
            statisticsList.add(mapRowToStatistics(results, "author"));
        }
        return statisticsList;
    }

    @Override
    public List<Statistics> collectionSeriesStats(int collectionId) {
        List<Statistics> statisticsList = new ArrayList<>();
        String sql = "select count(comic_data.series) as num_comics_series, comic_data.series\n" +
                "from character_table\n" +
                "join character_comic ON character_comic.character_id = character_table.character_id\n" +
                "join comic_data ON comic_data.comic_data_id = character_comic.comic_data_id\n" +
                "join comic_collection ON comic_collection.comic_data_id = comic_data.comic_data_id\n" +
                "join collection ON collection.collection_id = comic_collection.collection_id\n" +
                "where collection.collection_id = ?\n" +
                "group by comic_data.series\n" +
                "order by 1 desc;";

        SqlRowSet results =  jdbcTemplate.queryForRowSet(sql, collectionId);

        while(results.next()){
            statisticsList.add(mapRowToStatistics(results, "series"));
        }
        return statisticsList;
    }

    @Override
    public List<Statistics> siteStatistics() {
        List<Statistics> statisticsList = new ArrayList<>();
        String sql = "select count(character_comic.character_id) as num_comics_character , character_table.name, count(comic_data.author) as num_comics_author, comic_data.author, count(comic_data.series) as num_comics_series, comic_data.series\n" +
                "from character_table\n" +
                "join character_comic ON character_comic.character_id = character_table.character_id\n" +
                "join comic_data ON comic_data.comic_data_id = character_comic.comic_data_id\n" +
                "join comic_collection ON comic_collection.comic_data_id = comic_data.comic_data_id\n" +
                "join collection ON collection.collection_id = comic_collection.collection_id\n" +
                "group by character_table.name, comic_data.author,  comic_data.series;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            statisticsList.add(mapRowToStatistics(results, "all"));
        }
        return statisticsList;

    }

    private Statistics mapRowToStatistics(SqlRowSet results, String type) {
        Statistics statistics = new Statistics();
        switch(type){
            case "character":
                statistics.setNum_comics_character(results.getInt(1));
                statistics.setName(results.getString(2));
                break;
            case "author":
                statistics.setNum_comics_author(results.getInt(1));
                statistics.setAuthor(results.getString(2));
                break;
            case "series":
                statistics.setNum_comics_series(results.getInt(1));
                statistics.setSeries(results.getString(2));
//            case "all":
//                statistics.setNum_comics_character(results.getInt("num_comics_character"));
//                statistics.setName(results.getString("name"));
//                statistics.setNum_comics_author(results.getInt("num_comics_author"));
//                statistics.setAuthor(results.getString("author"));
//                statistics.setNum_comics_series(results.getInt("num_comics_series"));
//                statistics.setSeries(results.getString("series"));
            default:
                break;
        }

        return statistics;
    }
}
