package com.techelevator.controller;

import com.techelevator.dao.StatsDao;
import com.techelevator.model.ComicCharacter;
import com.techelevator.model.Statistics;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/stats")
@PreAuthorize("isAuthenticated()")
@CrossOrigin
public class StatisticsController {

    StatsDao statsDao;

    public StatisticsController(StatsDao statsDao) {
        this.statsDao = statsDao;
    }

    @PreAuthorize("hasAnyRole('USER','PREMIUM')")
    @GetMapping("/{collectionId}")
    public List<Statistics> getCollectionStats(@PathVariable int collectionId) {
      List<Statistics> statisticsList = null;

      statisticsList = statsDao.collectionStatistics(collectionId);
        if(statisticsList == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find statistics for collection");
        }
        else return statisticsList;
    }

    @PreAuthorize("hasAnyRole('USER','PREMIUM')")
    @GetMapping("/")
    public List<Statistics> getSiteStats() {
        List<Statistics> statisticsList = null;

        statisticsList = statsDao.siteStatistics();
        if(statisticsList == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find statistics for site");
        }
        else return statisticsList;
    }
}


