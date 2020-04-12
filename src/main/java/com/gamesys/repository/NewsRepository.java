package com.gamesys.repository;

import com.gamesys.model.News;

import java.util.List;

public interface NewsRepository {

    News save(News news);

    List<News> save(List<News> news);

    List<News> getAll();

    List<News> getLatest();
}
