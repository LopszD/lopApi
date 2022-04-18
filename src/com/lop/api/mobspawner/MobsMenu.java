package com.wandy.api.mobspawner;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import com.wandy.api.caixas.spawners.Conteúdo;

public class MobsMenu implements Listener {
	
	public static void abrirMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 45, "§8Geradores");
	    inv.setItem(10, Conteúdo.spawnerCreate("Spider"));
	    inv.setItem(11, Conteúdo.spawnerCreate("Blaze"));
	    inv.setItem(12, Conteúdo.spawnerCreate("Skeleton"));
	    inv.setItem(13, Conteúdo.spawnerCreate("Iron_golem"));
	    inv.setItem(14, Conteúdo.spawnerCreate("Pig"));
	    inv.setItem(15, Conteúdo.spawnerCreate("Pig_zombie"));
	    inv.setItem(16, Conteúdo.spawnerCreate("Chicken"));
	    inv.setItem(19, Conteúdo.spawnerCreate("Sheep"));
	    inv.setItem(20, Conteúdo.spawnerCreate("Cow"));
	    inv.setItem(21, Conteúdo.spawnerCreate("Creeper"));
	    inv.setItem(22, Conteúdo.spawnerCreate("Zombie"));
	    inv.setItem(23, Conteúdo.spawnerCreate("Slime"));
	    inv.setItem(24, Conteúdo.spawnerCreate("Enderman"));
	    inv.setItem(25, Conteúdo.spawnerCreate("Cave_spider"));
	    inv.setItem(28, Conteúdo.spawnerCreate("Wither"));
	    inv.setItem(29, Conteúdo.spawnerCreate("Ghast"));
	    inv.setItem(30, Conteúdo.spawnerCreate("Witch"));
	    inv.setItem(31, Conteúdo.spawnerCreate("Magma"));
	    inv.setItem(32, Conteúdo.spawnerCreate("Silverfish"));
		p.openInventory(inv);
	}

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e) {
		if (!e.getInventory().getName().contains("§8Geradores")) {
			return;
		}
		e.setCancelled(false);
		if (e.getCurrentItem() == null) {
			return;
		}
		Player p = (Player) e.getWhoClicked();
		if (e.getSlot() >= 10) {
			if (e.getSlot() <= 32) {
				if (!(e.getCurrentItem().getType() == Material.AIR)) {
					p.getInventory().addItem(e.getCurrentItem());
				}
			}
		}
		e.setCancelled(true);
	}
}
