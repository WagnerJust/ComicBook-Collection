package com.techelevator.dao;


import com.techelevator.model.ComicCollection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcCollectionDaoTests extends BaseDaoTests{

    protected final ComicCollection COLLECTION_1 = new ComicCollection(1, 1, "collection1", true);
    protected final ComicCollection COLLECTION_2 = new ComicCollection(2, 2, "collection2", false);

    private ComicCollection collectionTest;
    private ComicCollection collectionTest2;

    private JdbcCollectionDao sut;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcCollectionDao(jdbcTemplate);
        collectionTest = new ComicCollection(100, 100, "Collection100", true);
        collectionTest2 = new ComicCollection(101, 101, "Collection101", false);
    }

    @Test
    public void listAllCollectionsReturnsAllCollections(){
        List<ComicCollection> collections = sut.listAllCollections();

        Assert.assertEquals(4, collections.size());

    }

    @Test
    public void listAllCollectionsByUserIdReturnsCorrectCollections(){
        List<ComicCollection> collections = sut.listCollectionsByUser(1);

        Assert.assertEquals(2, collections.size());
        assertCollectionsMatch(COLLECTION_1, collections.get(0));
    }



    public void assertCollectionsMatch(ComicCollection expected, ComicCollection actual){
        Assert.assertEquals(expected.getCollectionName(), actual.getCollectionName());
        Assert.assertEquals(expected.getCollectionId(), actual.getCollectionId());
        Assert.assertEquals(expected.getUserId(), actual.getUserId());
        Assert.assertEquals(expected.getPublic(), actual.getPublic());
    }


}
