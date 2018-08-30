package me.devsnox.spigotbroadcast.configuration;

import me.devsnox.spigotbroadcast.task.TickUnit;

import java.util.List;

public class BroadcastConfiguration {

    private String prefix;
    private Integer interval;
    private TickUnit tickUnit;
    private List<String> messages;

    public BroadcastConfiguration(String prefix, int interval, TickUnit tickUnit, List<String> messages) {
        this.prefix = prefix;
        this.interval = Integer.valueOf(interval);
        this.tickUnit = tickUnit;
        this.messages = messages;
    }

    public String getPrefix() {
        return prefix;
    }

    public Integer getInterval() {
        return interval;
    }

    public TickUnit getTickUnit() {
        return tickUnit;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
