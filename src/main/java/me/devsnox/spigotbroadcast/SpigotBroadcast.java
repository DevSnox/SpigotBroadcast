package me.devsnox.spigotbroadcast;

import me.devsnox.spigotbroadcast.commands.BroadcastCommand;
import me.devsnox.spigotbroadcast.configuration.AdvancedPlugin;
import me.devsnox.spigotbroadcast.configuration.BroadcastConfigurator;
import me.devsnox.spigotbroadcast.task.BroadcastTask;
import org.bstats.bukkit.Metrics;
import org.bukkit.ChatColor;

import java.util.logging.Level;

public final class SpigotBroadcast extends AdvancedPlugin {

    private BroadcastConfigurator broadcastConfigurator;
    private BroadcastTask broadcastTask;

    @Override
    public void onEnable() {
        this.load();
    }

    private void load() {
        this.sendHeader();

        this.createConfigFile();
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
        this.log(ChatColor.YELLOW + "-= register commands =-");
        this.getCommand("spigotbroadcast").setExecutor(new BroadcastCommand(this));
    }

    private void startTask() {
        this.log(ChatColor.YELLOW + "-= starting task =-");
        this.broadcastTask = new BroadcastTask(this, broadcastConfigurator.getBroadcastConfiguration());
        this.broadcastTask.start();
    }

    private void initializeMetrics() {
        this.log(ChatColor.YELLOW + "-= register metrics =-");
        new Metrics(this);
    }

    public void createConfigFile() {
        this.log(ChatColor.YELLOW + "-= creating config.yml =-");
        this.saveResource("config.yml", false);
    }

    private void loadConfiguration() {
        this.log(ChatColor.YELLOW + "-= loading configuration =-");
        this.broadcastConfigurator = new BroadcastConfigurator(this);
        this.broadcastConfigurator.load();
    }

    private void sendHeader() {
        this.log(" ");
        this.log(ChatColor.AQUA + "[ -------------------------------------------------------------- ]");
        this.log(" ");
        this.log(ChatColor.DARK_GRAY + "{ " + ChatColor.GREEN + "INFORMATIONS" + ChatColor.DARK_GRAY + " }");
        this.log(ChatColor.YELLOW + "-= " + this.getName() + " =-  ");
        this.log(ChatColor.YELLOW + "-= Author: " + this.getDescription().getAuthors().get(0) + " =-");
        this.log(ChatColor.YELLOW + "-= Version: " + this.getDescription().getVersion() + " =-");
        this.log(ChatColor.DARK_RED + "Please report bugs on spigotmc.org per PM");
        this.log(ChatColor.DARK_GRAY + "{ " + ChatColor.GREEN + "------------" + ChatColor.DARK_GRAY + " }");
        this.log(" ");
        this.log(ChatColor.DARK_GRAY + "{ " + ChatColor.GREEN + "LOADING" + ChatColor.DARK_GRAY + " }");
    }


    private void sendFooter() {
        this.log(ChatColor.GREEN + "successfully enabled " + this.getName());

        this.log(ChatColor.DARK_GRAY + "{ " + ChatColor.GREEN + "-------" + ChatColor.DARK_GRAY + " }");

        this.log(" ");
        this.log(ChatColor.AQUA + "[ -------------------------------------------------------------- ]");
        this.log(" ");
    }

    private void log(final String message) {
        this.getLogger().log(Level.INFO, message);
    }
}
