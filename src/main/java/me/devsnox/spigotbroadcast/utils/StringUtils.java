package me.devsnox.spigotbroadcast.utils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.09.2018 12:55.
 * Last edit 02.09.2018
 */
public final class StringUtils {

    private String prefix = "";
    private String suffix = "";
    private String format = "";

    @NotNull
    public String prefix(final String message) {
        return this.prefix + message;
    }

    @NotNull
    public String suffix(final String message) {
        return this.suffix + message;
    }

    @NotNull
    public String format(final String message) {
        return this.format(this.format, message);
    }

    @SuppressWarnings("WeakerAccess")
    @NotNull
    public String format(final String format, final String message) {
        final List<Character> characters = new ArrayList<>();

        for (char c : format.toCharArray()) {
            characters.add(c);
        }

        Collections.reverse(characters);
        return format + message + characters;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
