package com.proudmur.articlesbackend.dao;

import com.proudmur.articlesbackend.model.ArticleFile;
import com.proudmur.articlesbackend.model.mapper.ArticleFileRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleFileDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ArticleFileDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveArticleFile(ArticleFile file) {
        String sql = "INSERT INTO files (name, doc) VALUES (?, ?)";
        return jdbcTemplate.update(sql, file.getName(), file.getDoc());
    }

    public ArticleFile getArticleFileById(int id) {
        String sql = "SELECT * FROM files WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new ArticleFileRowMapper(), id);
    }
}
