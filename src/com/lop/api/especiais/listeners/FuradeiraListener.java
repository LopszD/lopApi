package com.wandy.api.especiais.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.massivecore.ps.PS;
import com.wandy.api.utils.Proteção;

public class FuradeiraListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public static void aoColocar(PlayerInteractEvent e) {
		if (e.getItem() == null) {
			return;
		}
		if (e.getItem().getType() == Material.AIR) {
			return;
		}
		if (e.getItem().getType() == Material.STICK) {
			if (e.getItem().getItemMeta().hasDisplayName()) {
				if (e.getItem().getItemMeta().getDisplayName().contains("§eFuradeira")) {
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
					if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
						e.setCancelled(true);
						if (e.getClickedBlock().getType() == Material.BEDROCK) {
							if (e.getClickedBlock().getLocation().getBlockY() <= 1) {
								return;
							}
							if (p.getItemInHand().getAmount() >= 2) {
								p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
							} else if (p.getItemInHand().getAmount() == 1) {
								p.setItemInHand(new ItemStack(Material.AIR));
							}
							e.getClickedBlock().setType(Material.AIR);
						}
						if (e.getClickedBlock().getType() == Material.ENDER_STONE) {
							if (p.getItemInHand().getAmount() >= 2) {
								p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
							} else if (p.getItemInHand().getAmount() == 1) {
								p.setItemInHand(new ItemStack(Material.AIR));
							}
							e.getClickedBlock().setType(Material.AIR);
						}
						if (e.getClickedBlock().getType() == Material.OBSIDIAN) {
							if (p.getItemInHand().getAmount() >= 2) {
								p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
							} else if (p.getItemInHand().getAmount() == 1) {
								p.setItemInHand(new ItemStack(Material.AIR));
							}
							e.getClickedBlock().setType(Material.AIR);
						}
						if (e.getClickedBlock().getType() == Material.GLASS) {
							if (p.getItemInHand().getAmount() >= 2) {
								p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
							} else if (p.getItemInHand().getAmount() == 1) {
								p.setItemInHand(new ItemStack(Material.AIR));
							}
							e.getClickedBlock().setType(Material.AIR);
						}
						if (e.getClickedBlock().getType() == Material.STAINED_GLASS) {
							if (p.getItemInHand().getAmount() >= 2) {
								p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
							} else if (p.getItemInHand().getAmount() == 1) {
								p.setItemInHand(new ItemStack(Material.AIR));
							}
							e.getClickedBlock().setType(Material.AIR);
						}
					}
				}
			}
		}
	}
}
