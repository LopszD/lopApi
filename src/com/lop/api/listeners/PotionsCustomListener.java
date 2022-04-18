package com.wandy.api.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionsCustomListener implements Listener {
	
	@EventHandler
	public static void aoTomar(PlayerItemConsumeEvent e) {
		if (e.getPlayer().getItemInHand() != null) {
			if (!e.getPlayer().getItemInHand().getType().equals(Material.AIR)) {
				if (e.getPlayer().getItemInHand().hasItemMeta()) {
					if (e.getPlayer().getItemInHand().getItemMeta().hasDisplayName()) {
						if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains("§5Poção de ")) {
							if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains("§1")) {
								e.setCancelled(true);
								Player p = e.getPlayer();
								if (p.getItemInHand().getAmount() >= 2) {
									p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
								} else if (p.getItemInHand().getAmount() == 1) {
									p.setItemInHand(new ItemStack(Material.AIR));
								}
								p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3000, 1));
								p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 3000, 1));
								return;
							}
						}
					}
				}
			}
		}
	}
}
