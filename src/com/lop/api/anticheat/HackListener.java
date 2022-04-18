package com.wandy.api.anticheat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class HackListener implements Listener{

	@EventHandler
	private void onPlayerJoinEvent(PlayerJoinEvent event) {
		if (!(event.getPlayer() instanceof Player)) {
			return;
		}
		Player player = event.getPlayer();
		HackUtils.setPlayer(player);
	}
}
