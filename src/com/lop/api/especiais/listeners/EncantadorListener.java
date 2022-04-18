package com.wandy.api.especiais.listeners;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class EncantadorListener implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST)
	public static void aoMover(InventoryClickEvent e) {
		int ch = 0;
		if (e.getCursor() != null) {
			if (!e.getCursor().getType().equals(Material.AIR)) {
				if (e.getCurrentItem() != null) {
					if (!e.getCurrentItem().getType().equals(Material.AIR)) {
						if (e.getCursor().getType().equals(Material.EYE_OF_ENDER)) {
							if (e.getCursor().hasItemMeta()) {
								if (e.getCursor().getItemMeta().hasDisplayName()) {
									if (e.getCursor().getItemMeta().getDisplayName().contains("§aEncantador")) {
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
			if (!e.getCurrentItem().getType().equals(Material.AIR)) {
				if (e.getCursor() != null) {
					if (!e.getCursor().getType().equals(Material.AIR)) {
						if (e.getCurrentItem().getType().equals(Material.EYE_OF_ENDER)) {
							if (e.getCurrentItem().hasItemMeta()) {
								if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
									if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§aEncantador")) {
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
			if (e.getCurrentItem().getEnchantments().size() == 0) {
				e.getWhoClicked().sendMessage("§cEste item não tem encantamentos.");
				return;
			}
			Player p = (Player) e.getWhoClicked();
			int i = 0;
			for (Enchantment enc : e.getCurrentItem().getEnchantments().keySet()) {
				if (e.getCurrentItem().getEnchantmentLevel(enc) < 6) {
					if (enc != Enchantment.WATER_WORKER) {
						i++;
						int af = e.getCurrentItem().getEnchantmentLevel(enc) + 1;
						e.getCurrentItem().removeEnchantment(enc);
						e.getCurrentItem().addUnsafeEnchantment(enc, af);
					}
				}
			}
			if (i == 0) {
				p.sendMessage("§cEste item não tem encantamentos para serem evoluídos.");
				return;
			}
			e.setCancelled(true);
			if (e.getCursor().getAmount() >= 2) {
				e.getCursor().setAmount(e.getCursor().getAmount() - 1);
			} else if (e.getCursor().getAmount() == 1) {
				e.setCursor(new ItemStack(Material.AIR));
			}
			p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 1.0F, 1.0F);
		}
	}

	public static boolean checarInv(Player p) {
		int antes = 35;
		int v = 0;
		ItemStack[] arrayOfItemStack;
		int j = (arrayOfItemStack = p.getInventory().getContents()).length;
		for (int i = 0; i < j; i++) {
			ItemStack itens = arrayOfItemStack[i];
			if (itens != null) {
				if (!(itens.getType() == Material.AIR)) {
					v++;
				}
			}
		}
		if (v <= antes) {
			return true;
		}
		return false;
	}
}
