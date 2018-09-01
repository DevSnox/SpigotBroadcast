package me.devsnox.spigotbroadcast.configuration;

import com.google.common.base.Charsets;
import me.devsnox.spigotbroadcast.SpigotBroadcast;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public final class BroadcastConfigurator {

    private final SpigotBroadcast spigotBroadcast;

    private final File config;
    private final YamlConfiguration yamlConfiguration;
    private BroadcastConfiguration broadcastConfiguration;

    public BroadcastConfigurator(final SpigotBroadcast spigotBroadcast) {
        this.spigotBroadcast = spigotBroadcast;

        this.config = new File(this.spigotBroadcast.getDataFolder() + File.separator + "config.yml");

        this.yamlConfiguration = new UTF8YamlConfiguration();

        try {
            this.yamlConfiguration.load(config);
        } catch (final IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }


    public void load() {
        TimeUnit timeUnit = TimeUnit.MINUTES;

        try {
            timeUnit = TimeUnit.valueOf(yamlConfiguration.getString("timeunit"));
        } catch (final IllegalArgumentException exception) {
            this.log(Level.SEVERE,ChatColor.RED + "" + ChatColor.BOLD + "Error while trying to get timeunit!");
            this.log(Level.INFO,ChatColor.RED + "" + ChatColor.BOLD + "Valid timeunits are HOURS, MINUTES, SECONDS!");

            this.yamlConfiguration.set("timeunit", TimeUnit.MINUTES);

            try {
                this.yamlConfiguration.save(config);
            } catch (final IOException e) {
                e.printStackTrace();
            }

            this.log(Level.INFO,"TimeUnit changed to MINUTES, you can change it in the config.yml!");
        }

        this.broadcastConfiguration = new BroadcastConfiguration(this.yamlConfiguration.getString("prefix"), this.yamlConfiguration.getInt("interval"), timeUnit, this.yamlConfiguration.getStringList("messages"));

        if(this.yamlConfiguration.isSet("enabled")) {
            if(this.yamlConfiguration.getBoolean("enabled")) {
                final File messagesFile = new File(this.spigotBroadcast.getDataFolder() + File.separator + "messages.txt");

                if(messagesFile.exists()) {
                    final List<String> lines = new ArrayList<>();

                    try (final BufferedReader br = new BufferedReader((new InputStreamReader(new FileInputStream(messagesFile), Charsets.UTF_8)))) {
                        for(String line; (line = br.readLine()) != null; ) {
                            lines.add(line);
                        }
                    } catch (final FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (final IOException e) {
                        e.printStackTrace();
                    }

                    this.broadcastConfiguration.getMessages().addAll(lines);
                } else {
                    log(Level.SEVERE, "Your configuration file is out of date!");
                    log(Level.INFO, "Please delete it and restart your server or change the value enabled in the configuration file to false!");
                }
            }
        }
    }

    private void log(final Level level, final String message) {
        Bukkit.getLogger().log(level, message);
    }

    public BroadcastConfiguration getBroadcastConfiguration() {
        return broadcastConfiguration;
    }
}
