package me.devsnox.spigotbroadcast.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.commons.lang.Validate;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

/**
 *  class by @Cybermaxke
 */

public final class UTF8YamlConfiguration extends YamlConfiguration {

    @Override
    public void save(File file) throws IOException {
        Validate.notNull(file, "File cannot be null");

        // Create the parent dirs
        Files.createParentDirs(file);

        // Save the data as a string
        String data = this.saveToString();

        // Write the data as utf8
        Writer writer = new OutputStreamWriter(new FileOutputStream(file), Charsets.UTF_8);
        try {
            writer.write(data);
        } finally {
            writer.close();
        }
    }

    @Override
    public void load(File file) throws FileNotFoundException, IOException, InvalidConfigurationException {
        Validate.notNull(file, "File cannot be null");
        // Load the content of the target file
        this.load(new InputStreamReader(new FileInputStream(file), Charsets.UTF_8));
    }

    @Override
    @Deprecated
    public void load(InputStream stream) throws IOException, InvalidConfigurationException {
        Validate.notNull(stream, "Stream cannot be null");
        // Load the content of the target stream
        this.load(new InputStreamReader(stream, Charsets.UTF_8));
    }
}