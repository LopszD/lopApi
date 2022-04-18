package com.wandy.api.item;

import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class ItemListener implements Listener {
	
	@EventHandler
	public static void aoClicar(PlayerInteractAtEntityEvent e) {
		if (e.getRightClicked().getType() == EntityType.ARMOR_STAND) {
			if (!ItemCommand.editarmini.contains(e.getPlayer().getName())) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public static void aoBater(EntityDamageByEntityEvent e) {
		if ((e.getEntity() instanceof ArmorStand)) {
			if ((e.getDamager() instanceof Player)) {
				Player p = (Player) e.getDamager();
				if (p.getInventory().getItemInHand().getType().equals(Material.GOLD_AXE)) {
					e.setCancelled(true);
					ArmorStand as = (ArmorStand) e.getEntity();
					if (ItemCommand.selected.containsKey(p.getName())) {
						if (((Entity) ItemCommand.selected.get(p.getName())).equals(as)) {
							p.sendMessage("§cVocê já selecionou este objeto!");
							return;
						}
					}
					ItemCommand.selected.put(p.getName(), as);
					p.sendMessage("§aVocê selecionou este objeto!");
					return;
				}
				if (!ItemCommand.editarmini.contains(p.getName())) {
					e.setCancelled(true);
					return;
				}
				ItemCommand.selected.remove(p.getName());
			}
		}
	}

	@EventHandler
	public static void aoEntrar(PlayerJoinEvent e) {
		if (ItemCommand.editarmini.contains(e.getPlayer().getName())) {
			ItemCommand.editarmini.remove(e.getPlayer().getName());
		}
	}
}
