package com.gamesys.model;

public class News extends AbstractBaseEntity{
    private String topic;
    private Integer length;

    public News() {
    }

    public News(String topic, int length) {
        this.topic = topic;
        this.length = length;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
