package com.wandy.api.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.massivecore.ps.PS;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.wandy.api.Main;

public class Proteção implements Listener {
	
	@EventHandler
	public static void aoQueda(EntityDamageEvent e) {
		if ((e.getEntity() instanceof Player)) {
			Player p = (Player) e.getEntity();
			if (e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
				if (getArea(p).equals("spawn")) {
					e.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public static void aoTomar(EntityDamageEvent e) {
		if ((e.getEntity() instanceof Player)) {
			try {
				if (BoardColl.get().getFactionAt(PS.valueOf(e.getEntity().getLocation())).getId().equalsIgnoreCase("safezone")) {
					e.setCancelled(true);
				}
			} catch (NullPointerException localNullPointerException) {
			}
		}
	}

	@EventHandler
	public static void aoExplodir(EntityExplodeEvent e) {
		if (BoardColl.get().getFactionAt(PS.valueOf(e.getEntity().getLocation())).getId().equalsIgnoreCase("safezone")) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public static void aoSpawnar(CreatureSpawnEvent e) {
		if (e.getEntity().getCustomName() != null) {
			if (!e.getEntity().getCustomName().equals("§5Magaiver")) {
				if (BoardColl.get().getFactionAt(PS.valueOf(e.getEntity().getLocation())).getId().equalsIgnoreCase("safezone")) {
					e.setCancelled(true);
				}
			}
		}
	}

	public static String getArea(Player p) {
		String name = "";
		RegionManager regionManager = Main.getWorldGuard().getRegionManager(p.getWorld());
		ApplicableRegionSet set = regionManager.getApplicableRegions(p.getLocation());
		if (set.size() != 0) {
			set.toString().toLowerCase();
			String id = ((ProtectedRegion) set.iterator().next()).getId();
			ProtectedRegion region = regionManager.getRegion(id);
			name = region.getId();
		}
		return name;
	}

	public static String getArea(Location loc) {
		String name = "";
		RegionManager regionManager = Main.getWorldGuard().getRegionManager(loc.getWorld());
		ApplicableRegionSet set = regionManager.getApplicableRegions(loc);
		if (set.size() != 0) {
			set.toString().toLowerCase();
			String id = ((ProtectedRegion) set.iterator().next()).getId();
			ProtectedRegion region = regionManager.getRegion(id);
			name = region.getId();
		}
		return name;
	}
}
