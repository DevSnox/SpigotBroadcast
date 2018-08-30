package me.devsnox.spigotbroadcast;

import me.devsnox.spigotbroadcast.commands.BroadcastCommand;
import me.devsnox.spigotbroadcast.configuration.AdvancedPlugin;
import me.devsnox.spigotbroadcast.configuration.BroadcastConfigurator;
import me.devsnox.spigotbroadcast.task.BroadcastTask;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotBroadcast extends AdvancedPlugin {

    private BroadcastConfigurator broadcastConfigurator;
    private BroadcastTask broadcastTask;

    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[ -------------------------------------------------------------- ]");
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "{ " + ChatColor.GREEN + "INFORMATIONS" + ChatColor.DARK_GRAY + " }");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "-= SpigotBroadcast =-  ");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "-= Author: DevSnox =-");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "-= Version: 1.8 =-");
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "Please report bugs on spigotmc.org per PM");
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "{ " + ChatColor.GREEN + "------------" + ChatColor.DARK_GRAY + " }");
        Bukkit.getConsoleSender().sendMessage(" ");

        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "{ " + ChatColor.GREEN + "LOADING" + ChatColor.DARK_GRAY + " }");

        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "-= register metrics =-");
        new Metrics(this);

        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "-= creating config.yml =-");
        this.createConfigFile();

        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "-= creating messages.txt =-");
        this.createMessagesFile();

        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "-= loading configuration =-");
        broadcastConfigurator = new BroadcastConfigurator(this);
        broadcastConfigurator.load();

        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "-= starting task =-");
        broadcastTask = new BroadcastTask(this, broadcastConfigurator.getBroadcastConfiguration());
        broadcastTask.start();

        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "-= register commands =-");
        getCommand("spigotbroadcast").setExecutor(new BroadcastCommand(this));

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "sucessfully enabled SpigotBroadcast");

        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "{ " + ChatColor.GREEN + "-------" + ChatColor.DARK_GRAY + " }");

        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[ -------------------------------------------------------------- ]");
        Bukkit.getConsoleSender().sendMessage(" ");
    }

    public void reload() {
        this.broadcastConfigurator = null;

        this.broadcastTask.cancel();
        this.broadcastTask = null;

        this.onEnable();
    }

    public void createConfigFile() {
        saveResource("config.yml", false);
    }

    public void createMessagesFile() {
        saveResource("messages.txt", false);
    }
}
