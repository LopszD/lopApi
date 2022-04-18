package com.wandy.api.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TabAPI {
   public static Class<?> getNmsClass(String nmsClassName) throws ClassNotFoundException {
      return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + "." + nmsClassName);
   }

   public static String getServerVersion() {
      return Bukkit.getServer().getClass().getPackage().getName().substring(23);
   }

   @SuppressWarnings("null")
public static void sendTablist(Player p, String msg, String msg2) {
      Object footer;
      try {
         Object header;
         Object ppoplhf;
         Field f;
         Object nmsp;
         Object pcon;
         if (!getServerVersion().equalsIgnoreCase("v1_9_R1") && !getServerVersion().equalsIgnoreCase("v1_9_R2")) {
            if (!getServerVersion().equalsIgnoreCase("v1_8_R1") && !getServerVersion().equalsIgnoreCase("v1_8_R2") && !getServerVersion().equalsIgnoreCase("v1_8_R3")) {
               header = getNmsClass("ChatSerializer").getMethod("a", String.class).invoke((Object)null, "{'text': '" + msg + "'}");
               footer = getNmsClass("ChatSerializer").getMethod("a", String.class).invoke((Object)null, "{'text': '" + msg2 + "'}");
               ppoplhf = getNmsClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(getNmsClass("IChatBaseComponent")).newInstance(header);
               f = ppoplhf.getClass().getDeclaredField("b");
               f.setAccessible(true);
               f.set(ppoplhf, footer);
               nmsp = p.getClass().getMethod("getHandle").invoke(p);
               pcon = nmsp.getClass().getField("playerConnection").get(nmsp);
               pcon.getClass().getMethod("sendPacket", getNmsClass("Packet")).invoke(pcon, ppoplhf);
            } else {
               header = getNmsClass("IChatBaseComponent$ChatSerializer").getMethod("a", String.class).invoke((Object)null, "{'text': '" + msg + "'}");
               footer = getNmsClass("IChatBaseComponent$ChatSerializer").getMethod("a", String.class).invoke((Object)null, "{'text': '" + msg2 + "'}");
               ppoplhf = getNmsClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(getNmsClass("IChatBaseComponent")).newInstance(header);
               f = ppoplhf.getClass().getDeclaredField("b");
               f.setAccessible(true);
               f.set(ppoplhf, footer);
               nmsp = p.getClass().getMethod("getHandle").invoke(p);
               pcon = nmsp.getClass().getField("playerConnection").get(nmsp);
               pcon.getClass().getMethod("sendPacket", getNmsClass("Packet")).invoke(pcon, ppoplhf);
            }
         } else {
            header = getNmsClass("ChatComponentText").getConstructor(String.class).newInstance(ChatColor.translateAlternateColorCodes('&', msg));
            footer = getNmsClass("ChatComponentText").getConstructor(String.class).newInstance(ChatColor.translateAlternateColorCodes('&', msg2));
            ppoplhf = getNmsClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(getNmsClass("IChatBaseComponent")).newInstance(header);
            f = ppoplhf.getClass().getDeclaredField("b");
            f.setAccessible(true);
            f.set(ppoplhf, footer);
            nmsp = p.getClass().getMethod("getHandle").invoke(p);
            pcon = nmsp.getClass().getField("playerConnection").get(nmsp);
            pcon.getClass().getMethod("sendPacket", getNmsClass("Packet")).invoke(pcon, ppoplhf);
         }
      } catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException | InstantiationException | NoSuchFieldException | IllegalAccessException var9) {
         footer = null;
         ((Exception)footer).printStackTrace();
      }

   }
}