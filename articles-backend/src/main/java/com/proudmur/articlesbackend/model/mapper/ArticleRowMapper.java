package com.proudmur.articlesbackend.model.mapper;

import com.proudmur.articlesbackend.model.Article;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ArticleRowMapper implements RowMapper<Article> {

    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        Article article = new Article();
        article.setId(rs.getInt("id"));
        article.setTitle(rs.getString("title"));
        article.setDescription(rs.getString("description"));
        article.setPublicationDate(rs.getObject("publication_date", LocalDate.class));
        article.setFileId(rs.getInt("file_id"));

        return article;
    }
}
