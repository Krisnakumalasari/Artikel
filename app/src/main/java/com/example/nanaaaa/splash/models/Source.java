package com.example.nanaaaa.splash.models;

import java.util.List;

public class Source {
    //            "id": "1492919364",
    //            "title": "Korut Ancam Serang Australia dengan Kekuatan Nuklir",
    //            "content": "Korea Utara (Korut) mengancam Australia dengan serangan nuklir jika terus mengikuti kebijakan Amerika Serikat (AS)",
    //            "url": "http://dev.republika.co.id/news",
    //            "category": "Politik",
    //            },
    //            "sortBysAvailable": ["top"]

    public String id;
    public String title;
    public String content;
    public String url;
    public String category;
    public String language;
    public String country;
    public List<Source> sources;
    //public List<String> urlsToLogos;
    public List<String> sortBysAvailable;
    public int color;

}
