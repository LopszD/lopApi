package com.wandy.api.especiais.listeners;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.massivecore.ps.PS;
import com.wandy.api.Main;
import com.wandy.api.utils.Proteção;

public class RaioMestreListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public static void aoColocar(PlayerInteractAtEntityEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand() == null) {
			return;
		}
		if (p.getItemInHand().getType() == Material.AIR) {
			return;
		}
		if (p.getItemInHand().getType() == Material.BLAZE_ROD) {
			if (p.getItemInHand().getItemMeta().hasDisplayName()) {
				if (p.getItemInHand().getItemMeta().getDisplayName().contains("§6Raio Mestre")) {
					if (e.getRightClicked().getType() == EntityType.CREEPER) {
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
						e.setCancelled(true);
						if (p.getItemInHand().getAmount() >= 2) {
							p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
						} else if (p.getItemInHand().getAmount() == 1) {
							p.setItemInHand(new ItemStack(Material.AIR));
						}
						Creeper c = (Creeper) e.getRightClicked();
						c.setPowered(true);
						c.setMetadata("SUPER", new FixedMetadataValue(Main.getInstance(), Integer.valueOf(1)));
						Location loc = p.getTargetBlock((Set<Material>) null, 20).getLocation();
						Bukkit.getWorld(p.getWorld().getName()).strikeLightning(loc);
					}
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public static void aoColocar(PlayerInteractEvent e) {
		if (e.getItem() == null) {
			return;
		}
		if (e.getItem().getType() == Material.AIR) {
			return;
		}
		if (e.getItem().getType() == Material.BLAZE_ROD) {
			if (e.getItem().getItemMeta().hasDisplayName()) {
				if (e.getItem().getItemMeta().getDisplayName().contains("§6Raio Mestre")) {
					Player p = e.getPlayer();
					if ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
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
						e.setCancelled(true);
						if (p.getItemInHand().getAmount() >= 2) {
							p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
						} else if (p.getItemInHand().getAmount() == 1) {
							p.setItemInHand(new ItemStack(Material.AIR));
						}
						Location loc = p.getTargetBlock((Set<Material>) null, 20).getLocation();
						Bukkit.getWorld(p.getWorld().getName()).strikeLightning(loc);
					}
				}
			}
		}
	}
}
