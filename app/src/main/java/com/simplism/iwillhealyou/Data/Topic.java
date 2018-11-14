package com.simplism.iwillhealyou.Data;

public class Topic {
    public String topic;
    public String cont;

    public Topic() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Topic(String topic, String cont) {
        this.topic = topic;
        this.cont = cont;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }
}
