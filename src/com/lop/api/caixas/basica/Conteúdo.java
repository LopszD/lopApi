package com.wandy.api.caixas.basica;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Conteúdo implements Listener {
	
	public static void confirmarAbrir(Player p) {
		Inventory inv = Bukkit.createInventory(null, 54, "§8Conteúdo - Caixa Básica");
		int slot = 10;
		for (ItemStack got : ItensCaixas.getLista()) {
			if ((slot == 17) || (slot == 18)) {
				slot = 19;
			}
			if ((slot == 26) || (slot == 27) || (slot == 27)) {
				slot = 28;
			}
			if ((slot == 35) || (slot == 36)) {
				slot = 37;
			}
			inv.setItem(slot, got);
			slot++;
		}
		p.openInventory(inv);
	}

	@SuppressWarnings("unlikely-arg-type")
	public static ItemStack itemCreate(String nome, Material m, int qnt, short damage, List<String> lore) {
		ItemStack a = new ItemStack(m, qnt, damage);
		ItemMeta am = a.getItemMeta();
		am.setDisplayName(nome);
		if (!lore.equals("")) {
			am.setLore(lore);
		}
		a.setItemMeta(am);
		return a;
	}

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e) {
		if (e.getInventory().getName().equalsIgnoreCase("§8Conteúdo - Caixa Básica")) {
			e.setCancelled(false);
			if (e.getCurrentItem() == null) {
				return;
			}
			e.setCancelled(true);
			if (e.getSlot() == 49) {
				e.setCancelled(true);
			}
		}
	}
}
