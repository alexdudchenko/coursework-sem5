package com.proudmur.articlesbackend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TagDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TagDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int createTag(String name) {
        String sql = "INSERT INTO tags (title) VALUES (?)";
        return jdbcTemplate.update(sql, name);
    }

    public int removeTag(int id) {
        String sql = "DELETE FROM tags WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}


