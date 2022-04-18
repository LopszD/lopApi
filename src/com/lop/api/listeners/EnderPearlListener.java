package com.wandy.api.listeners;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.Plugin;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.massivecore.ps.PS;
import com.wandy.api.Main;
import com.wandy.api.arenas.ArenaMenu;
import com.wandy.api.utils.Proteção;

public class EnderPearlListener implements Listener {
	
	public static HashMap<String, Integer> delayen = new HashMap<String, Integer>();
	public static ArrayList<String> ta = new ArrayList<String>();

	@SuppressWarnings("deprecation")
	@EventHandler
	public static void aoJoga(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand() != null) {
			if (!(p.getItemInHand().getType() == Material.AIR)) {
				if (p.getItemInHand().getType() == Material.ENDER_PEARL) {
					if (!(e.getPlayer().getGameMode() == GameMode.SURVIVAL)) {
						return;
					}
					if (ArenaMenu.areia.containsKey(p.getName())) {
						e.setCancelled(true);
						p.updateInventory();
						return;
					}
					if (ArenaMenu.pedra.containsKey(p.getName())) {
						e.setCancelled(true);
						p.updateInventory();
						return;
					}
					if (ArenaMenu.nether.containsKey(p.getName())) {
						e.setCancelled(true);
						p.updateInventory();
						return;
					}
					if (p.getWorld().getName() == "world_arenas") {
						e.setCancelled(true);
						p.updateInventory();
						return;
					}
					if (Proteção.getArea(p) == "spawn") {
						e.setCancelled(true);
						p.updateInventory();
						return;
					}
					if (BoardColl.get().getFactionAt(PS.valueOf(p.getLocation())).getId().equalsIgnoreCase("safezone")) {
						e.setCancelled(true);
						return;
					}
					if (ta.contains(p.getName())) {
						e.setCancelled(true);
						p.updateInventory();
						return;
					}
					ta.add(p.getName());
					Bukkit.getScheduler().scheduleAsyncDelayedTask((Plugin) Main.plugin, (Runnable) new Runnable() {
						@Override
						public void run() {
							if (p.isOnline() && ta.contains(p.getName())) {
								ta.remove(p.getName());
							}
						}
					}, 60L);
				}
			}
		}
	}

	@EventHandler
	public static void aoJogar(PlayerTeleportEvent e) {
		Player p = e.getPlayer();
		if (CombatLogListener.combate.containsKey(e.getPlayer().getName())) {
			if (!(e.getCause() == PlayerTeleportEvent.TeleportCause.UNKNOWN)) {
				e.setCancelled(true);
				p.sendMessage("§cTeleporte cancelado!");
				return;
			}
		}
		if (e.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
			if (ArenaMenu.areia.containsKey(p.getName())) {
				e.setCancelled(true);
				return;
			}
			if (ArenaMenu.pedra.containsKey(p.getName())) {
				e.setCancelled(true);
				return;
			}
			if (ArenaMenu.nether.containsKey(p.getName())) {
				e.setCancelled(true);
				return;
			}
			if (p.getWorld().getName() == "world_arenas") {
				e.setCancelled(true);
				return;
			}
			if (Proteção.getArea(p) == "spawn") {
				e.setCancelled(true);
				return;
			}
			if (e.getTo().getWorld().getName() == "world") {
				if (eFora(e.getTo(), p)) {
					p.sendMessage("§cVocê não pode ir para fora do limite do mundo.");
					e.setCancelled(true);
					return;
				}
			}
			if (e.getTo().getWorld().getName() == "world_nether") {
				if (e.getTo().getBlockY() >= 127) {
					p.sendMessage("§cVocê não pode ir para fora do limite do mundo.");
					e.setCancelled(true);
					return;
				}
				if (e.getTo().getBlockX() >= 1250) {
					p.sendMessage("§cVocê não pode ir para fora do limite do mundo.");
					e.setCancelled(true);
					return;
				}
				if (e.getTo().getBlockZ() >= 1250) {
					p.sendMessage("§cVocê não pode ir para fora do limite do mundo.");
					e.setCancelled(true);
					return;
				}
				if (e.getTo().getBlockX() <= 64286) {
					p.sendMessage("§cVocê não pode ir para fora do limite do mundo.");
					e.setCancelled(true);
					return;
				}
				if (e.getTo().getBlockZ() <= 64286) {
					p.sendMessage("§cVocê não pode ir para fora do limite do mundo.");
					e.setCancelled(true);
					return;
				}
			}
			e.setCancelled(true);
			Location loc = e.getTo().getBlock().getRelative(e.getTo().getBlock().getFace(e.getTo().getBlock())).getLocation().add(0.5D, 0.0D, 0.5D);
			Location f = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ(), e.getFrom().getYaw(), e.getFrom().getPitch());
			p.teleport(f);
		}
	}

	public static boolean eFora(Location loc, Player p) {
		WorldBorder wb = p.getWorld().getWorldBorder();
		int raio = (int) wb.getSize() / 2;
		int x = loc.getBlockX();
		int z = loc.getBlockZ();
		int cx = raio;
		int cz = raio;
		int cxn = -raio;
		int czn = -raio;
		if (x >= cx) {
			return true;
		}
		if (z >= cz) {
			return true;
		}
		if (x <= cxn) {
			return true;
		}
		if (z <= czn) {
			return true;
		}
		return false;
	}
}
