package com.proudmur.articlesbackend.controller;

import com.proudmur.articlesbackend.model.ArticleFile;
import com.proudmur.articlesbackend.service.FileService;
import com.proudmur.articlesbackend.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;

@RestController
public class ArticleFileController {

    private final FileService fileService;

    @Autowired
    public ArticleFileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/admin/files")
    public int upload(@RequestParam MultipartFile file) throws IOException {
        return fileService.saveFile(file);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<Resource> download(@PathVariable int id) throws UnsupportedEncodingException {
        ArticleFile articleFile = fileService.getArticleFileById(id);
        ByteArrayResource resource = new ByteArrayResource(articleFile.getDoc());
        String fileName = Utils.filename(articleFile.getName());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
}
