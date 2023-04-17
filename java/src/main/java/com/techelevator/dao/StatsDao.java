package com.techelevator.dao;

import com.techelevator.model.Statistics;

import java.util.List;

public interface StatsDao {

    List<Statistics> collectionCharacterStats(int collectionId);
    List<Statistics> collectionAuthorStats(int collectionId);
    List<Statistics> collectionSeriesStats(int collectionId);

    List<Statistics> siteStatistics();
}
