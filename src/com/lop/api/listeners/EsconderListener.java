package com.wandy.api.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.wandy.api.commands.EsconderCommand;

public class EsconderListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public static void aoEntrar(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		for (Player todos : Bukkit.getOnlinePlayers()) {
			if (!p.hasPermission("wandy.equipe")) {
				if (EsconderCommand.esnd.contains(todos.getName())) {
					todos.hidePlayer(p);
				}
			}
		}
	}
}
