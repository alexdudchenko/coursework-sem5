package com.proudmur.articlesbackend.service;

import com.proudmur.articlesbackend.dao.SavingsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SavingsService {

    private final SavingsDao savingsDao;

    @Autowired
    public SavingsService(SavingsDao savingsDao) {
        this.savingsDao = savingsDao;
    }

    public int saveArticleForLater(Integer userId, Integer articleId) {
        return savingsDao.createSavedForLater(userId, articleId);
    }

    public Boolean isSaved(Integer userId, Integer articleId) {
        return savingsDao.isSaved(userId, articleId);
    }

    public void deleteBookmark(Integer userId, Integer articleId) {
        savingsDao.deleteBookmark(userId, articleId);
    }
}
