package me.devsnox.spigotbroadcast.task;

import me.devsnox.spigotbroadcast.configuration.BroadcastConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class BroadcastTask extends BukkitRunnable {

    private final Plugin plugin;
    private final BroadcastConfiguration broadcastConfiguration;

    private int count;

    public BroadcastTask(final Plugin plugin, final BroadcastConfiguration broadcastConfiguration) {
        this.plugin = plugin;
        this.count = 0;
        this.broadcastConfiguration = broadcastConfiguration;
    }

    public void start() {
        this.runTaskTimer(this.plugin, 0L, this.broadcastConfiguration.getTimeUnit().toSeconds(this.broadcastConfiguration.getInterval().intValue()) * 20);
    }

    public void run() {
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', this.broadcastConfiguration
                .getPrefix()) + "§r " + ChatColor.translateAlternateColorCodes('&', this.broadcastConfiguration.getMessages().get(count)));

        if (this.count == this.broadcastConfiguration.getMessages().size() - 1) {
            this.count = 0;
        } else {
            this.count += 1;
        }
    }
}