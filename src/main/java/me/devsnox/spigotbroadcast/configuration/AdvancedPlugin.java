package me.devsnox.spigotbroadcast.configuration;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;

public abstract class AdvancedPlugin extends JavaPlugin {

    @Override
    public final void saveResource(final String resourcePath, final boolean replace) {
        if (resourcePath == null || resourcePath.equals("")) {
            throw new IllegalArgumentException("ResourcePath cannot be null or empty");
        }

        final String editedResourcePath = resourcePath.replace('\\', '/');

        final InputStream in = getResource(editedResourcePath);

        if (in == null) {
            throw new IllegalArgumentException("The embedded resource '" + editedResourcePath + "' cannot be found in " + super.getFile());
        }

        final File outFile = new File(super.getDataFolder(), editedResourcePath);
        final int lastIndex = editedResourcePath.lastIndexOf('/');
        final File outDir = new File(super.getDataFolder(), editedResourcePath.substring(0, lastIndex >= 0 ? lastIndex : 0));

        if (!outDir.exists()) {
            outDir.mkdirs();
        }

        try {
            if (!outFile.exists() || replace) {
                OutputStream out = new FileOutputStream(outFile);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.close();
                in.close();
            }
        } catch (IOException ex) {
            super.getLogger().log(Level.SEVERE, "Could not save " + outFile.getName() + " to " + outFile, ex);
        }
    }

    @Override
    public final InputStream getResource(final String filename) {
        if (filename == null) {
            throw new IllegalArgumentException("Filename cannot be null");
        }

        try {
            final URL url = getClassLoader().getResource(filename);

            if (url == null) {
                return null;
            }

            final URLConnection connection = url.openConnection();
            connection.setUseCaches(false);
            return connection.getInputStream();
        } catch (IOException ex) {
            return null;
        }
    }
}
