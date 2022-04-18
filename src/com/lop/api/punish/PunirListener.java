package com.wandy.api.punish;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PunirListener implements Listener {

	@EventHandler
	public static void aoEntrar(PlayerJoinEvent e) {
		MembroP.loadInfomation(e.getPlayer());
	}

	@EventHandler
	public static void aoSair(PlayerQuitEvent e) {
		if (MembroP.hasMembro(e.getPlayer().getName())) {
			MembroP.mem.remove(e.getPlayer().getName().toLowerCase());
		}
	}
}
