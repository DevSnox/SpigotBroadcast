package me.devsnox.spigotbroadcast.commands;

import me.devsnox.spigotbroadcast.SpigotBroadcast;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public final class BroadcastCommand implements org.bukkit.command.CommandExecutor {

    private final SpigotBroadcast spigotBroadcast;

    public BroadcastCommand(final SpigotBroadcast spigotBroadcast) {
        this.spigotBroadcast = spigotBroadcast;
    }

    public boolean onCommand(final CommandSender commandSender, final Command command, final String label, final String[] args) {
        if (commandSender.hasPermission("spigotbroadcast.admin")) {

            final String pluginName = this.spigotBroadcast.getName();

            if (args.length != 0) {
                if (args[0].equalsIgnoreCase("reload")) {
                    this.spigotBroadcast.reload();
                } else if (args[0].equalsIgnoreCase("info")) {
                    commandSender.sendMessage("§e" + pluginName + " §cv." + this.spigotBroadcast.getDescription().getVersion() + " §eby DevSnox");
                }
            } else {
                commandSender.sendMessage("§8|§b| §3" + pluginName  + " §b|§8|");
                commandSender.sendMessage(" ");
                commandSender.sendMessage("§e" + pluginName + " §cv." + this.spigotBroadcast.getDescription().getVersion() + " §eby DevSnox");
                commandSender.sendMessage(" ");
                commandSender.sendMessage("§aCommands:");
                commandSender.sendMessage("§7/§c" + pluginName.toLowerCase() + " §einfo");
                commandSender.sendMessage("§7/§c" + pluginName.toLowerCase() + " §ereload");
                commandSender.sendMessage(" ");
                commandSender.sendMessage("§8|§b| §3" + pluginName + " §b|§8|");
            }
        }
        return false;
    }
}
