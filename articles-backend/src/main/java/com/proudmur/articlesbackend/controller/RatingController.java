package com.proudmur.articlesbackend.controller;

import com.proudmur.articlesbackend.model.User;
import com.proudmur.articlesbackend.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/rate")
    public String rateArticle(HttpServletRequest request, @AuthenticationPrincipal User user,
                              @RequestParam Integer articleId, @RequestParam Integer rating) {
        String referer = request.getHeader("Referer");
        ratingService.rateArticle(user.getId(), articleId, rating);
        return "redirect:" + referer;
    }
}
