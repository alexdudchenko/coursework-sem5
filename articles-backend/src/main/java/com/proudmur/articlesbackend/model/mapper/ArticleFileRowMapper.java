package com.proudmur.articlesbackend.model.mapper;

import com.proudmur.articlesbackend.model.ArticleFile;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.jdbc.core.RowMapper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleFileRowMapper implements RowMapper<ArticleFile> {

    @SneakyThrows
    @Override
    public ArticleFile mapRow(ResultSet rs, int rowNum) throws SQLException {
        ArticleFile articleFile = new ArticleFile();
        articleFile.setId(rs.getInt("id"));
        articleFile.setName(rs.getString("name"));
        InputStream binaryStream = rs.getBinaryStream("doc");
        byte[] doc = IOUtils.toByteArray(binaryStream);
        articleFile.setDoc(doc);

        return articleFile;
    }
}
