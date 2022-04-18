package com.wandy.api.especiais.listeners;

import java.util.ArrayList;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.wandy.api.Main;

public class FoodPotionListener implements Listener {

	public static ArrayList<String> lista = new ArrayList<String>();

	@EventHandler
	public void onfoodchange(FoodLevelChangeEvent e) {
		Entity p = e.getEntity();
		if (((p instanceof Player)) && (lista.contains(p.getName()))) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onDrinkPotion(PlayerItemConsumeEvent e) {
		Player p = e.getPlayer();
		if ((e.getItem().hasItemMeta()) && (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aPoção Contra Fome"))) {
			if (lista.contains(p.getName())) {
				p.sendMessage("§cVocê não pode beber outra poção enquanto já há uma ativa.");
				e.setCancelled(true);
			} else {
				e.setCancelled(true);
				p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
				p.sendMessage("§aPoção Contra fome foi ativada por 30 minutos.");
				new BukkitRunnable() {
					public void run() {
						lista.remove(p.getName());
					}
				}.runTaskLater(Main.getInstance(), 36000L);
				lista.add(p.getName());
			}
		}
	}
}
