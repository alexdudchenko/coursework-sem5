package com.proudmur.articlesbackend.controller;

import com.proudmur.articlesbackend.model.Article;
import com.proudmur.articlesbackend.model.User;
import com.proudmur.articlesbackend.service.ArticleService;
import com.proudmur.articlesbackend.service.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    SavingsService savingsService;

    @Autowired
    ArticleService articleService;

    @GetMapping("personal-info")
    public String personalInfo(@AuthenticationPrincipal User user,
                               Model model) {
        model.addAttribute("user", user);
        List<Article> articles = articleService.getArticles(savingsService.getSaved(user.getId()));
        model.addAttribute("savedArticles", articles);
        return "personal-info";
    }
}
