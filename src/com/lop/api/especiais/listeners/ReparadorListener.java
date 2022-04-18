package com.wandy.api.especiais.listeners;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ReparadorListener implements Listener {
	
	@EventHandler
	public void aoClicar(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getItem() == null || e.getItem().getType() == Material.AIR) return;
			ItemStack item = e.getItem();
			if (item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().contains("§eReparador Instantâneo")) {
				repairArmor(e.getPlayer());
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

	private void repairArmor(Player p) {
		ItemStack[] armadura = p.getInventory().getArmorContents();
		for (ItemStack i : armadura) {
			if (i != null && i.getType().getMaxDurability() != 0) {
				i.setDurability((short) 0);
			}
		}
		p.updateInventory();
		p.playSound(p.getLocation(), Sound.ANVIL_USE, 1, 1);
	}
}
