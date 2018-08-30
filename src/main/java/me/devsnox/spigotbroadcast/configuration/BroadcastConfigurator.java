package me.devsnox.spigotbroadcast.configuration;

import com.google.common.base.Charsets;
import me.devsnox.spigotbroadcast.SpigotBroadcast;
import me.devsnox.spigotbroadcast.task.TickUnit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BroadcastConfigurator {

    private SpigotBroadcast spigotBroadcast;

    private File config;
    private UTF8YamlConfiguration yamlConfiguration;
    private BroadcastConfiguration broadcastConfiguration;

    public BroadcastConfigurator(SpigotBroadcast spigotBroadcast) {
        this.spigotBroadcast = spigotBroadcast;

        config = new File("plugins" + File.separator + spigotBroadcast.getName() + File.separator + "config.yml");
        try {
            yamlConfiguration = new UTF8YamlConfiguration();
            yamlConfiguration.load(config);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }


    public void load() {
        if(!yamlConfiguration.isSet("config-version")) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + " - IMPORTANT - ");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Found old not compatible configuration File!");

            this.config.renameTo(new File("plugins" + File.separator + spigotBroadcast.getName() + File.separator + "old-config.yml.txt"));

            this.config.delete();
            this.spigotBroadcast.createConfigFile();

            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Sucessfully changed config.yml to version 2!");
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Old config.yml has been saved to old-config.yml.txt");
            Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + " - IMPORTANT - ");
        }

        TickUnit tickUnit = TickUnit.MINUTES;

        try {
            tickUnit = TickUnit.valueOf(yamlConfiguration.getString("timeunit"));
        } catch (IllegalArgumentException exception) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Error while trying to get timeunit!");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Valid timeunits are HOURS, MINUTES, SECONDS!");

            yamlConfiguration.set("timeunit", TickUnit.MINUTES);
            try {
                yamlConfiguration.save(config);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "TimeUnit changed to MINUTES, you can change it in the config.yml!");
        }

        broadcastConfiguration = new BroadcastConfiguration(yamlConfiguration.getString("prefix"), yamlConfiguration.getInt("interval"), tickUnit, yamlConfiguration.getStringList("messages"));

        if(yamlConfiguration.getBoolean("enabled") == true) {
            List<String> lines = new ArrayList<>();

            try (BufferedReader br = new BufferedReader((new InputStreamReader(new FileInputStream(new File("plugins" + File.separator + spigotBroadcast.getName() + File.separator + "messages.txt")), Charsets.UTF_8)))) {
                for(String line; (line = br.readLine()) != null; ) {
                    lines.add(line);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            broadcastConfiguration.setMessages(lines);
        }
    }


    public BroadcastConfiguration getBroadcastConfiguration() {
        return broadcastConfiguration;
    }
}
