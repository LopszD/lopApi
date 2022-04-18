package com.wandy.api.especiais.listeners;

import java.util.HashMap;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class AgrupadorListener implements Listener {

	@EventHandler
	public void aoClicar(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getItem() == null || e.getItem().getType() == Material.AIR) return;
			ItemStack item = e.getItem();
			if (item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().contains("§dAgrupador de Poções")) {
				agroupPotions(e.getPlayer());
				removeItem(e.getPlayer());
				e.setCancelled(true);
				return;
			}
		}
	}

	private void removeItem(Player p) {
		if (p.getItemInHand().getAmount() < 2) {
			p.setItemInHand(new ItemStack(Material.AIR));
		} else {
			ItemStack item = p.getItemInHand();
			item.setAmount(item.getAmount() - 1);
		}
	}

	@SuppressWarnings("deprecation")
	private void agroupPotions(Player p) {
		PlayerInventory inv = p.getInventory();
		HashMap<ItemStack, Integer> potions = new HashMap<>();
		for (ItemStack item : inv.getContents()) {
			if (item != null && item.getType() == Material.POTION) {
				int amount = item.getAmount();
				item.setAmount(1);
				if (potions.containsKey(item)) {
					amount += potions.get(item);
					potions.replace(item, amount);
				} else {
					potions.put(item, amount);
				}
				inv.removeItem(item);
			}
		}
		for (ItemStack potion : potions.keySet()) {
			int amount = potions.get(potion);
			while (amount > 16) {
				potion.setAmount(16);
				inv.addItem(potion);
				amount -= 16;
			}
			potion.setAmount(amount);
			inv.addItem(potion);
		}
		p.playEffect(p.getLocation(), Effect.POTION_BREAK, 0);
	}
}
