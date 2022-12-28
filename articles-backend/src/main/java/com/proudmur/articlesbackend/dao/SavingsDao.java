package com.proudmur.articlesbackend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SavingsDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SavingsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int createSavedForLater(int userId, int articleId) {
        String sql = "INSERT INTO savings (user_id, article_id) VALUES (?, ?)";
        return jdbcTemplate.update(sql, userId, articleId);
    }

    public Boolean isSaved(int userId, int articleId) {
        String sql = "SELECT EXISTS(SELECT * FROM savings WHERE article_id = ? AND user_id = ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, articleId, userId);
    }

    public void deleteBookmark(Integer userId, Integer articleId) {
        String sql = "DELETE FROM savings WHERE user_id = ? AND article_id = ?";
        jdbcTemplate.update(sql, userId, articleId);
    }

    public List<Integer> getSavedForUser(Integer userId) {
        String sql = "SELECT article_id FROM savings WHERE user_id = ?";
        return jdbcTemplate.queryForList(sql, Integer.class, userId);
    }
}
