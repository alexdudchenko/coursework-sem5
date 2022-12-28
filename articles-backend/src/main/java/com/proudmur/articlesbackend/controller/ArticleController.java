package com.proudmur.articlesbackend.controller;

import com.proudmur.articlesbackend.model.Article;
import com.proudmur.articlesbackend.model.User;
import com.proudmur.articlesbackend.service.ArticleService;
import com.proudmur.articlesbackend.service.FileService;
import com.proudmur.articlesbackend.service.RatingService;
import com.proudmur.articlesbackend.service.SavingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
public class ArticleController {

    private final ArticleService articleService;

    private final FileService fileService;

    private final SavingsService savingsService;

    private final RatingService ratingService;

    private static final String ARTICLE = "article";
    private static final String ARTICLES = "articles";
    private static final String REDIRECT_TO_ADMIN_ARTICLES = "redirect:/admin/articles";

    @Autowired
    public ArticleController(ArticleService articleService, FileService fileService, SavingsService savingsService, RatingService ratingService) {
        this.articleService = articleService;
        this.fileService = fileService;
        this.savingsService = savingsService;
        this.ratingService = ratingService;
    }

    @GetMapping("/articles")
    public String articles(@AuthenticationPrincipal User user,
                           @RequestParam(required = false, defaultValue = "5") int size,
                           Model model) {
        List<Article> articles = articleService.getRecent(size);
        List<Article> recommended = articleService.getArticles(ratingService.getRecommendation(user.getId(), size));
        System.out.println(recommended);
        model.addAttribute(ARTICLES, articles);
        model.addAttribute("recommended", recommended);
        return ARTICLES;
    }

    @GetMapping("/articles/{id}")
    public String returnArticle(@AuthenticationPrincipal User user, @PathVariable int id, Model model) {
        Article article = articleService.getArticleById(id);
        Boolean saved = savingsService.isSaved(user.getId(), id);
        model.addAttribute(ARTICLE, article);
        model.addAttribute("saved", saved);
        return ARTICLE;
    }

    @GetMapping("/admin/articles")
    public String adminPage(HttpServletRequest request, Model model) {
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

    @GetMapping("/admin/articles/create")
    public String redirectToCreateForm() {
        return "admin-create-article";
    }

    @PostMapping(value = "/admin/articles", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String saveArticle(@RequestParam String title, @RequestParam String description, @RequestParam MultipartFile file, Model model) throws IOException {
        int id = fileService.saveFile(file);
        Article article = new Article();
        article.setTitle(title);
        article.setDescription(description);
        article.setFileId(id);
        articleService.saveArticle(article);

        List<Article> articles = articleService.getAll();
        model.addAttribute(ARTICLES, articles);
        return REDIRECT_TO_ADMIN_ARTICLES;
    }

    @PostMapping("/admin/articles/{id}/delete")
    public String deleteArticle(@PathVariable int id) {
        articleService.deleteArticle(id);
        return REDIRECT_TO_ADMIN_ARTICLES;
    }

    @PostMapping(value = "/admin/articles/{id}/save-edited", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String updateArticle(@PathVariable int id, Article article) {
        articleService.updateArticle(id, article);
        return REDIRECT_TO_ADMIN_ARTICLES;
    }

    @GetMapping("/articles/find")
    public String findByTitle(@AuthenticationPrincipal User user, String pattern, Model model) {

        model.addAttribute(ARTICLES, articleService.findArticlesByTitle(pattern));

        return user.getRole().equals("ROLE_ADMIN") ? "admin-page-articles" : ARTICLES;
    }

}
