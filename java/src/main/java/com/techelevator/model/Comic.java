package com.techelevator.model;

import java.time.LocalDate;

public class Comic {
    private String seriesName;
    private int comicId;
    private int issueNumber;
    private String upc;
    private String imageURL;
    private LocalDate publish_date;

    public Comic() {
    }

    public Comic(String seriesName, int comicId, int issueNumber, String upc, String imageURL, LocalDate publish_date) {
        this.seriesName = seriesName;
        this.comicId = comicId;
        this.issueNumber = issueNumber;
        this.upc = upc;
        this.imageURL = imageURL;
        this.publish_date = publish_date;
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
