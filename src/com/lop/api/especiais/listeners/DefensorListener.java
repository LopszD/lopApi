package com.wandy.api.especiais.listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.wandy.api.Main;

public class DefensorListener implements Listener {

	public static ArrayList<String> lista = new ArrayList<String>();

	@EventHandler
	public void onDrinkPotion(PlayerItemConsumeEvent e) {
		Player p = e.getPlayer();
		if ((e.getItem().hasItemMeta()) && (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aDefensor Secreto"))) {
			if (lista.contains(p.getName())) {
				p.sendMessage("§cVocê não pode usar outro Defensor Secreto enquanto já há um ativo.");
				e.setCancelled(true);
			} else {
				e.setCancelled(true);
				p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
				p.sendMessage("§aDefensor Secreto ativada com sucesso por alguns segundos.");
				for (Entity player : p.getNearbyEntities(20, 20, 20)) {
					if ((player instanceof Player)) {
						if (!player.getName().equals(p.getName())) {
							player.sendMessage("§cUm usuário acabou de usar um Defensor Secreto, cuidado!");
						}
					}
				}
				for (Player todos : Bukkit.getOnlinePlayers()) {
					if (!todos.hasPermission("wandy.vanish")) {
						todos.hidePlayer(p);
					}
					if (todos.hasPermission("wandy.vanish")) {
						todos.showPlayer(p);
					}
				}
				new BukkitRunnable() {
					public void run() {
						for (Player todos : Bukkit.getOnlinePlayers()) {
							todos.showPlayer(p);
						}
						lista.remove(p.getName());
						p.sendMessage("§cSeu tempo de Defensor Secreto acabou!");
					}
				}.runTaskLater(Main.getInstance(), 20 * 20);
				lista.add(p.getName());
			}
		}
	}

	@EventHandler
	public static void aoSair(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (lista.contains(p.getName())) {
			lista.remove(p.getName());
		}
		for (Player todos : Bukkit.getOnlinePlayers()) {
			todos.showPlayer(p);
		}
	}
}
