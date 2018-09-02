package me.devsnox.spigotbroadcast.configuration;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public final class BroadcastConfiguration {

    private final String prefix;
    private final Integer interval;
    private final TimeUnit timeUnit;
    private final List<String> messages;
    
    BroadcastConfiguration(final String prefix, final int interval, final TimeUnit timeUnit, final List<String> messages)
    {
        this.prefix = prefix;
        this.interval = interval;
        this.timeUnit = timeUnit;
        this.messages = messages;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public Integer getInterval() {
        return this.interval;
    }

    public TimeUnit getTimeUnit() {
        return this.timeUnit;
    }

    public List<String> getMessages() {
        return this.messages;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        BroadcastConfiguration that = (BroadcastConfiguration) o;
        return Objects.equals(this.prefix, that.prefix) &&
                Objects.equals(this.interval, that.interval) &&
                this.timeUnit == that.timeUnit &&
                Objects.equals(this.messages, that.messages);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(this.prefix, this.interval, this.timeUnit, this.messages);
    }
    
    @Override
    public String toString()
    {
        return "BroadcastConfiguration{" +
                "prefix='" + this.prefix + '\'' +
                ", interval=" + this.interval +
                ", timeUnit=" + this.timeUnit +
                ", messages=" + this.messages +
                '}';
    }
}
