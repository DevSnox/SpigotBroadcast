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

package me.devsnox.spigotbroadcast.commands;

import me.devsnox.spigotbroadcast.loader.Loader;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Yasin Dalal (DevSnox)
 * Created by Yasin Dalal (DevSnox) on 24.12.2017 00:00.
 */
public final class BroadcastCommand implements CommandExecutor {

    private final JavaPlugin javaPlugin;
    private final Loader loader;

    public BroadcastCommand(final JavaPlugin javaPlugin, final Loader loader) {
        this.javaPlugin = javaPlugin;
        this.loader = loader;

        PluginCommand command = javaPlugin.getCommand(javaPlugin.getName());
        command.setPermission(javaPlugin.getName() + ".admin");
        command.setExecutor(this);
    }

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        final PluginDescriptionFile description = this.javaPlugin.getDescription();

        if (args.length != 0) {
            if (args[0].equalsIgnoreCase("reload")) {
                this.loader.reload();
            } else if (args[0].equalsIgnoreCase("info")) {
                sendPluginByMessage(sender, description);
            }
        } else {
            sendSplinter(sender, description.getName());
            sendSpace(sender);
            sendPluginByMessage(sender, description);
            sendSpace(sender);
            sender.sendMessage(ChatColor.GREEN + "Commands:");
            sendHelp(sender, label, "info");
            sendHelp(sender, label, "reload");
            sendSpace(sender);
            sendSplinter(sender, description.getName());
        }
        return false;
    }

    private void sendPluginByMessage(final CommandSender sender, final PluginDescriptionFile description) {
        sender.sendMessage(ChatColor.YELLOW + description.getFullName() + ChatColor.YELLOW + " by " + description.getAuthors());
    }

    private void sendHelp(final CommandSender sender, final String label, final String subCommand) {
        sender.sendMessage(ChatColor.GRAY + "/" + ChatColor.RED + label + " " + ChatColor.YELLOW + subCommand);
    }

    private void sendSplinter(final CommandSender sender, final String name) {
        sender.sendMessage(ChatColor.DARK_GRAY + "|" + ChatColor.AQUA + "| " + ChatColor.DARK_AQUA + name + ChatColor.AQUA + " |" + ChatColor.DARK_GRAY + "|");
    }

    private void sendSpace(final CommandSender sender) {
        sender.sendMessage(" ");
    }

}
