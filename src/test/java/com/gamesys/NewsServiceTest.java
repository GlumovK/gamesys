package com.gamesys;

import com.gamesys.model.News;
import com.gamesys.service.NewsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class NewsServiceTest {

    private static final int INITIAL_SIZE = 2;

    @Autowired
    NewsService service;

    @Test
    void testGet() {
        assertEquals(INITIAL_SIZE, service.getAll().size());
    }

    @Test
    public void save() {
        String topic = "Test topic";
        News created = service.save(topic);
        assertEquals("Test topic", created.getTopic());
    }

    @Test
    public void getLatest() {
        List<String> topics = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            topics.add("test" + i);
        }
        service.save(topics);
        assertEquals(10, service.getLatest().size());
    }


}
