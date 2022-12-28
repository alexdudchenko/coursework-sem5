package com.proudmur.articlesbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Article {
    private int id;
    private String title;
    private String description;
    private LocalDate publicationDate;
    private int fileId;
}
