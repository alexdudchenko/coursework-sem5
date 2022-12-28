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

    public List<Article> getAll() {
        return articleDao.findArticles();
    }

    public Article getArticleById(int id) {
        return articleDao.findArticleById(id);
    }

    public int saveArticle(Article article) {
        return articleDao.saveArticle(article);
    }

    public int deleteArticle(int id) {
        return articleDao.deleteArticleById(id);
    }

    public int updateArticle(int id, Article article) {
        article.setId(id);
        if (article.getFileId() != 0) {
            return articleDao.updateArticleWithFileChange(article);
        }
        return articleDao.updateArticleWithoutFileChange(article);
    }

    public List<Article> getArticles(List<Integer> ids) {
        return articleDao.getArticles(ids);
    }
}
