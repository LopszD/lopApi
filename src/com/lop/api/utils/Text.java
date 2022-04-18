package com.wandy.api.utils;

import org.bukkit.*;

public class Text
{
    public static String colorize(final String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
