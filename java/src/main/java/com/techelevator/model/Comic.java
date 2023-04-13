package com.techelevator.model;

import java.time.LocalDate;

public class Comic {
    private String seriesName;
    private int comicId;
    private int issueNumber;
    private String upc;
    private String imageURL;
    private LocalDate publish_date;
    private String author;
    private String artist;

    public Comic() {
    }

    public Comic(String seriesName, int comicId, int issueNumber, String upc, String imageURL, LocalDate publish_date, String author, String artist) {
        this.seriesName = seriesName;
        this.comicId = comicId;
        this.issueNumber = issueNumber;
        this.upc = upc;
        this.imageURL = imageURL;
        this.publish_date = publish_date;
        this.author = author;
        this.artist = artist;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public LocalDate getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(LocalDate publish_date) {
        this.publish_date = publish_date;
    }

    public int getComicId() {
        return comicId;
    }

    public void setComicId(int comicId) {
        this.comicId = comicId;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
