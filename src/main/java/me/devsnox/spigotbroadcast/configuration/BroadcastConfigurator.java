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
        if(!yamlConfiguration.isSet("config-version")) {
            this.log(Level.WARNING,"Found old not compatible configuration File!");

            this.config.renameTo(new File("plugins" + File.separator + this.spigotBroadcast.getName() + File.separator + "old-config.yml.txt"));

            this.config.delete();
            this.spigotBroadcast.createConfigFile();

            this.log(Level.INFO,"Sucessfully changed config.yml to version 2!");
            this.log(Level.INFO,"Old config.yml has been saved to old-config.yml.txt");
        }

        TimeUnit timeUnit = TimeUnit.MINUTES;

        try {
            timeUnit = timeUnit.valueOf(yamlConfiguration.getString("timeunit"));
        } catch (final IllegalArgumentException exception) {
            this.log(Level.SEVERE,ChatColor.RED + "" + ChatColor.BOLD + "Error while trying to get timeunit!");
            this.log(Level.INFO,ChatColor.RED + "" + ChatColor.BOLD + "Valid timeunits are HOURS, MINUTES, SECONDS!");

            this.yamlConfiguration.set("timeunit", timeUnit.MINUTES);

            try {
                this.yamlConfiguration.save(config);
            } catch (final IOException e) {
                e.printStackTrace();
            }

            this.log(Level.INFO,"TimeUnit changed to MINUTES, you can change it in the config.yml!");
        }

        this.broadcastConfiguration = new BroadcastConfiguration(this.yamlConfiguration.getString("prefix"), this.yamlConfiguration.getInt("interval"), timeUnit, this.yamlConfiguration.getStringList("messages"));

        if(this.yamlConfiguration.getBoolean("enabled")) {
            List<String> lines = new ArrayList<>();

            try (final BufferedReader br = new BufferedReader((new InputStreamReader(new FileInputStream(new File(this.spigotBroadcast.getDataFolder() + File.separator + "messages.txt")), Charsets.UTF_8)))) {
                for(String line; (line = br.readLine()) != null; ) {
                    lines.add(line);
                }
            } catch (final FileNotFoundException e) {
                e.printStackTrace();
            } catch (final IOException e) {
                e.printStackTrace();
            }

            this.broadcastConfiguration.getMessages().addAll(lines);
        }
    }

    private void log(final Level level, final String message) {
        Bukkit.getLogger().log(level, message);
    }

    public BroadcastConfiguration getBroadcastConfiguration() {
        return broadcastConfiguration;
    }
}
