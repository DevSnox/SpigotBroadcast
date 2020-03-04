/*
 * Copyright (C) 2018 Yasin Dalal (DevSnox)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package me.devsnox.spigotbroadcast.configuration;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import java.util.logging.Level;

/**
 * @author Yasin Dalal (DevSnox)
 * Created by Yasin Dalal (DevSnox) on 24.12.2017 00:00.
 */
public final class BroadcastConfigurator {

    private final JavaPlugin javaPlugin;

    private final File config;
    private final YamlConfiguration yamlConfiguration;

    private BroadcastConfiguration broadcastConfiguration;

    public BroadcastConfigurator(final JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
        this.config = new File(this.javaPlugin.getDataFolder() + File.separator + "config.yml");

        this.yamlConfiguration = new UTF8YamlConfiguration();

        try {
            this.yamlConfiguration.load(this.config);
        } catch (final IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }


    public void load() {
        final boolean prefixEnabled = this.yamlConfiguration.getBoolean("prefix-enabled");
        final String prefix = ChatColor.translateAlternateColorCodes('&',
                this.yamlConfiguration.getString("prefix"));

        TimeUnit timeUnit = TimeUnit.MINUTES;

        try {
            timeUnit = TimeUnit.valueOf(this.yamlConfiguration.getString("timeunit"));

            if (timeUnit == TimeUnit.NANOSECONDS || timeUnit == TimeUnit.MICROSECONDS || timeUnit == TimeUnit.MILLISECONDS) {
                throw new IllegalArgumentException("TimeUnit is tow low");
            }
        } catch (final IllegalArgumentException exception) {
            this.log(Level.SEVERE, ChatColor.RED + "" + ChatColor.BOLD + "Error while trying to get timeunit!");
            this.log(Level.INFO, ChatColor.RED + "" + ChatColor.BOLD + "Valid timeunits are DAYS, HOURS, MINUTES, SECONDS!");

            this.yamlConfiguration.set("timeunit", TimeUnit.MINUTES);

            try {
                this.yamlConfiguration.save(this.config);
            } catch (final IOException e) {
                e.printStackTrace();
            }

            this.log(Level.INFO, "TimeUnit changed to MINUTES, you can change it in the config.yml!");
        }

        final List<BroadcastMessage> messages = new ArrayList<>();

        if (!prefixEnabled) {

            this.yamlConfiguration.getStringList("messages")
                    .forEach(s -> messages.add(new BroadcastMessage(Arrays.asList(s))));

            this.javaPlugin.saveResource("config.yml", true);

            //TODO: Logging
        } else {
            final ConfigurationSection configurationSection = this.yamlConfiguration.getConfigurationSection("messages");
            configurationSection.getKeys(false).forEach(s -> messages.add(new BroadcastMessage(configurationSection.getStringList(s + ".lines"))));
        }

        messages.replaceAll(broadcastMessage -> {
            broadcastMessage.getLines().replaceAll(raw -> {
                StringBuilder stringBuilder = new StringBuilder();

                if (prefixEnabled) {
                    stringBuilder.append(prefix);
                    stringBuilder.append(ChatColor.RESET);
                }

                stringBuilder.append(ChatColor.translateAlternateColorCodes('&', raw));

                return stringBuilder.toString();
            });

            return broadcastMessage;
        });

        this.broadcastConfiguration = new BroadcastConfiguration(prefixEnabled, prefix, this.yamlConfiguration.getInt("interval"), timeUnit, messages);
    }

    private void log(final Level level, final String message) {
        Bukkit.getLogger().log(level, message);
    }

    public BroadcastConfiguration getBroadcastConfiguration() {
        return this.broadcastConfiguration;
    }
}
