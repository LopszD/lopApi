package com.wandy.api.cash;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import com.wandy.api.utils.ItemBuilder;

public class CashMenu implements Listener {

	public void abrirMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 3 * 9, "§8Recompensa diária");

		inv.setItem(12, new ItemBuilder(Material.DIAMOND_BLOCK).setName("§bWandy")
				.setLore("§7Clique para coletar §f3.000 §7Cash.", "", "§7Exclusivo para §bWandy§7.").build());

		inv.setItem(13, new ItemBuilder(Material.REDSTONE_BLOCK).setName("§4Phunish")
				.setLore("§7Clique para coletar §f2.500 §7Cash.", "", "§7Exclusivo para §4Phunish§7.").build());

		inv.setItem(14, new ItemBuilder(Material.EMERALD_BLOCK).setName("§2Ghuenon")
				.setLore("§7Clique para coletar §f2.000 §7Cash.", "", "§7Exclusivo para §2Ghuenon§7.").build());

		inv.setItem(15, new ItemBuilder(Material.LAPIS_BLOCK).setName("§9Betther")
				.setLore("§7Clique para coletar §f1.500 §7Cash.", "", "§7Exclusivo para §9Betther§7.").build());

		inv.setItem(16, new ItemBuilder(Material.IRON_BLOCK).setName("§3Glatiz")
				.setLore("§7Clique para coletar §f1.000 §7Cash.", "", "§7Exclusivo para §3Glatiz§7.").build());
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (!e.getInventory().getName().equals("§8Recompensa diária")) {
			return;
		}
		e.setCancelled(true);
		e.setResult(Result.DENY);
		if (e.getCurrentItem() == null) {
			return;
		}
		if (e.getSlot() == 12) {
			e.setCancelled(true);
			e.getWhoClicked().sendMessage("§aVocê coletou seu Cash diário com sucesso.");
		}
	}

	public void criarMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 27, "§8On-lines");
		inv.setItem(12,
				new ItemBuilder(Material.SKULL_ITEM)
						.setName("§aServidor tem §f" + Bukkit.getOnlinePlayers().size() + " §ausuários jogando")
						.durability(1).setSkullOwner("yLeooSz").amount(Bukkit.getOnlinePlayers().size()).build());
	}

	@EventHandler
	public void onclick(InventoryClickEvent e) {
		if (!(e.getInventory().getName() == "§8On-lines")) {
			return;
		}
		if (e.getCurrentItem() == null) {
			return;
		}
		e.setCancelled(true);
		e.setResult(Result.DENY);
	}
}
