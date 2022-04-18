package com.wandy.api.especiais.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class TotemListener implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public void aoMorrer(PlayerDeathEvent e) {
		for (ItemStack item : e.getDrops()) {
			if (item != null && item.getType().equals(Material.ARMOR_STAND) && item.getItemMeta().getDisplayName().contains("§eTotem da Morte")) {
				e.setKeepInventory(true);
				removeItem(e.getEntity());
				e.getEntity().sendMessage("§aSeus itens foram mantidos pelo Totem da Morte!");
				return;
			}
		}
	}

	private void removeItem(Player p) {
		for (ItemStack item : p.getInventory().getContents()) {
			if (item != null && item.getType().equals(Material.ARMOR_STAND) && item.getItemMeta().getDisplayName().contains("§eTotem da Morte")) {
				if (item.getAmount() < 2) {
					p.getInventory().removeItem(item);
				} else {
					item.setAmount(item.getAmount() - 1);
				}
				return;
			}
		}
	}
}