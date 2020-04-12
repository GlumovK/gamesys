package com.gamesys.controller;

import com.gamesys.model.News;
import com.gamesys.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class NewsController {

    public final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @PostMapping("latestNews")
    public @ResponseBody
    List<News> getLatestNews() {
        return newsService.getLatest();
    }
}
