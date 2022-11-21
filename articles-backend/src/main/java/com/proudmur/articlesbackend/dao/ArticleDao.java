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
        String sql = "SELECT * FROM articles WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new ArticleRowMapper(), id);
    }

    public int saveArticle(Article article) {
        String sql = "INSERT INTO articles (title, description, publication_date, file_id) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, article.getTitle(), article.getDescription(),
                article.getPublicationDate(), article.getFileId());
    }

    public int deleteArticleById(int id) {
        String sql = "DELETE FROM articles WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public int updateArticleWithoutFileChange(Article article) {
        String sql = "UPDATE articles SET title = ?, description = ? WHERE id = ?";
        return jdbcTemplate.update(sql, article.getTitle(), article.getDescription(), article.getId());
    }

    public int updateArticleWithFileChange(Article article) {
        String sql = "UPDATE articles SET title = ?, description = ?, file_id = ? WHERE id = ?";
        return jdbcTemplate.update(sql, article.getTitle(), article.getDescription(), article.getFileId());
    }
}
