package com.wandy.api.especiais.listeners;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class MoedaReparaçãoListener implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public static void aoMover(InventoryClickEvent e) {
		int ch = 0;
		if (e.getCursor() != null) {
			if (!(e.getCursor().getType() == Material.AIR)) {
				if (e.getCurrentItem() != null) {
					if (!(e.getCurrentItem().getType() == Material.AIR)) {
						if (e.getCursor().getType() == Material.IRON_INGOT) {
							if (e.getCursor().hasItemMeta()) {
								if (e.getCursor().getItemMeta().hasDisplayName()) {
									if (e.getCursor().getItemMeta().getDisplayName().contains("§aMoeda de Reparação")) {
										if (e.getCurrentItem().hasItemMeta()) {
											if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
												if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§")) {
													return;
												}
											}
										}
										ch++;
									}
								}
							}
						}
					}
				}
			}
		}
		if (e.getCurrentItem() != null) {
			if (!(e.getCurrentItem().getType() == Material.AIR)) {
				if (e.getCursor() != null) {
					if (!(e.getCursor().getType() == Material.AIR)) {
						if (e.getCurrentItem().getType() == Material.IRON_INGOT) {
							if (e.getCurrentItem().hasItemMeta()) {
								if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
									if (e.getCurrentItem().getItemMeta().getDisplayName()
											.contains("§aMoeda de Reparação")) {
										if (e.getCursor().hasItemMeta()) {
											if (e.getCursor().getItemMeta().hasDisplayName()) {
												if (e.getCursor().getItemMeta().getDisplayName().contains("§")) {
													return;
												}
											}
										}
										ch++;
									}
								}
							}
						}
					}
				}
			}
		}
		if (ch > 1) {
			return;
		}
		if (ch > 0) {
			if (e.getCurrentItem().getType().getMaxDurability() == 0) {
				e.getWhoClicked().sendMessage("§cEste item não pode ser reparado.");
				return;
			}
			if (e.getCurrentItem().getDurability() == 0) {
				e.getWhoClicked().sendMessage("§cEste item não está desgastado.");
				return;
			}
			e.setCancelled(true);
			if (e.getCursor().getAmount() >= 2) {
				e.getCursor().setAmount(e.getCursor().getAmount() - 1);
			} else if (e.getCursor().getAmount() == 1) {
				e.setCursor(new ItemStack(Material.AIR));
			}
			Player p = (Player) e.getWhoClicked();
			e.getCurrentItem().setDurability((short) 0);
			p.playSound(p.getLocation(), Sound.ANVIL_USE, 1.0F, 1.0F);
		}
	}
}
