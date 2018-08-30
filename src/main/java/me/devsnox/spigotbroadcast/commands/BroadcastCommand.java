package me.devsnox.spigotbroadcast.commands;

import me.devsnox.spigotbroadcast.SpigotBroadcast;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class BroadcastCommand implements org.bukkit.command.CommandExecutor {

    private SpigotBroadcast spigotBroadcast;

    public BroadcastCommand(SpigotBroadcast spigotBroadcast) {
        this.spigotBroadcast = spigotBroadcast;
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender.hasPermission("spigotbroadcast.admin")) {
            if (args.length != 0) {
                if (args[0].equalsIgnoreCase("reload")) {
                    spigotBroadcast.reload();
                } else if (args[0].equalsIgnoreCase("info")) {
                    commandSender.sendMessage("§eSpigotBroadcast §cv." + spigotBroadcast.getDescription().getVersion() + " §eby DevSnox");
                }
            } else {
                commandSender.sendMessage("§8|§b| §3SpigotBroadcast §b|§8|");
                commandSender.sendMessage(" ");
                commandSender.sendMessage("§eSpigotBroadcast §cv." + spigotBroadcast
                        .getDescription().getVersion() + " §eby DevSnox");
                commandSender.sendMessage(" ");
                commandSender.sendMessage("§aCommands:");
                commandSender.sendMessage("§7/§cspigotbroadcast §einfo");
                commandSender.sendMessage("§7/§cspigotbroadcast §ereload");
                commandSender.sendMessage(" ");
                commandSender.sendMessage("§8|§b| §3SpigotBroadcast §b|§8|");
            }
        }
        return false;
    }
}
