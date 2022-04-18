package com.wandy.api.especiais.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.massivecore.ps.PS;
import com.wandy.api.Main;
import com.wandy.api.utils.Proteção;

public class ArmadilhaListener implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void aoLancar(ProjectileLaunchEvent e) {
		if (e.getEntity().getShooter() instanceof Player) {
			Player p = (Player) e.getEntity().getShooter();
				if (e.getEntityType() == EntityType.SNOWBALL && e.getEntityType().getName().toLowerCase().contains("§carmadilha")) {
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
				if (p.getItemInHand().getItemMeta().getDisplayName().toLowerCase().contains("§carmadilha")) {
					return;
				}
			}
		}
	}

	@EventHandler
	public void aoAcertarProjetil(ProjectileHitEvent e) {
		if (e.getEntity() instanceof Snowball){
			if (e.getEntity().hasMetadata("armadilha")) {
			executeTrap(e.getEntity().getLocation());
		}
	}
}

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if (e.getDamager().hasMetadata("armadilha")) {
			e.setCancelled(true);
			return;
		}
	}

//	@EventHandler
//	public void onBreak(BlockBreakEvent e) {
//		if (e.getBlock().getType() == Material.WEB && e.getBlock().hasMetadata("temporary")) {
//			e.setCancelled(true);
//			return;
//		}
//	}

	private void executeTrap(Location l) {
		for (double x = l.getX() - 1; x <= l.getX() + 1; x++) {
			for (double z = l.getZ() - 1; z <= l.getZ() + 1; z++) {
				Block b = new Location(l.getWorld(), x, l.getY(), z).getBlock();
				if (b.getType() == Material.AIR) {
					b.setType(Material.WEB);
				}
			}
		}
		new BukkitRunnable() {
			@Override
			public void run() {
				for (double x = l.getX() - 1; x <= l.getX() + 1; x++) {
					for (double z = l.getZ() - 1; z <= l.getZ() + 1; z++) {
						Block b = new Location(l.getWorld(), x, l.getY(), z).getBlock();
						if (b.getType() == Material.WEB) {
							b.setType(Material.AIR);
						}
					}
				}
			}
		}.runTaskLater(Main.getInstance(), (20L * 20));
	}
}
