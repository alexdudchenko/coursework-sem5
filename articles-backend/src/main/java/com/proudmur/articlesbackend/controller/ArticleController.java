package com.proudmur.articlesbackend.controller;

import com.proudmur.articlesbackend.model.Article;
import com.proudmur.articlesbackend.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@Slf4j
@Controller
public class ArticleController {

    private final ArticleService articleService;
    private static final String ARTICLE = "article";
    private static final String ARTICLES = "articles";
    private static final String REDIRECT_TO_ADMIN_ARTICLES = "redirect:/admin/articles";

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles")
    public String articles(@RequestParam(required = false, defaultValue = "5") int size, Model model) {
        List<Article> articles = articleService.getRecent(size);
        model.addAttribute(ARTICLES, articles);
        return ARTICLES;
    }

    @GetMapping("/articles/{id}")
    public String returnArticle(@PathVariable int id, Model model) {
        Article article = articleService.getArticleById(id);
        model.addAttribute(ARTICLE, article);
        return ARTICLE;
    }

    @GetMapping("/admin/articles")
    public String adminPage(Model model) {
        List<Article> articles = articleService.getAll();
        model.addAttribute(ARTICLES, articles);
        return "admin-page-articles";
    }

    @GetMapping("/admin/articles/{id}/edit")
    public String editPage(@PathVariable int id, Model model) {
        Article article = articleService.getArticleById(id);
        model.addAttribute(ARTICLE, article);
        return "admin-edit-article";
    }

    @PostMapping("/admin/articles")
    public String saveArticle(@RequestBody Article article, Model model) {
        List<Article> articles = articleService.getAll();
        model.addAttribute(ARTICLES, articles);
        articleService.saveArticle(article);
        return REDIRECT_TO_ADMIN_ARTICLES;
    }

    @PostMapping("/admin/articles/{id}/delete")
    public String deleteArticle(@PathVariable int id) {
        articleService.deleteArticle(id);
        return REDIRECT_TO_ADMIN_ARTICLES;
    }

    @PostMapping(value = "/admin/articles/{id}/save-edited", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String updateArticle(@PathVariable int id, Article article) {
        log.debug(article.toString());
        articleService.updateArticle(id, article);
        return REDIRECT_TO_ADMIN_ARTICLES;
    }

    @GetMapping(value="/login")
    public String getLoginPage(Model model){
        return "login";
    }

    @GetMapping(value = "/")
    public String root() {
        return "index";
    }
}
