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

package me.devsnox.spigotbroadcast.messages;

import me.devsnox.spigotbroadcast.utils.StringUtils;
import org.bukkit.ChatColor;

import java.util.logging.Level;


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.09.2018 13:37.
 * Last edit 02.09.2018
 */
public class SimpleLogger implements DefaultLogger {

    private final java.util.logging.Logger logger;
    private final StringUtils stringUtils;

    public SimpleLogger(final java.util.logging.Logger logger) {
        this.logger = logger;
        this.stringUtils = new StringUtils();
        this.stringUtils.setFormat(ChatColor.YELLOW + "-= ");
    }

    @Override
    public void log(Level level, String message) {
        this.logger.log(level, message);
    }

    @Override
    public String format(String message) {
        return this.stringUtils.format(message);
    }
}
