package com.wandy.api.listeners;

import java.util.Random;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import com.wandy.api.Main;
import com.wandy.api.commands.FlyCommand;
import com.wandy.api.sql.Manager;

public class WorldVIPListener implements Listener {

	@EventHandler
	public static void aoCair(EntityDamageEvent e) {
		if ((e.getEntity() instanceof Player)) {
			if (e.getCause().equals(EntityDamageEvent.DamageCause.VOID)) {
				if (e.getEntity().getWorld().getName().equals("world")) {
					e.setCancelled(true);
					Player p = (Player) e.getEntity();
					if (Manager.checarLocExiste("SPAWN")) {
						Random r = new Random();
						int rn = r.nextInt(4);
						if (rn == 0) {
							Location loc = Manager.pegarLocation("SPAWN").clone().add(0.0D, 0.0D, 2.0D);
							p.teleport(loc);
							return;
						}
						if (rn == 1) {
							Location loc = Manager.pegarLocation("SPAWN").clone().subtract(2.0D, 0.0D, 0.0D);
							p.teleport(loc);
							return;
						}
						if (rn == 2) {
							Location loc = Manager.pegarLocation("SPAWN").clone().subtract(0.0D, 0.0D, 2.0D);
							p.teleport(loc);
							return;
						}
						if (rn == 3) {
							Location loc = Manager.pegarLocation("SPAWN").clone().add(2.0D, 0.0D, 0.0D);
							p.teleport(loc);
							return;
						}
						if (rn == 4) {
							Location loc = Manager.pegarLocation("SPAWN").clone().add(0.0D, 0.0D, 2.0D);
							p.teleport(loc);
							return;
						}
					}
				}
				if (e.getEntity().getWorld().getName().equals("vip")) {
					e.setCancelled(true);
					Player p = (Player) e.getEntity();
					if (Manager.checarLocExiste("VIP")) {
						Random r = new Random();
						int rn = r.nextInt(4);
						if (rn == 0) {
							Location loc = Manager.pegarLocation("VIP").clone().add(0.0D, 0.0D, Main.spawn.intValue());
							p.teleport(loc);
							return;
						}
						if (rn == 1) {
							Location loc = Manager.pegarLocation("VIP").clone().subtract(Main.spawn.intValue(), 0.0D, 0.0D);
							p.teleport(loc);
							return;
						}
						if (rn == 2) {
							Location loc = Manager.pegarLocation("VIP").clone().subtract(0.0D, 0.0D, Main.spawn.intValue());
							p.teleport(loc);
							return;
						}
						if (rn == 3) {
							Location loc = Manager.pegarLocation("VIP").clone().add(Main.spawn.intValue(), 0.0D, 0.0D);
							p.teleport(loc);
							return;
						}
						if (rn == 4) {
							Location loc = Manager.pegarLocation("VIP").clone().add(0.0D, 0.0D, Main.spawn.intValue());
							p.teleport(loc);
							return;
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	void event(BlockPlaceEvent e) {
		if ((e.getBlock().getWorld().getName().equalsIgnoreCase("vip")) && (e.getBlock().getType().equals(Material.MOB_SPAWNER))) {
			e.setBuild(false);
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void claim(PlayerCommandPreprocessEvent e) {
		if (((e.getMessage().contains("/f proteger")) || (e.getMessage().contains("/f dominar")) || (e.getMessage().contains("/f claim"))) && (e.getPlayer().getWorld().getName().equalsIgnoreCase("minas"))) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cVocê não pode executar este comando no mundo VIP.");
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDamageEvent(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player p = (Player) e.getEntity();
		if (((e.getCause() == EntityDamageEvent.DamageCause.FIRE) || (e.getCause() == EntityDamageEvent.DamageCause.FALL) || (e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) || (e.getCause() == EntityDamageEvent.DamageCause.LAVA)) && (p.getWorld().getName().equalsIgnoreCase("vip"))) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onAntiLogDmg(EntityDamageByEntityEvent event) {
		if (((event.getDamager() instanceof Player)) && ((event.getEntity() instanceof Player))) {
			Player damager = (Player) event.getDamager();
			if (damager.getWorld().getName().equalsIgnoreCase("vip")) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public static void aoMundo(PlayerTeleportEvent e) {
		Player p = e.getPlayer();
		if (e.getFrom().getWorld().getName().equals(e.getTo().getWorld().getName())) {
			return;
		}
		if (e.getTo().getWorld().getName().equals("vip")) {
			if (!p.hasPermission("wandy.vip")) {
				e.setCancelled(true);
				p.sendMessage("§cVocê não pode se teleportar para este mundo.");
				return;
			}
			if (!FlyCommand.voando.contains(p.getName())) {
				if (p.getGameMode().equals(GameMode.SURVIVAL)) {
					p.setAllowFlight(true);
					p.setFlying(true);
				}
			}
		}
		if (e.getFrom().getWorld().getName().equals("vip")) {
			if (!FlyCommand.voando.contains(p.getName())) {
				if (p.getGameMode().equals(GameMode.SURVIVAL)) {
					p.setAllowFlight(false);
					p.setFlying(false);
				}
			}
		}
	}
}
