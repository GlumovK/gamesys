package com.gamesys.service;

import com.gamesys.model.News;

import java.util.List;

public interface NewsService {

    void download();

    News save(String topic);

    List<News> save(List<String> newsTopics);

    List<News> getLatest();

    List<News> getAll();
}
