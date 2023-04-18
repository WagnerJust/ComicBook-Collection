package com.techelevator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techelevator.model.Comic;
import com.techelevator.services.RestMarvelService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@PreAuthorize("isAuthenticated")
@CrossOrigin
public class MarvelController {
    RestMarvelService restMarvelService;

    public MarvelController(RestMarvelService marvelService){
        this.restMarvelService = marvelService;
    }


    @PreAuthorize("hasAnyRole('USER','PREMIUM')")
    @GetMapping("/marvel/{series}/{issueNo}")
    public List<Comic> searchMarvelBySeriesAndIssueNo(@PathVariable String series, @PathVariable String issueNo) throws JsonProcessingException {
        return restMarvelService.searchComicsBySeriesAndIssueNo(series, issueNo);
    }

    @PreAuthorize("hasAnyRole('USER','PREMIUM')")
    @GetMapping("/marvel/upc/{upc}")
    public List<Comic> getMarvelComicByUpc(@PathVariable String upc) throws JsonProcessingException {
        return restMarvelService.getComicByUpc(upc);
    }

    @PreAuthorize("hasAnyRole('USER','PREMIUM')")
    @GetMapping("/marvel/latest")
    public List<Comic> getLatestReleases() throws JsonProcessingException {
        return restMarvelService.getLatestReleases();
    }
}
