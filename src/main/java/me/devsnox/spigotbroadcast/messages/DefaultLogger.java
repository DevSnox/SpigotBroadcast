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

import org.bukkit.ChatColor;

import java.util.logging.Level;

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.09.2018 13:35.
 * Last edit 02.09.2018
 */
public interface DefaultLogger extends Logger {

    default void logDesign() {
        this.log(ChatColor.AQUA + "[ -------------------------------------------------------------- ]");
    }

    default void logSeparator() {
        this.log(ChatColor.DARK_GRAY + "{ " + ChatColor.GREEN + "LOADING" + ChatColor.DARK_GRAY + " }");
    }

    default void logSpace() {
        this.log(" ");
    }

    default void log(final String message) {
        this.log(Level.INFO, message);
    }
}
