package com.wandy.api.especiais.listeners;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.massivecore.ps.PS;
import com.wandy.api.Main;
import com.wandy.api.utils.Proteção;

public class PurificadorListener implements Listener {

	@EventHandler
	public void onDamage(ProjectileHitEvent e) {
		if (((e.getEntity() instanceof ThrownPotion)) && (e.getEntity().hasMetadata("Purificador"))) {
			for (Entity entity : getEntitiesAroundPoint(e.getEntity().getLocation(), 3.0D)) {
				if ((entity instanceof Player)) {
					Player player = (Player) entity;
					for (PotionEffect a : player.getActivePotionEffects()) {
						player.removePotionEffect(a.getType());
						player.sendMessage("§cOps! Parece que seus efeitos foram removidos por um Purificador!");
					}
				}
			}
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if ((e.hasItem()) && (e.getItem().hasItemMeta()) && (e.getItem().getItemMeta().hasDisplayName()) && (e.getItem().getItemMeta().getDisplayName().contains("§9Purificador"))) {
			e.setCancelled(true);
			if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
				e.getPlayer().updateInventory();
				return;
			}
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
			e.getPlayer().updateInventory();
			((ThrownPotion) e.getPlayer().launchProjectile(ThrownPotion.class)).setMetadata("Purificador", new FixedMetadataValue(Main.getInstance(), Boolean.valueOf(true)));
			if (e.getPlayer().getItemInHand().getAmount() > 1) {
				e.getPlayer().getItemInHand().setAmount(e.getPlayer().getItemInHand().getAmount() - 1);
			} else {
				e.getPlayer().setItemInHand(new ItemStack(Material.AIR));
			}
		}
	}
	
	public static List<Entity> getEntitiesAroundPoint(Location location, double radius) {
		return  (List<Entity>) location.getWorld().getNearbyEntities(location, radius, radius, radius);
	}
}
