package com.wandy.api.listeners;

import java.util.HashMap;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class IPListener implements Listener {
	
	public static HashMap<String, String> ips = new HashMap<String, String>();

	public static String getIP(Player p) {
		String ip = (String) ips.get(p.getName());
		return ip;
	}

	@EventHandler
	public static void aoLogar(PlayerLoginEvent e) {
		String ip = new StringBuilder().append(e.getAddress()).toString();
		ips.put(e.getPlayer().getName(), ip);
	}

	@EventHandler
	public static void aoSair(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		ips.remove(p.getName());
	}
}
