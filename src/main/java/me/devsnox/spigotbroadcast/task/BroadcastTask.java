package me.devsnox.spigotbroadcast.task;

import me.devsnox.spigotbroadcast.configuration.BroadcastConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Yasin Dalal (DevSnox)
 * Created by Yasin Dalal (DevSnox) on 24.12.2017 00:00.
 */
public final class BroadcastTask extends BukkitRunnable {

    private final Plugin plugin;
    private final BroadcastConfiguration broadcastConfiguration;

    private int count;

    public BroadcastTask(final Plugin plugin, final BroadcastConfiguration broadcastConfiguration) {
        this.plugin = plugin;
        this.broadcastConfiguration = broadcastConfiguration;
        this.count = 0;
    }

    public void start() {
        this.runTaskTimerAsynchronously(this.plugin, 0L, this.broadcastConfiguration.getTimeUnit().toSeconds(this.broadcastConfiguration.getInterval()) * 20);
    }

    public void run() {
        String message = ChatColor.translateAlternateColorCodes(
                '&',
                this.broadcastConfiguration.getPrefix() + ChatColor.RESET + this.broadcastConfiguration.getMessages().get(this.count)
        );

        Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(message));

        if (this.count == this.broadcastConfiguration.getMessages().size() - 1) {
            this.count = 0;
        } else {
            this.count++;
        }
    }
}