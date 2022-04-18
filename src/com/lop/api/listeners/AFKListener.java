package com.wandy.api.listeners;

import java.util.HashMap;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class AFKListener implements Listener {
	
	public static HashMap<String, Integer> afk = new HashMap<String, Integer>();

	@EventHandler
	public static void aoEntrar(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (!p.hasPermission("wandy.semafk")) {
			if (afk.containsKey(p.getName())) {
				afk.replace(p.getName(), Integer.valueOf(0));
			} else {
				afk.put(p.getName(), Integer.valueOf(0));
			}
			return;
		}
	}

	@EventHandler
	public static void aoSair(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (afk.containsKey(p.getName())) {
			afk.remove(p.getName());
		}
	}

	@EventHandler
	public static void aoDigitar(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if (afk.containsKey(p.getName())) {
			afk.replace(p.getName(), Integer.valueOf(0));
		} else {
			afk.put(p.getName(), Integer.valueOf(0));
		}
	}

	@EventHandler
	public static void aoMover(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (afk.containsKey(p.getName())) {
			afk.replace(p.getName(), Integer.valueOf(0));
		} else {
			afk.put(p.getName(), Integer.valueOf(0));
		}
	}

	@EventHandler
	public static void aoComandar(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		if (afk.containsKey(p.getName())) {
			afk.replace(p.getName(), Integer.valueOf(0));
		} else {
			afk.put(p.getName(), Integer.valueOf(0));
		}
	}
}
