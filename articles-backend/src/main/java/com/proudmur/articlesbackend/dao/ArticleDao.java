package com.proudmur.articlesbackend.dao;

import com.proudmur.articlesbackend.model.Article;
import com.proudmur.articlesbackend.model.mapper.ArticleRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ArticleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Article> findRecentArticles(int size) {
        String sql = "SELECT * FROM articles ORDER BY publication_date DESC LIMIT ?";
        return jdbcTemplate.query(sql, new ArticleRowMapper(), size);
    }

    public Article findArticleById(int id) {
        //TODO
        return null;
    }

}
