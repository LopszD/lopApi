package com.wandy.api.utils;

import ru.tehkode.permissions.bukkit.*;
import org.bukkit.inventory.*;

import com.wandy.api.Main;

import java.util.*;
import org.bukkit.*;

public class Util
{
    @SuppressWarnings("deprecation")
	public static String getColorPrefix(final String player) {
        if (Main.getInstance().getServer().getPluginManager().getPlugin("PermissionsEx") != null) {
            return ChatColor.getLastColors(Text.colorize(PermissionsEx.getUser(player).getGroups()[0].getPrefix()));
        }
        return "&7";
    }
    
    public static String onlyNumbers(final String str) {
        if (str != null) {
            return str.replaceAll("[^0123456789]", "");
        }
        return "";
    }
    
    public static String onlyWords(final String input) {
        if (input != null) {
            return input.replaceAll("\\d+", "");
        }
        return "";
    }
    
    public static boolean isNum(final String s) {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean invCanHoldItem(final Inventory inv, final ItemStack is) {
        Iterator<?> iterator = inv.iterator();
        if (iterator.hasNext()) {
            ItemStack itemStack = (ItemStack) iterator.next();
            return itemStack == null || itemStack.getType() != is.getType() || itemStack.getAmount() > is.getMaxStackSize() - is.getAmount() || true;
        }
        return false;
    }
    
    public static long calculate(final long delay) {
        return System.currentTimeMillis() - delay;
    }
    
    public static boolean materialExists(final String m) {
        return Material.getMaterial(m.toUpperCase()) != null;
    }
}
