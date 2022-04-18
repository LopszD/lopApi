package com.wandy.api.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import com.wandy.api.commands.GodCommand;

public class DeusListener implements Listener {

	@EventHandler
	public void mododeus(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (GodCommand.godmode.contains(p.getName())) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public static void aoFoodar(FoodLevelChangeEvent e) {
		Player p = (Player) e.getEntity();
		if (GodCommand.godmode.contains(p.getName())) {
			e.setCancelled(true);
		}
	}
}