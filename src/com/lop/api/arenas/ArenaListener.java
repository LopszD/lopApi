package com.wandy.api.arenas;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class ArenaListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public static void aoDigitar(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		int i = 0;
		if (ArenaMenu.areia.containsKey(p.getName())) {
			i++;
		}
		if (ArenaMenu.pedra.containsKey(p.getName())) {
			i++;
		}
		if (ArenaMenu.nether.containsKey(p.getName())) {
			i++;
		}
		if (i > 0) {
			if (!e.getMessage().startsWith("/spawn")) {
				if (!e.getMessage().startsWith("/arena")) {
					if (!e.getMessage().startsWith("/arenas")) {
						if (!e.getMessage().startsWith("/vip")) {
							if (!p.hasPermission("wandy.passarena")) {
								e.setCancelled(true);
								p.sendMessage("§cVocê não pode executar este comando dentro da arena.");
							}
						}
					}
				}
			}
			return;
		}
	}

	@EventHandler
	public static void aoQuitar(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (ArenaMenu.areia.containsKey(p.getName())) {
			ArenaMenu.areia.remove(p.getName());
		}
		if (ArenaMenu.pedra.containsKey(p.getName())) {
			ArenaMenu.pedra.remove(p.getName());
		}
		if (ArenaMenu.nether.containsKey(p.getName())) {
			ArenaMenu.nether.remove(p.getName());
		}
	}

	@EventHandler
	public static void aoMorrer(PlayerDeathEvent e) {
		Player p = e.getEntity();
		if (ArenaMenu.areia.containsKey(p.getName())) {
			ArenaMenu.areia.remove(p.getName());
		}
		if (ArenaMenu.pedra.containsKey(p.getName())) {
			ArenaMenu.pedra.remove(p.getName());
		}
		if (ArenaMenu.nether.containsKey(p.getName())) {
			ArenaMenu.nether.remove(p.getName());
		}
	}

	@EventHandler
	public static void aoSair(PlayerTeleportEvent e) {
		if (e.getFrom().getWorld().getName().equals("world_arenas")) {
			Player p = e.getPlayer();
			if (!e.getTo().getWorld().getName().equals("world_arenas")) {
				if (ArenaMenu.pedra.containsKey(p.getName())) {
					ArenaMenu.pedra.remove(p.getName());
				}
				if (ArenaMenu.areia.containsKey(p.getName())) {
					ArenaMenu.areia.remove(p.getName());
				}
				if (ArenaMenu.nether.containsKey(p.getName())) {
					ArenaMenu.nether.remove(p.getName());
				}
			}
		}
	}
}
