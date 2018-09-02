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

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.apache.commons.lang.Validate;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

/**
 * class by @Cybermaxke
 * edited by Yasin Dalal (DevSnox)
 */

public final class UTF8YamlConfiguration extends YamlConfiguration {

    @Override
    public void save(final File file) throws IOException {
        Validate.notNull(file, "File cannot be null");

        // Create the parent dirs
        Files.createParentDirs(file);

        // Save the data as a string
        final String data = this.saveToString();

        // Write the data as utf8
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), Charsets.UTF_8)) {
            writer.write(data);
        }
    }

    @Override
    public final void load(final File file) throws IOException, InvalidConfigurationException {
        Validate.notNull(file, "File cannot be null");

        // Load the content of the target file
        this.load(new InputStreamReader(new FileInputStream(file), Charsets.UTF_8));
    }
}