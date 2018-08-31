package me.devsnox.spigotbroadcast.task;

import me.devsnox.spigotbroadcast.configuration.BroadcastConfiguration;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import java.util.concurrent.TimeUnit;

public class BroadcastTask extends org.bukkit.scheduler.BukkitRunnable {

    private Plugin plugin;

    private int count;

    private BroadcastConfiguration broadcastConfiguration;

    public BroadcastTask(Plugin plugin, BroadcastConfiguration broadcastConfiguration) {
        this.plugin = plugin;
        count = 0;
        this.broadcastConfiguration = broadcastConfiguration;
    }

    public void start() {
        runTaskTimer(plugin, 0L, broadcastConfiguration.getTimeUnit().toSeconds(broadcastConfiguration.getInterval().intValue()) * 20);
    }

    public void run() {
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', broadcastConfiguration
                .getPrefix()) + "§r " + ChatColor.translateAlternateColorCodes('&', broadcastConfiguration.getMessages().get(count)));

        if (count == broadcastConfiguration.getMessages().size() - 1) {
            count = 0;
        } else {
            count += 1;
        }
    }
}