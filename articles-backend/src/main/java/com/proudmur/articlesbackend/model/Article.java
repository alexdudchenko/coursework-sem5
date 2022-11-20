package com.proudmur.articlesbackend.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Article {
    private int id;
    private String title;
    private int fileId;
    private LocalDate publicationDate;
    private String description;
}
