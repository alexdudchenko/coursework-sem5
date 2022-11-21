package com.proudmur.articlesbackend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleFile {

    private int id;
    private String name;
    private byte[] doc;
}
