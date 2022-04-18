package com.wandy.api.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.meta.ItemMeta;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PersonalizarListener implements Listener {
	
	@EventHandler
	public void aoColocar(SignChangeEvent e) {
		Player p = e.getPlayer();
		if (!p.hasPermission("whyze.vip")) {
			return;
		}
		if (e.getLine(0).contains("&")) {
			e.setLine(0, e.getLine(0).replace("&", "§"));
		}
		if (e.getLine(1).contains("&")) {
			e.setLine(1, e.getLine(1).replace("&", "§"));
		}
		if (e.getLine(2).contains("&")) {
			e.setLine(2, e.getLine(2).replace("&", "§"));
		}
		if (e.getLine(3).contains("&")) {
			e.setLine(3, e.getLine(3).replace("&", "§"));
		}
	}
	
	@EventHandler
	public static void aoForjar(InventoryClickEvent e) {
		if (e.getInventory().getType().equals(InventoryType.ANVIL)) {
			if (e.getSlot() == 2) {
				if (e.getCurrentItem() != null) {
					if (!e.getCurrentItem().getType().equals(Material.AIR)) {
						if (e.getWhoClicked().hasPermission("wandy.vip")) {
							@SuppressWarnings("deprecation")
							String cor = "§" + PermissionsEx.getUser(e.getWhoClicked().getName()).getGroups()[0].getPrefix().charAt(1);
							List<String> lore = new ArrayList<String>();
							if (e.getCurrentItem().hasItemMeta()) {
								if (e.getCurrentItem().getItemMeta().hasLore()) {
									List<String> lis = new ArrayList<String>();
									for (String ni : e.getCurrentItem().getItemMeta().getLore()) {
										if (ni.contains("§1§3§1")) {
											lis.add(ni);
										}
										if (ni.contains("§1§3§2")) {
											lis.add(ni);
										}
										if (ni.contains("§1§3§3")) {
											lis.add(ni);
										}
									}
									ItemMeta am = e.getCurrentItem().getItemMeta();
									lore = am.getLore();
									for (String la : lis) {
										lore.remove(la);
									}
								}
							}
							ItemMeta am = e.getCurrentItem().getItemMeta();
							lore.add("§1§3§3");
							lore.add("§1§3§1" + cor + "Forjado por:");
							lore.add("§1§3§2" + cor + e.getWhoClicked().getName());
							am.setLore(lore);
							e.getCurrentItem().setItemMeta(am);
						}
					}
				}
			}
		}
	}
}
