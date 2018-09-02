package me.devsnox.spigotbroadcast.messages;

import java.util.logging.Level;

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.09.2018 13:34.
 * Last edit 02.09.2018
 */
public interface Logger
{
	void logDesign();
	
	void logSeparator();
	
	void logSpace();
	
	void log(final String message);
	
	void log(final Level level, final String message);
	
	String format(final String message);
}
