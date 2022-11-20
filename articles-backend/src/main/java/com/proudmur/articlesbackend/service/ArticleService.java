package com.proudmur.articlesbackend.service;

import com.proudmur.articlesbackend.dao.ArticleDao;
import com.proudmur.articlesbackend.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleDao articleDao;

    @Autowired
    public ArticleService(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public List<Article> getRecent(int size) {
        return articleDao.findRecentArticles(size);
    }

    public Article getArticleById(int id) {
        return articleDao.findArticleById(id);
    }
}
