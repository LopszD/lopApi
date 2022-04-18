package com.wandy.api.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.wandy.api.commands.VanishCommand;
import com.wandy.economy.events.MoneyPayEvent;

public class PayListener implements Listener {

	@EventHandler
	public static void aoMandar(MoneyPayEvent e) {
		String nome = e.getTarget().getName();
		Player p = e.getPlayer();
		if (VanishCommand.vanish.contains(nome)) {
			p.sendMessage("§cEste usuário não está on-line.");
			return;
		}
		if (ToggleListener.cancelcoins.contains(nome)) {
			p.sendMessage("§cEste usuário desativou o recebimento de coins.");
			return;
		}
	}
}
