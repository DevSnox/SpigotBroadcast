package me.devsnox.spigotbroadcast.loader;

import me.devsnox.spigotbroadcast.commands.BroadcastCommand;
import me.devsnox.spigotbroadcast.configuration.BroadcastConfigurator;
import me.devsnox.spigotbroadcast.exceptions.AlreadyInitializeException;
import me.devsnox.spigotbroadcast.messages.Logger;
import me.devsnox.spigotbroadcast.task.BroadcastTask;
import org.bstats.bukkit.Metrics;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.09.2018 13:32.
 * Last edit 02.09.2018
 */
public final class Loader
{
	private final Logger logger;
	private final JavaPlugin javaPlugin;
	private BroadcastConfigurator broadcastConfigurator;
	private BroadcastTask broadcastTask;
	
	public Loader(final JavaPlugin javaPlugin, final Logger logger)
	{
		this.javaPlugin = javaPlugin;
		this.logger = logger;
	}
	
	public void load()
	{
		if (this.broadcastConfigurator != null || this.broadcastTask != null)
		{
			throw new AlreadyInitializeException();
		}
		this.sendHeader();
		this.createConfigFile();
		this.loadConfiguration();
		this.startTask();
		this.registerCommands();
		this.initializeMetrics();
		this.sendFooter();
	}
	
	public void reload()
	{
		this.broadcastConfigurator = null;
		this.broadcastTask.cancel();
		this.broadcastTask = null;
		this.load();
	}
	
	private void registerCommands()
	{
		this.logByFormat("register commands");
		new BroadcastCommand(this.javaPlugin, this);
	}
	
	private void startTask()
	{
		this.logByFormat("starting task");
		this.broadcastTask = new BroadcastTask(this.javaPlugin, this.broadcastConfigurator.getBroadcastConfiguration());
		this.broadcastTask.start();
	}
	
	private void initializeMetrics()
	{
		this.logByFormat("register metrics");
		new Metrics(this.javaPlugin);
	}
	
	private void createConfigFile()
	{
		final String configName = "config.yml";
		this.logByFormat("creating " + configName);
		this.javaPlugin.saveResource(configName, false);
	}
	
	private void loadConfiguration()
	{
		this.logByFormat("loading configuration");
		this.broadcastConfigurator = new BroadcastConfigurator(this.javaPlugin);
		this.broadcastConfigurator.load();
	}
	
	private void sendHeader()
	{
		this.logger.logSpace();
		this.logger.logDesign();
		this.logger.logSpace();
		final PluginDescriptionFile description = this.javaPlugin.getDescription();
		this.logger.log(ChatColor.DARK_GRAY + "{ " + ChatColor.GREEN + "INFORMATIONS" + ChatColor.DARK_GRAY + " }");
		this.logger.log(this.logger.format(description.getName()));
		this.logByFormat("Authors: " + description.getAuthors());
		this.logByFormat("Version: " + description.getVersion());
		this.logger.log(ChatColor.DARK_RED + "Please report bugs on spigotmc.org per PM");
		this.logger.logSeparator();
		this.logger.logSpace();
		this.logger.log(ChatColor.DARK_GRAY + "{ " + ChatColor.GREEN + "LOADING" + ChatColor.DARK_GRAY + " }");
	}
	
	private void sendFooter()
	{
		this.logger.log(ChatColor.GREEN + "Successfully enabled " + this.javaPlugin.getDescription().getFullName());
		this.logger.logSeparator();
		this.logger.logSpace();
		this.logger.logDesign();
		this.logger.logSpace();
	}
	
	private void logByFormat(String message)
	{
		this.logger.log(this.logger.format(message));
	}
}
