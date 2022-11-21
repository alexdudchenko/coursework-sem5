package com.proudmur.articlesbackend.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Utils {

    private Utils() {}

    public static String filename(String f) throws UnsupportedEncodingException {
        String fileName = URLEncoder.encode(f, "UTF-8");
        return URLDecoder.decode(fileName, "ISO8859_1");
    }
}
