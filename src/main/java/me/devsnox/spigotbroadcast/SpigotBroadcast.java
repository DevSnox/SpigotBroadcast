package me.devsnox.spigotbroadcast;

import me.devsnox.spigotbroadcast.commands.BroadcastCommand;
import me.devsnox.spigotbroadcast.configuration.AdvancedPlugin;
import me.devsnox.spigotbroadcast.configuration.BroadcastConfigurator;
import me.devsnox.spigotbroadcast.task.BroadcastTask;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SpigotBroadcast extends AdvancedPlugin {

    private BroadcastConfigurator broadcastConfigurator;
    private BroadcastTask broadcastTask;

    @Override
    public void onEnable() {
        this.load();
    }

    private void load() {
        this.sendHeader();

        this.createConfigFile();
        this.createMessagesFile();
        this.loadConfiguration();
        this.startTask();
        this.registerCommands();
        this.initializeMetrics();

        this.sendFooter();
    }

    public void reload() {
        this.broadcastConfigurator = null;

        this.broadcastTask.cancel();
        this.broadcastTask = null;

        this.load();
    }

    private void registerCommands() {
        this.getLogger().log(Level.INFO, ChatColor.YELLOW + "-= register commands =-");
        this.getCommand("spigotbroadcast").setExecutor(new BroadcastCommand(this));
    }

    private void startTask() {
        this.getLogger().log(Level.INFO, ChatColor.YELLOW + "-= starting task =-");
        broadcastTask = new BroadcastTask(this, broadcastConfigurator.getBroadcastConfiguration());
        broadcastTask.start();
    }

    private void initializeMetrics() {
        this.getLogger().log(Level.INFO, ChatColor.YELLOW + "-= register metrics =-");
        new Metrics(this);
    }

    public void createConfigFile() {
        this.getLogger().log(Level.INFO, ChatColor.YELLOW + "-= creating config.yml =-");
        this.saveResource("config.yml", false);
    }

    public void createMessagesFile() {
        this.getLogger().log(Level.INFO, ChatColor.YELLOW + "-= creating messages.txt =-");
        this.saveResource("messages.txt", false);
    }

    private void loadConfiguration() {
        this.getLogger().log(Level.INFO, ChatColor.YELLOW + "-= loading configuration =-");
        broadcastConfigurator = new BroadcastConfigurator(this);
        broadcastConfigurator.load();
    }

    private void sendHeader() {
        this.getLogger().log(Level.INFO, " ");
        this.getLogger().log(Level.INFO, ChatColor.AQUA + "[ -------------------------------------------------------------- ]");
        this.getLogger().log(Level.INFO, " ");
        this.getLogger().log(Level.INFO, ChatColor.DARK_GRAY + "{ " + ChatColor.GREEN + "INFORMATIONS" + ChatColor.DARK_GRAY + " }");
        this.getLogger().log(Level.INFO, ChatColor.YELLOW + "-= SpigotBroadcast =-  ");
        this.getLogger().log(Level.INFO, ChatColor.YELLOW + "-= Author: DevSnox =-");
        this.getLogger().log(Level.INFO, ChatColor.YELLOW + "-= Version: 1.9-RELEASE =-");
        this.getLogger().log(Level.INFO, ChatColor.DARK_RED + "Please report bugs on spigotmc.org per PM");
        this.getLogger().log(Level.INFO, ChatColor.DARK_GRAY + "{ " + ChatColor.GREEN + "------------" + ChatColor.DARK_GRAY + " }");
        this.getLogger().log(Level.INFO, " ");
        this.getLogger().log(Level.INFO, ChatColor.DARK_GRAY + "{ " + ChatColor.GREEN + "LOADING" + ChatColor.DARK_GRAY + " }");
    }


    private void sendFooter() {
        this.getLogger().log(Level.INFO, ChatColor.GREEN + "sucessfully enabled SpigotBroadcast");

        this.getLogger().log(Level.INFO, ChatColor.DARK_GRAY + "{ " + ChatColor.GREEN + "-------" + ChatColor.DARK_GRAY + " }");

        this.getLogger().log(Level.INFO, " ");
        this.getLogger().log(Level.INFO, ChatColor.AQUA + "[ -------------------------------------------------------------- ]");
        this.getLogger().log(Level.INFO, " ");
    }
}
