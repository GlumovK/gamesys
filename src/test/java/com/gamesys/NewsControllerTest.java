package com.gamesys;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.gamesys.controller.NewsController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class NewsControllerTest {

    @Autowired
    public NewsController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contexLoads() {
         assertThat(controller).isNotNull();
    }

    @Test
    public void verifyController() throws Exception {
        this.mockMvc.perform(post("/latestNews")).andDo(print()).andExpect(status().isOk());
    }
}
