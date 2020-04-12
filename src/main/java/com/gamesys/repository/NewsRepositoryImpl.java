package com.gamesys.repository;

import com.gamesys.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class NewsRepositoryImpl implements NewsRepository{

    private final JdbcTemplate jdbcTemplate;

    private final BeanPropertyRowMapper<News> ROW_MAPPER = BeanPropertyRowMapper.newInstance(News.class);

    @Autowired
    public NewsRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public News save(News news){
        jdbcTemplate.update("INSERT INTO news(topic, length) VALUES (?,?)", news.getTopic(), news.getLength());
        return news;
    }

    @Override
    public List<News> save(List<News> news){
        List<Object[]> preparedData = news.stream()
                .map(a -> new Object[]{a.getTopic(), a.getLength()})
                .collect(Collectors.toList());
        jdbcTemplate.batchUpdate("INSERT INTO news(topic, length) VALUES (?,?)", preparedData);
        return news;
    }

    @Override
    public List<News> getAll(){
        return jdbcTemplate.query("SELECT * FROM news", ROW_MAPPER);
    }

    @Override
    public List<News> getLatest() {
        return jdbcTemplate.query("SELECT * FROM news order by id desc limit 10", ROW_MAPPER);
    }


}
