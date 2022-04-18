package com.wandy.api.especiais.listeners;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

public class RunaExtraçãoListener implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public static void aoMover(InventoryClickEvent e) {
		int ch = 0;
		if (e.getCursor() != null) {
			if (!(e.getCursor().getType() == Material.AIR)) {
				if (e.getCurrentItem() != null) {
					if (!(e.getCurrentItem().getType() == Material.AIR)) {
						if (e.getCursor().getType() == Material.BOOK) {
							if (e.getCursor().hasItemMeta()) {
								if (e.getCursor().getItemMeta().hasDisplayName()) {
									if (e.getCursor().getItemMeta().getDisplayName().contains("§aRuna de Extração")) {
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
						if (e.getCurrentItem().getType() == Material.BOOK) {
							if (e.getCurrentItem().hasItemMeta()) {
								if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
									if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§aRuna de Extração")) {
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
			e.setCancelled(true);
			if (e.getCursor().getAmount() >= 2) {
				e.getCursor().setAmount(e.getCursor().getAmount() - 1);
			} else if (e.getCursor().getAmount() == 1) {
				e.setCursor(new ItemStack(Material.AIR));
			}
			Player p = (Player) e.getWhoClicked();
			for (Enchantment enc : e.getCurrentItem().getEnchantments().keySet()) {
				if (e.getCurrentItem().getEnchantmentLevel(enc) > enc.getMaxLevel()) {
					ItemStack a = new ItemStack(Material.ENCHANTED_BOOK);
					EnchantmentStorageMeta meta = (EnchantmentStorageMeta) a.getItemMeta();
					meta.addStoredEnchant(enc, enc.getMaxLevel(), true);
					a.setItemMeta(meta);
					if (checarInv(p)) {
						p.getInventory().addItem(new ItemStack[] { a });
					} else {
						p.getWorld().dropItemNaturally(p.getLocation(), a);
					}
					int dps = e.getCurrentItem().getEnchantmentLevel(enc) - enc.getMaxLevel();
					e.getCurrentItem().removeEnchantment(enc);
					e.getCurrentItem().addUnsafeEnchantment(enc, dps);
				} else {
					ItemStack a = new ItemStack(Material.ENCHANTED_BOOK);
					EnchantmentStorageMeta meta = (EnchantmentStorageMeta) a.getItemMeta();
					meta.addStoredEnchant(enc, e.getCurrentItem().getEnchantmentLevel(enc), true);
					a.setItemMeta(meta);
					if (checarInv(p)) {
						p.getInventory().addItem(new ItemStack[] { a });
					} else {
						p.getWorld().dropItemNaturally(p.getLocation(), a);
					}
					e.getCurrentItem().removeEnchantment(enc);
				}
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
