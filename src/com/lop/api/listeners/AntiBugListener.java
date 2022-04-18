package com.wandy.api.listeners;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class AntiBugListener implements Listener {
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = false)
	public static void aoQuebrar(BlockBreakEvent e) {
		if (!e.isCancelled()) {
			if (e.getBlock() != null) {
				if (e.getBlock().getType().equals(Material.ENDER_CHEST)) {
					if (e.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
						e.setCancelled(true);
						e.getBlock().setType(Material.AIR);
						e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.ENDER_CHEST));
					}
				}
			}
		}
	}

	@EventHandler
	public static void aoClicar(InventoryClickEvent e) {
		if (e.getInventory().getType().equals(InventoryType.ANVIL)) {
			if (e.getClick().equals(ClickType.NUMBER_KEY)) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public static void aoDis(BlockDispenseEvent e) {
		if (e.getItem() != null) {
			if (!e.getItem().getType().equals(Material.AIR)) {
				if (e.getItem().getType().equals(Material.LAVA_BUCKET)) {
					e.setCancelled(true);
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = false)
	public static void onBlockPlace(BlockPlaceEvent event) {
		if ((!event.canBuild()) || (event.isCancelled())) {
			if (event.getBlock().getLocation().getBlockY() < event.getPlayer().getLocation().getY()) {
				if (event.getBlock().getLocation().getBlockX() == event.getPlayer().getLocation().getBlockX()) {
					if (event.getBlock().getLocation().getBlockZ() == event.getPlayer().getLocation().getBlockZ()) {
						if (!event.getPlayer().isFlying()) {
							Location loc = new Location(event.getBlock().getWorld(), event.getBlock().getLocation().getX() + 0.5D, event.getBlock().getLocation().getY(), event.getBlock().getLocation().getZ() + 0.5D, event.getPlayer().getLocation().getYaw(), event.getPlayer().getLocation().getPitch()); event.getPlayer().teleport(loc);
						}
					}
				}
			}
		}
	}
}
