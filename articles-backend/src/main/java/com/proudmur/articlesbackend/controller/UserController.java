package com.proudmur.articlesbackend.controller;

import com.proudmur.articlesbackend.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("personal-info")
    public String personalInfo(@AuthenticationPrincipal User user,
                               Model model) {
        model.addAttribute("user", user);
        return "personal-info";
    }
}
