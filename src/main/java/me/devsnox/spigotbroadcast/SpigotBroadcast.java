package me.devsnox.spigotbroadcast;

import me.devsnox.spigotbroadcast.configuration.AdvancedPlugin;
import me.devsnox.spigotbroadcast.loader.Loader;
import me.devsnox.spigotbroadcast.messages.SimpleLogger;

public final class SpigotBroadcast extends AdvancedPlugin {
    @Override
    public void onEnable()
    {
        new Loader(this, new SimpleLogger(this.getLogger())).load();
    }
}
