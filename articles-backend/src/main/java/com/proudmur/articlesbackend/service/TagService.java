package com.proudmur.articlesbackend.service;

import com.proudmur.articlesbackend.dao.TagDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    private final TagDao tagDao;

    @Autowired
    public TagService(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    public int saveTag(String name) {
        return tagDao.createTag(name);
    }

    public int deleteTag(int id) {
        return tagDao.removeTag(id);
    }
}
