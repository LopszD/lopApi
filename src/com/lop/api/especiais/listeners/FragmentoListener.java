package com.wandy.api.especiais.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class FragmentoListener implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if ((e.hasItem()) && (e.getItem().hasItemMeta()) && (e.getItem().getItemMeta().hasDisplayName()) && (e.getItem().getItemMeta().getDisplayName().contains("§dFragmento"))) {
			e.setCancelled(true);
			Player p = e.getPlayer();
			if (e.getItem().getAmount() == 4) {
				e.getPlayer().setItemInHand(new ItemStack(Material.AIR));
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "poder m " + e.getPlayer().getName());
				p.sendMessage("§aSeus fragmentos foram transformado em poder máximo.");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
				return;
			}
			if (e.getItem().getAmount() > 4) {
				e.getPlayer().getItemInHand().setAmount(e.getItem().getAmount() - 4);
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "poder m " + e.getPlayer().getName());
				p.sendMessage("§aSeus fragmentos foram transformado em poder máximo.");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
				return;
			}
			p.sendMessage("§cVocê precisa de 4 fragmentos para transformá-los em um poder máximo.");
			return;
		}
	}
}
