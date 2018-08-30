package me.devsnox.spigotbroadcast.task;

public enum TickUnit {

    HOURS(72000),
    MINUTES(1200),
    SECONDS(20);

    private int ticks;

    TickUnit(int ticks) {
        this.ticks = ticks;
    }

    public int getTicks() {
        return ticks;
    }
}