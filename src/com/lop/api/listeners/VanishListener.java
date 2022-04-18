package com.wandy.api.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import com.wandy.api.commands.VanishCommand;

public class VanishListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (p.hasPermission("wandy.vanish")) {
			if (VanishCommand.vanish.contains(p.getName())) {
				for (Player pOns : Bukkit.getOnlinePlayers()) {
					if (!pOns.hasPermission("wandy.vanish"))
						pOns.hidePlayer(p);
				}
			}
		} else {
			for (Player pOns : Bukkit.getOnlinePlayers()) {
				if (VanishCommand.vanish.contains(pOns.getName()))
					p.hidePlayer(pOns);
			}
		}
	}

	@EventHandler
	public void onVisu(EntityTargetEvent e) {
		if (e.isCancelled())
			return;
		if (e.getTarget() instanceof Player)
			if (VanishCommand.vanish.contains(((Player) e.getTarget()).getName()))
				e.setCancelled(true);
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		if (e.isCancelled())
			return;
		if (VanishCommand.vanish.contains(e.getPlayer().getName()))
			if (e.getPlayer().hasPermission("wandy.vanish") && !e.getPlayer().isOp())
				e.setCancelled(true);
	}

	@EventHandler
	public void onItem(PlayerPickupItemEvent e) {
		if (e.isCancelled())
			return;
		if (VanishCommand.vanish.contains(e.getPlayer().getName()))
			if (e.getPlayer().hasPermission("wandy.vanish") && !e.getPlayer().isOp())
				e.setCancelled(true);
	}

	@EventHandler
	public void onPvP(EntityDamageByEntityEvent e) {
		if (e.isCancelled())
			return;

		if (e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			if (VanishCommand.vanish.contains(p.getName()))
				if (!p.hasPermission("wandy.vanish"))
					e.setCancelled(true);
		}
	}
}
