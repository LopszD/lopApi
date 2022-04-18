package com.wandy.api.especiais.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.massivecore.ps.PS;
import com.wandy.api.utils.ItemBuilder;
import com.wandy.api.utils.Proteção;

public class DispenserListener implements Listener {

	@EventHandler
	void aoColocarTNT(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if (e.getBlock().getType() == Material.DISPENSER) {
			ItemStack itemStack = p.getItemInHand();
			if (itemStack == null || itemStack.getType() == Material.AIR)
				return;
			if (itemStack.getItemMeta().hasDisplayName()) {
				if (itemStack.getItemMeta().getDisplayName().startsWith("§cDispenser de TNT")) {
					if (BoardColl.get().getFactionAt(PS.valueOf(p.getLocation())).getId().equalsIgnoreCase("safezone")) {
						e.setCancelled(true);
						return;
					}
					if (BoardColl.get().getFactionAt(PS.valueOf(p.getLocation())).getId().equalsIgnoreCase("warzone")) {
						e.setCancelled(true);
						return;
					}
					if (Proteção.getArea(p).equals("spawn")) {
						e.setCancelled(true);
						return;
					}
					if (Proteção.getArea(p).equals("__global__")) {
						e.setCancelled(true);
						return;
					}
					if (p.getWorld().getName().equals("vip")) {
						e.setCancelled(true);
						return;
					}
					if (p.getWorld().getName().equals("minas")) {
						e.setCancelled(true);
						return;
					}
					if (p.getWorld().getName().equals("world_arenas")) {
						e.setCancelled(true);
						return;
					}
					InventoryHolder dispenserhold = (InventoryHolder) e.getBlock().getState();
					Inventory dispenser = dispenserhold.getInventory();
					dispenser.addItem(new ItemBuilder(Material.TNT).amount(64).build());
					dispenser.addItem(new ItemBuilder(Material.TNT).amount(64).build());
					dispenser.addItem(new ItemBuilder(Material.TNT).amount(64).build());
					dispenser.addItem(new ItemBuilder(Material.TNT).amount(64).build());
					dispenser.addItem(new ItemBuilder(Material.TNT).amount(64).build());
					dispenser.addItem(new ItemBuilder(Material.TNT).amount(64).build());
					dispenser.addItem(new ItemBuilder(Material.TNT).amount(64).build());
					dispenser.addItem(new ItemBuilder(Material.TNT).amount(64).build());
					dispenser.addItem(new ItemBuilder(Material.TNT).amount(64).build());
				}
			}
		}
	}

	@EventHandler
	void aoColocarImpulsao(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if (e.getBlock().getType() == Material.DISPENSER) {
			ItemStack itemStack = p.getItemInHand();
			if (itemStack == null || itemStack.getType() == Material.AIR)
				return;
			if (itemStack.getItemMeta().hasDisplayName()) {
				if (itemStack.getItemMeta().getDisplayName().startsWith("§cDispenser de Impulsão")) {
					if (BoardColl.get().getFactionAt(PS.valueOf(p.getLocation())).getId().equalsIgnoreCase("safezone")) {
						e.setCancelled(true);
						return;
					}
					if (BoardColl.get().getFactionAt(PS.valueOf(p.getLocation())).getId().equalsIgnoreCase("warzone")) {
						e.setCancelled(true);
						return;
					}
					if (Proteção.getArea(p).equals("spawn")) {
						e.setCancelled(true);
						return;
					}
					if (Proteção.getArea(p).equals("__global__")) {
						e.setCancelled(true);
						return;
					}
					if (p.getWorld().getName().equals("vip")) {
						e.setCancelled(true);
						return;
					}
					if (p.getWorld().getName().equals("minas")) {
						e.setCancelled(true);
						return;
					}
					if (p.getWorld().getName().equals("world_arenas")) {
						e.setCancelled(true);
						return;
					}
					InventoryHolder dispenserhold = (InventoryHolder) e.getBlock().getState();
					Inventory dispenser = dispenserhold.getInventory();
					dispenser.addItem(new ItemBuilder(ImpulsaoListener.getItem()).amount(64).build());
					dispenser.addItem(new ItemBuilder(ImpulsaoListener.getItem()).amount(64).build());
					dispenser.addItem(new ItemBuilder(ImpulsaoListener.getItem()).amount(64).build());
					dispenser.addItem(new ItemBuilder(ImpulsaoListener.getItem()).amount(64).build());
					dispenser.addItem(new ItemBuilder(ImpulsaoListener.getItem()).amount(64).build());
					dispenser.addItem(new ItemBuilder(ImpulsaoListener.getItem()).amount(64).build());
					dispenser.addItem(new ItemBuilder(ImpulsaoListener.getItem()).amount(64).build());
					dispenser.addItem(new ItemBuilder(ImpulsaoListener.getItem()).amount(64).build());
					dispenser.addItem(new ItemBuilder(ImpulsaoListener.getItem()).amount(64).build());
				}
			}
		}
	}
}