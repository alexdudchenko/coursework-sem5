package com.proudmur.articlesbackend.controller;

import com.proudmur.articlesbackend.model.User;
import com.proudmur.articlesbackend.service.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SavingsController {

    private final SavingsService savingsService;

    @Autowired
    public SavingsController(SavingsService savingsService) {
        this.savingsService = savingsService;
    }

    @PostMapping("/articles/save-for-later")
    public String saveForLater(@AuthenticationPrincipal User user, @RequestParam Integer id, HttpServletRequest request) {
        savingsService.saveArticleForLater(user.getId(), id);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @PostMapping("/articles/unsave-for-later")
    public String deleteBookmark(@AuthenticationPrincipal User user, @RequestParam Integer id, HttpServletRequest request) {
        savingsService.deleteBookmark(user.getId(), id);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
