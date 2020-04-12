package com.gamesys.service;

import com.gamesys.model.News;
import com.gamesys.repository.NewsRepository;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService{

    @Value("${key.url}")
    public String rssUrl;

    private final NewsRepository repository;

    @Autowired
    public NewsServiceImpl(NewsRepository repository) {
        this.repository = repository;
    }

    @Scheduled(cron="${key.interval}")
    @Override
    public void download(){
        String url = rssUrl;
        URL feedUrl = null;
        try {
            feedUrl = new URL(url);
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL");
        }
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = null;
        try {
            feed = input.build(new XmlReader(feedUrl));
        } catch (FeedException | IOException e) {
            System.out.println("Not possible to build Xml");
        }
        List<String> newsTopics = new ArrayList<>();
        for (SyndEntry entry : (List<SyndEntry>)feed.getEntries()) {
            newsTopics.add(entry.getTitle());
        }
        if (newsTopics.size() > 1)
            save(newsTopics);
        else if (newsTopics.size() == 1)
            save(newsTopics.get(0));
    }

    @Override
    public News save(String topic){
        News news = new News(topic, topic.length());
        return repository.save(news);
    }

    @Override
    public List<News> save(List<String> newsTopics){
        List<News> newsList = newsTopics.stream()
                .map(topic -> new News(topic, topic.length()))
                .collect(Collectors.toList());

       return repository.save(newsList);
    }

    public List<News> getLatest(){
        return repository.getLatest();
    }

    public List<News> getAll(){
        return repository.getAll();
    }


}
