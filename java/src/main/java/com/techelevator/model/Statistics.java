package com.techelevator.model;

public class Statistics {

    public Statistics() {
    }

    private int num_comics_character;

    private int num_comics_siteWide;
    private int num_collections_siteWide;
    private String name;

    private int num_comics_author;

    private String author;

    private int num_comics_series;

    private String series;


    public int getNum_comics_character() {
        return num_comics_character;
    }

    public void setNum_comics_character(int num_comics_character) {
        this.num_comics_character = num_comics_character;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum_comics_author() {
        return num_comics_author;
    }

    public void setNum_comics_author(int num_comics_author) {
        this.num_comics_author = num_comics_author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNum_comics_series() {
        return num_comics_series;
    }

    public void setNum_comics_series(int num_comics_series) {
        this.num_comics_series = num_comics_series;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public int getNum_comics_siteWide() {
        return num_comics_siteWide;
    }

    public void setNum_comics_siteWide(int num_comics_siteWide) {
        this.num_comics_siteWide = num_comics_siteWide;
    }

    public int getNum_collections_siteWide() {
        return num_collections_siteWide;
    }

    public void setNum_collections_siteWide(int num_collections_siteWide) {
        this.num_collections_siteWide = num_collections_siteWide;
    }
}
