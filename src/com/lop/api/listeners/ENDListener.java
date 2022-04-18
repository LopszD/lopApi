package com.wandy.api.listeners;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.wandy.kits.Main;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class ENDListener implements Listener {

	public static Set<String> teleport = new HashSet<String>();

	@EventHandler
	void event(PlayerChangedWorldEvent e) {
		Player player = e.getPlayer();
		if (player.getLocation().getWorld().getName().equals("world_the_end")) {
			if (player.hasPotionEffect(PotionEffectType.JUMP)) {
				player.removePotionEffect(PotionEffectType.JUMP);
			}
			player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 90000, 2));
		}
		if (e.getFrom().getName().equals("world_the_end")) {
			player.removePotionEffect(PotionEffectType.JUMP);
		}
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Location from = event.getFrom();
		Player player = event.getPlayer();
		if ((from.getBlockY() < -8) && (!player.isFlying())) {
			player.teleport(player.getWorld().getSpawnLocation());
			player.setFallDistance(0.0F);
		}
	}
	
	@EventHandler(ignoreCancelled = true)
	public void Fall(EntityDamageEvent e) {
		if (((e.getEntity() instanceof Player)) && (e.getCause() == EntityDamageEvent.DamageCause.FALL)) {
			Player p = (Player) e.getEntity();
			if (teleport.contains(p.getName())) {
				e.setCancelled(true);
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.NORMAL)
	public void event(PlayerTeleportEvent e) {
		if (e.isCancelled()) {
			return;
		}
		Player player = e.getPlayer();
		Location from = e.getFrom();
		if ((from.getWorld().getEnvironment() == World.Environment.THE_END) && (e.getTo().getWorld().getEnvironment() == World.Environment.THE_END) && (e.getFrom().getBlockY() < -8) && (!player.getAllowFlight())) {
			if (teleport.contains(player.getName())) {
				return;
			}
			teleport.add(player.getName());
			Random r = new Random();
			int n = 50;
			int f = 18;
			PermissionUser user = PermissionsEx.getUser(e.getPlayer().getName());
			String group = user.getGroups()[0].getName();
			switch (group) {
			case "ajudante":
				n = 10;
				f = 7;
				break;
			case "youtuber":
				n = 10;
				f = 7;
				break;
			case "minituber":
				n = 10;
				f = 7;
				break;
			case "wandy":
				n = 10;
				f = 7;
				break;
			case "phunish":
				n = 20;
				f = 4;
				break;
			case "ghuenon":
				n = 30;
				break;
			case "betther":
				n = 40;
				f = 12;
				break;
			default:
				n = 50;
				f = 18;
			}
			for (int i = 0; i < 2; i++) {
				int ra = r.nextInt(4);
				if (ra <= 1) {
					player.getInventory().setHelmet(null);
				}
				if (ra == 2) {
					player.getInventory().setChestplate(null);
				}
				if (ra == 3) {
					player.getInventory().setLeggings(null);
				}
				if (ra == 4) {
					player.getInventory().setBoots(null);
				}
			}
			for (int i = 0; i < f; i++) {
				try {
					player.getInventory().removeItem(player.getInventory().getItem(r.nextInt(34)));
				} catch (Exception localException1) {
				}
			}
			player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 1.0F, 1.0F);
			player.sendMessage("§eVocê caiu no void e perdeu " + n + "% de seus itens!");
			CombatLogListener.tirarCombate(player);
			new BukkitRunnable() {
				public void run() {
					teleport.remove(player.getName());
				}
			}.runTaskLaterAsynchronously(Main.getInstance(), 50L);
			return;
		}
	}
}
