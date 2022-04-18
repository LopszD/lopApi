package com.wandy.api.dúvida;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.wandy.api.utils.fanciful.FancyMessage;

public class DuvidaListener implements Listener {
	
	@EventHandler
	public static void aoEntrar(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (DAPI.temDuvida(p.getName())) {
			if (DAPI.taRespondida(p.getName())) {
				new FancyMessage("").then("§eSua dúvida já foi respondida! Clique ").color(ChatColor.YELLOW).then("§e§lAQUI").color(ChatColor.GOLD).command("/duvida ver").then(" §epara vê-la.").color(ChatColor.YELLOW).send(p);
			}
		}
	}
}
