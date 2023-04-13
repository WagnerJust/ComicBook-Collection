package com.techelevator.dao;

import com.techelevator.model.Comic;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;

public class JdbcComicDaoTests extends BaseDaoTests{

    protected final Comic COMIC_1 = new Comic("series1", 1, 1, "UPC1", "URL1", LocalDate.parse("2011-01-01"), "author1", "artist1");
    protected final Comic COMIC_2 = new Comic("series2", 2, 2, "UPC2", "URL2", LocalDate.parse("2011-01-02"), "author2", "artist2");

    private Comic comicTest;
    private Comic comicTest2;

    private JdbcComicDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcComicDao(jdbcTemplate);
        comicTest = new Comic("series8", 8, 8, "UPC8", "URL8", LocalDate.parse("2011-01-08"), "author8", "artist8");
        comicTest2 = new Comic("series9", 9, 9, "UPC9", "URL9", LocalDate.parse("2011-01-09"), "author9", "artist9");
    }

    @Test
    public void getComicsByID_returns_correct_character(){
        assertComicsMatch(COMIC_1, sut.getComic(1));
        assertComicsMatch(COMIC_2, sut.getComic(2));
    }

    @Test
    public void listAllComicsOfCollection_returns_correct_list() {
        Assert.assertEquals(3, sut.listAllComicsOfCollection(1).size());

        assertComicsMatch(COMIC_1, sut.listAllComicsOfCollection(1).get(0));
    }

    @Test
    public void addComic_adds_comic_correctly() {
        int newId = sut.addComic(comicTest).getComicId();
        comicTest.setComicId(newId);

        assertComicsMatch(comicTest, sut.getComic(8));
        newId = sut.addComic(comicTest2).getComicId();
        comicTest2.setComicId(newId);

        assertComicsMatch(comicTest2, sut.getComic(9));
    }

    @Test
    public void addComicToCollection_adds_comic_correctly() {
        sut.addComicToCollection(1, 7);
        Assert.assertEquals(2, sut.listAllComicsOfCollection(7).size());
        assertComicsMatch(COMIC_1, sut.listAllComicsOfCollection(7).get(1));
    }

    @Test
    public void removeComicFromCollection_removes_comic_correctly(){
        sut.removeComicFromCollection(1, 1);
        Assert.assertEquals(2, sut.listAllComicsOfCollection(1).size());
        sut.removeComicFromCollection(7, 7);
        Assert.assertEquals(0, sut.listAllComicsOfCollection(7).size());
    }

    @Test
    public void updateComic_updates_comic_correctly(){

    }

    @Test
    public void deleteComic_deletes_comic_correctly(){
        // dont forget to check both tables
    }

    @Test public void countUniqueComicsOfUser_counts_correctly(){
        Assert.assertEquals(5, sut.countUniqueComicsOfUser(1));
    }




    public void assertComicsMatch(Comic expected, Comic actual){
        Assert.assertEquals(expected.getImageURL(), actual.getImageURL());
        Assert.assertEquals(expected.getIssueNumber(), actual.getIssueNumber());
        Assert.assertEquals(expected.getPublish_date(), actual.getPublish_date());
        Assert.assertEquals(expected.getUpc(), actual.getUpc());
        Assert.assertEquals(expected.getComicId(), actual.getComicId());
        Assert.assertEquals(expected.getSeriesName(), actual.getSeriesName());
        Assert.assertEquals(expected.getArtist(), actual.getArtist());
        Assert.assertEquals(expected.getAuthor(), actual.getAuthor());
    }
}
