package com.wandy.api.mobspawner;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import com.wandy.api.caixas.spawners.Conte�do;

public class MobsMenu implements Listener {
	
	public static void abrirMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 45, "�8Geradores");
	    inv.setItem(10, Conte�do.spawnerCreate("Spider"));
	    inv.setItem(11, Conte�do.spawnerCreate("Blaze"));
	    inv.setItem(12, Conte�do.spawnerCreate("Skeleton"));
	    inv.setItem(13, Conte�do.spawnerCreate("Iron_golem"));
	    inv.setItem(14, Conte�do.spawnerCreate("Pig"));
	    inv.setItem(15, Conte�do.spawnerCreate("Pig_zombie"));
	    inv.setItem(16, Conte�do.spawnerCreate("Chicken"));
	    inv.setItem(19, Conte�do.spawnerCreate("Sheep"));
	    inv.setItem(20, Conte�do.spawnerCreate("Cow"));
	    inv.setItem(21, Conte�do.spawnerCreate("Creeper"));
	    inv.setItem(22, Conte�do.spawnerCreate("Zombie"));
	    inv.setItem(23, Conte�do.spawnerCreate("Slime"));
	    inv.setItem(24, Conte�do.spawnerCreate("Enderman"));
	    inv.setItem(25, Conte�do.spawnerCreate("Cave_spider"));
	    inv.setItem(28, Conte�do.spawnerCreate("Wither"));
	    inv.setItem(29, Conte�do.spawnerCreate("Ghast"));
	    inv.setItem(30, Conte�do.spawnerCreate("Witch"));
	    inv.setItem(31, Conte�do.spawnerCreate("Magma"));
	    inv.setItem(32, Conte�do.spawnerCreate("Silverfish"));
		p.openInventory(inv);
	}

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e) {
		if (!e.getInventory().getName().contains("�8Geradores")) {
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
