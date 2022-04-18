package com.wandy.api.especiais.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.massivecore.ps.PS;
import com.wandy.api.Main;
import com.wandy.api.utils.Proteção;

public class CreeperListener implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.hasItem() && e.getPlayer().getItemInHand().hasItemMeta() && e.getPlayer().getItemInHand().getItemMeta().hasDisplayName() && e.getPlayer().getItemInHand().getItemMeta().getDisplayName().toLowerCase().contains("§acreeper eletrizado") && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
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
			if (e.isCancelled())
				return;
			if (!e.isCancelled()) {
				e.setUseItemInHand(Result.DENY);
				ItemStack item = e.getPlayer().getItemInHand();
				if (item.getAmount() > 1) {
					item.setAmount(item.getAmount() - 1);
					p.updateInventory();
				} else {
					p.setItemInHand(null);
					p.updateInventory();
				}
				Location loc = e.getClickedBlock().getLocation();
				Creeper creeper = (Creeper) loc.getWorld().spawnEntity(getLoc(loc, e.getBlockFace()), EntityType.CREEPER);
				creeper.setPowered(true);
				creeper.setMetadata("creeper", new FixedMetadataValue(Main.getInstance(), true));
			}
		}
	}

	public Location getLoc(Location loc, BlockFace face) {
		Location loc2 = loc;
		if (face == BlockFace.UP) {
			return loc2.clone().add(0.5, 1.0, 0.5);
		} else if (face == BlockFace.DOWN) {
			return loc2.clone().add(0.5, -1.0, 0.5);
		} else if (face == BlockFace.NORTH) {
			return loc2.clone().add(0.5, 0.0, -0.5);
		} else if (face == BlockFace.EAST) {
			return loc2.clone().add(1.5, 0.0, 0.5);
		} else if (face == BlockFace.SOUTH) {
			return loc2.clone().add(0.5, 0.0, 1.5);
		} else if (face == BlockFace.WEST) {
			return loc2.clone().add(-0.5, 0.0, 0.5);
		}
		return loc2;
	}

	@EventHandler
	public void onExplode(ExplosionPrimeEvent e) {
		if (!e.isCancelled()) {
			if (e.getEntity() == null)
				return;
			if (e.getEntity() != null) {
				if (e.getEntity().hasMetadata("creeper")) {
					e.setRadius(e.getRadius() * 2.0F);
				}
			}
		}
	}
}
