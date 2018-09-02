package me.devsnox.spigotbroadcast.commands;

import me.devsnox.spigotbroadcast.loader.Loader;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public final class BroadcastCommand implements org.bukkit.command.CommandExecutor {
    private final JavaPlugin javaPlugin;
    private final Loader loader;
    
    public BroadcastCommand(final JavaPlugin javaPlugin, final Loader loader)
    {
        this.javaPlugin = javaPlugin;
        this.loader = loader;
        PluginCommand command = javaPlugin.getCommand(javaPlugin.getName());
        command.setPermission(javaPlugin.getName() + ".admin");
        command.setExecutor(this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args)
    {
        final PluginDescriptionFile description = this.javaPlugin.getDescription();
        if (args.length != 0)
        {
            if (args[0].equalsIgnoreCase("reload"))
            {
                this.loader.reload();
            }
            else if (args[0].equalsIgnoreCase("info"))
            {
                sendPluginByMessage(sender, description);
            }
        }
        else
        {
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
    
    private void sendPluginByMessage(final CommandSender sender, final PluginDescriptionFile description)
    {
        sender.sendMessage(ChatColor.YELLOW + description.getFullName() + ChatColor.YELLOW + " by " + description.getAuthors());
    }
    
    private void sendHelp(final CommandSender sender, final String label, final String subCommand)
    {
        sender.sendMessage(ChatColor.GRAY + "/" + ChatColor.RED + label + " " + ChatColor.YELLOW + subCommand);
    }
    
    private void sendSplinter(final CommandSender sender, final String name)
    {
        sender.sendMessage(ChatColor.DARK_GRAY + "|" + ChatColor.AQUA + "| " + ChatColor.DARK_AQUA + name + ChatColor.AQUA + " |" + ChatColor.DARK_GRAY + "|");
    }
    
    private void sendSpace(final CommandSender sender)
    {
        sender.sendMessage(" ");
    }
    
}
