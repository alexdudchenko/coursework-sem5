package com.proudmur.articlesbackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(value="/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping(value = "/")
    public String root() {
        return "index";
    }
}
