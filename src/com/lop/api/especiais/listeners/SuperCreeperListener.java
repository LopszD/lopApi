package com.wandy.api.especiais.listeners;

import java.util.HashMap;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.SpawnEgg;
import org.bukkit.metadata.FixedMetadataValue;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.massivecore.ps.PS;
import com.wandy.api.Main;
import com.wandy.api.utils.Proteção;

public class SuperCreeperListener implements Listener {
	
	public static HashMap<String, Entity> sc = new HashMap<String, Entity>();

	@EventHandler
	public static void aoSpawnar(CreatureSpawnEvent e) {
		if (e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SPAWNER_EGG) {
			if (e.getEntity().getType() == EntityType.CREEPER) {
				if (BoardColl.get().getFactionAt(PS.valueOf(e.getEntity().getLocation())).getId().equalsIgnoreCase("safezone")) {
					e.setCancelled(true);
					return;
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST)
	public static void aoColocar(PlayerInteractEvent e) {
		if (e.getItem() == null) {
			return;
		}
		if (e.getItem().getType() == Material.AIR) {
			return;
		}
		if (e.getItem().getType() == Material.MONSTER_EGG) {
			if (e.getItem().getItemMeta().hasDisplayName()) {
				if (e.getItem().getItemMeta().getDisplayName().contains("§aSuper Creeper")) {
					Player p = e.getPlayer();
					if (!e.isCancelled()) {
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
						Location loc = e.getClickedBlock().getRelative(e.getBlockFace()).getLocation().add(0.5D, 0.0D, 0.5D);
						Creeper c = (Creeper) loc.getWorld().spawnEntity(loc, EntityType.CREEPER);
						c.setPowered(true);
						c.setMetadata("SUPER", new FixedMetadataValue(Main.getInstance(), Integer.valueOf(1)));
					}
					return;
				}
			}
			if (e.getClickedBlock() != null) {
				if (e.getClickedBlock().getType() == Material.MOB_SPAWNER) {
					e.setCancelled(true);
					Player p = e.getPlayer();
					SpawnEgg egg = new SpawnEgg(p.getItemInHand().getData().getData());
					if (!(p.getGameMode() == GameMode.CREATIVE)) {
						if (p.getItemInHand().getAmount() >= 2) {
							p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
						} else if (p.getItemInHand().getAmount() == 1) {
							p.setItemInHand(new ItemStack(Material.AIR));
						}
					}
					Location loc = e.getClickedBlock().getRelative(e.getBlockFace()).getLocation().add(0.5D, 0.0D, 0.5D);
					loc.getWorld().spawnEntity(loc, egg.getSpawnedType());
				}
			}
		}
	}
}
