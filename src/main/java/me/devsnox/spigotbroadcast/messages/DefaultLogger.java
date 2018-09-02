package me.devsnox.spigotbroadcast.messages;

import org.bukkit.ChatColor;

import java.util.logging.Level;

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.09.2018 13:35.
 * Last edit 02.09.2018
 */
public interface DefaultLogger extends Logger
{
	default void logDesign()
	{
		this.log(ChatColor.AQUA + "[ -------------------------------------------------------------- ]");
	}
	
	default void logSeparator()
	{
		this.log(ChatColor.DARK_GRAY + "{ " + ChatColor.GREEN + "LOADING" + ChatColor.DARK_GRAY + " }");
	}
	
	default void logSpace()
	{
		this.log(" ");
	}
	
	default void log(final String message)
	{
		this.log(Level.INFO, message);
	}
}
