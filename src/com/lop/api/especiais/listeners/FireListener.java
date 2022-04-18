package com.wandy.api.especiais.listeners;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.massivecore.ps.PS;
import com.wandy.api.utils.Proteção;

public class FireListener implements Listener {

	public static HashMap<String, Entity> sc = new HashMap<String, Entity>();

	@EventHandler(priority = EventPriority.HIGHEST)
	public static void aoColocar(PlayerInteractEvent e) {
		if (e.getItem() == null) {
			return;
		}
		if (e.getItem().getType() == Material.AIR) {
			return;
		}
		if (e.getItem().getType() == Material.FIREBALL) {
			if (e.getItem().getItemMeta().hasDisplayName()) {
				if (e.getItem().getItemMeta().getDisplayName().contains("§cBola de fogo")) {
					Player p = e.getPlayer();
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
					if (e.getAction() == Action.RIGHT_CLICK_AIR) {
						e.setCancelled(true);
						if (p.getItemInHand().getAmount() >= 2) {
							p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
						} else if (p.getItemInHand().getAmount() == 1) {
							p.setItemInHand(new ItemStack(Material.AIR));
						}
						Vector direction = p.getEyeLocation().getDirection().multiply(0.5D);
						Projectile projectile = (Projectile) p.getWorld().spawn(p.getEyeLocation().add(direction.getX(), direction.getY(), direction.getZ()), LargeFireball.class);
						projectile.setShooter(p);
						projectile.setVelocity(direction);
					}
					if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
						e.setCancelled(true);
						if (p.getItemInHand().getAmount() >= 2) {
							p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
						} else if (p.getItemInHand().getAmount() == 1) {
							p.setItemInHand(new ItemStack(Material.AIR));
						}
						Vector direction = p.getEyeLocation().getDirection().multiply(0.5D);
						Projectile projectile = (Projectile) p.getWorld().spawn(p.getEyeLocation().add(direction.getX(), direction.getY(), direction.getZ()), LargeFireball.class);
						projectile.setShooter(p);
						projectile.setVelocity(direction);
					}
				}
			}
		}
	}
}
