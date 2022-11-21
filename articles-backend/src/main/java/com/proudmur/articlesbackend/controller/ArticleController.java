package com.proudmur.articlesbackend.controller;

import com.proudmur.articlesbackend.model.Article;
import com.proudmur.articlesbackend.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles")
    public List<Article> getRecent(@RequestParam int size) {
        return articleService.getRecent(size);
    }

    @GetMapping("/articles/{id}")
    public Article returnArticle(@PathVariable int id) {
        return articleService.getArticleById(id);
    }

    @PostMapping("/articles")
    public Integer saveArticle(@RequestBody Article article) {
        return articleService.saveArticle(article);
    }

    @DeleteMapping("/articles/{id}")
    public Integer deleteArticle(@PathVariable int id) {
        return articleService.deleteArticle(id);
    }

    @PutMapping("/articles/{id}")
    public Integer updateArticle(@PathVariable int id, @RequestBody Article article) {
        return articleService.updateArticle(id, article);
    }
}
