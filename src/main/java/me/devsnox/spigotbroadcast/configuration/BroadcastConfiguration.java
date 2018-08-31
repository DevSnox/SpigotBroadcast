package me.devsnox.spigotbroadcast.configuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BroadcastConfiguration {

    private String prefix;
    private Integer interval;
    private TimeUnit timeUnit;
    private List<String> messages;

    public BroadcastConfiguration(String prefix, int interval, TimeUnit timeUnit, List<String> messages) {
        this.prefix = prefix;
        this.interval = Integer.valueOf(interval);
        this.timeUnit = timeUnit;
        this.messages = messages;
    }

    public String getPrefix() {
        return prefix;
    }

    public Integer getInterval() {
        return interval;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
