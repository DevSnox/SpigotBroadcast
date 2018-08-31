package me.devsnox.spigotbroadcast.configuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

public final class BroadcastConfiguration {

    private final String prefix;
    private final Integer interval;
    private final TimeUnit timeUnit;
    private final List<String> messages;

    public BroadcastConfiguration(final String prefix, final int interval, final TimeUnit timeUnit, final List<String> messages) {
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
}
