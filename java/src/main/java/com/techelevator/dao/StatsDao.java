package com.techelevator.dao;

import com.techelevator.model.Statistics;

import java.util.List;

public interface StatsDao {

    List<Statistics> collectionStatistics(int collectionId);

    List<Statistics> siteStatistics();
}
