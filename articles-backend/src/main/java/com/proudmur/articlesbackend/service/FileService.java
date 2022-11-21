package com.proudmur.articlesbackend.service;

import com.proudmur.articlesbackend.dao.ArticleFileDao;
import com.proudmur.articlesbackend.model.ArticleFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {

    private ArticleFileDao fileDao;

    @Autowired
    public FileService(ArticleFileDao fileDao) {
        this.fileDao = fileDao;
    }

    public int saveFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        ArticleFile articleFile = new ArticleFile();
        articleFile.setName(fileName);
        articleFile.setDoc(file.getBytes());

        return fileDao.saveArticleFile(articleFile);
    }

    public ArticleFile getArticleFileById(int id) {
        return fileDao.getArticleFileById(id);
    }
}
