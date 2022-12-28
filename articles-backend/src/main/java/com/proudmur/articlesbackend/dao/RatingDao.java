package com.proudmur.articlesbackend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RatingDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int rateArticle(Integer userId, Integer articleId, Integer rating) {
        String sql = "INSERT INTO ratings (user_id, article_id, rating) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, userId, articleId, rating);
    }

    public Boolean rated(Integer userId, Integer articleId) {
        String sql = "SELECT EXISTS(SELECT * FROM ratings WHERE user_id = ? AND article_id = ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, userId, articleId);
    }

    public int clearRating(Integer userId, Integer articleId) {
        String sql = "DELETE FROM ratings WHERE user_id = ? AND article_id = ?";
        return jdbcTemplate.update(sql, userId, articleId);
    }
}
