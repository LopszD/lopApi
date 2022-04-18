package com.wandy.api.caixas.spawners;

import java.util.Arrays;
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
		Inventory inv = Bukkit.createInventory(null, 54, "§8Conteúdo - Caixa Spawners");
		inv.setItem(11, new ItemStack(Material.MONSTER_EGG, 1, (short) 50));
		inv.setItem(12, new ItemStack(Material.MONSTER_EGG, 5, (short) 50));
		inv.setItem(13, new ItemStack(Material.EMERALD_BLOCK, 16));
		inv.setItem(14, new ItemStack(Material.IRON_BLOCK, 16));
		inv.setItem(15, new ItemStack(Material.GOLD_BLOCK, 16));
		inv.setItem(20, spawnerCreate("Cow"));
		inv.setItem(21, spawnerCreate("Spider"));
		inv.setItem(22, new ItemStack(Material.GOLDEN_APPLE, 15, (short) 1));
		inv.setItem(23, new ItemStack(Material.ENDER_STONE, 64));
		inv.setItem(24, new ItemStack(Material.BEDROCK, 64));
		inv.setItem(29, spawnerCreate("Skeleton"));
		inv.setItem(30, spawnerCreate("Zombie"));
		inv.setItem(31, spawnerCreate("Blaze"));
		inv.setItem(32, spawnerCreate("Pig_zombie"));
		inv.setItem(33, spawnerCreate("Iron_golem"));
		p.openInventory(inv);
	}

	public static ItemStack spawnerCreate(String tipo) {
		ItemStack a = new ItemStack(Material.MOB_SPAWNER);
		ItemMeta am = a.getItemMeta();
		am.setDisplayName("§eGerador de Monstros");
		am.setLore(Arrays.asList("§7Tipo: §f" + tipo));
		a.setItemMeta(am);
		return a;
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
		if (!e.getInventory().getName().equalsIgnoreCase("§8Conteúdo - Caixa Spawners")) {
			return;
		}
		e.setCancelled(false);
		if (e.getSlot() == 49) {
			e.setCancelled(true);
		}
		if (e.getCurrentItem() == null) {
			return;
		}
		e.setCancelled(true);
	}
}
