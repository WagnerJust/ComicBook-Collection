package com.techelevator.dao;

import org.junit.Before;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcComicDaoTests extends BaseDaoTests{

    private JdbcComicDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcComicDao(jdbcTemplate);
    }



}
