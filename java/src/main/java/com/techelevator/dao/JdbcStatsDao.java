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
    public List<Statistics> collectionStatistics(int collectionId) {
        List<Statistics> statisticsList = new ArrayList<>();
        String sql = "select count(character_comic.character_id) as num_comics_character , character_table.name, count(comic_data.author) as num_comics_author, comic_data.author, count(comic_data.series) as num_comics_series, comic_data.series\n" +
                "from character_table\n" +
                "join character_comic ON character_comic.character_id = character_table.character_id\n" +
                "join comic_data ON comic_data.comic_data_id = character_comic.comic_data_id\n" +
                "join comic_collection ON comic_collection.comic_data_id = comic_data.comic_data_id\n" +
                "join collection ON collection.collection_id = comic_collection.collection_id\n" +
                "where collection.collection_id = ?\n" +
                "group by character_table.name, comic_data.author,  comic_data.series;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, collectionId);
        while(results.next()){
            statisticsList.add(mapRowToStatistics(results));
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
            statisticsList.add(mapRowToStatistics(results));
        }
        return statisticsList;

    }

    private Statistics mapRowToStatistics(SqlRowSet results) {
        Statistics statistics = new Statistics();
        statistics.setNum_comics_character(results.getInt("num_comics_character"));
        statistics.setName(results.getString("name"));
        statistics.setNum_comics_author(results.getInt("num_comics_author"));
        statistics.setAuthor(results.getString("author"));
        statistics.setNum_comics_series(results.getInt("num_comics_series"));
        statistics.setSeries(results.getString("series"));
        return statistics;
    }
}
