package com.wandy.api.listeners;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.wandy.api.Main;

public class EnchantsListener implements Listener {

	public static ArrayList<String> cooldown = new ArrayList<String>();
	
	/*@EventHandler
	public void damageoffall(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player p = (Player) e.getEntity();
		if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
			if ((p.getInventory().getBoots() != null) && (p.getInventory().getBoots().hasItemMeta()) && (p.getInventory().getBoots().getItemMeta().hasLore()) && (p.getInventory().getBoots().getItemMeta().getLore().contains("§7Antigravidade I"))) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void ativeeffect(PlayerTeleportEvent e) {
		Player p = e.getPlayer();
		if ((p.getInventory().getBoots() != null) && (p.getInventory().getBoots().hasItemMeta()) && (p.getInventory().getBoots().getItemMeta().hasLore()) && (p.getInventory().getBoots().getItemMeta().getLore().contains("§7Corrida I")) && (e.getCause().equals(PlayerTeleportEvent.TeleportCause.ENDER_PEARL))) {
			p.sendMessage("§aEfeito Corrida ativado.");
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 1), true);
			new BukkitRunnable() {
				public void run() {
					p.removePotionEffect(PotionEffectType.SPEED);
					p.sendMessage("§cEfeito Corrida desativado.");
				}
			}.runTaskLater(Main.getInstance(), 300L);
		}
	}*/

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (e.getPlayer().getInventory().getBoots() != null) {
			Player p = e.getPlayer();
			if ((p.getInventory().getBoots().hasItemMeta()) && (p.getInventory().getBoots().getItemMeta().hasLore()) && (p.getInventory().getBoots().getItemMeta().getLore().contains("§7Passos congelantes I"))) {
				Location playerPos = e.getPlayer().getLocation();
				int r = 2;
				for (int x = r * -1; x <= r; x++) {
					for (int y = r * -1; y <= r; y++) {
						for (int z = r * -1; z <= r; z++) {
							Block b = playerPos.getWorld().getBlockAt(playerPos.getBlockX() + x, playerPos.getBlockY() + y, playerPos.getBlockZ() + z);
							if ((b.getType() == Material.WATER) || (b.getType() == Material.STATIONARY_WATER)) {
								b.setType(Material.ICE);
							}
						}
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void rightSobrecarga(PlayerInteractEvent e) {
		if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) && (e.getPlayer().getInventory().getItemInHand() != null) && (e.getPlayer().getInventory().getItemInHand().getType() == Material.DIAMOND_SWORD) && (e.getPlayer().getInventory().getItemInHand().hasItemMeta()) && (e.getPlayer().getInventory().getItemInHand().getItemMeta().hasLore()) && (e.getPlayer().getInventory().getItemInHand().getItemMeta().getLore().contains("§7Sobre Carga II"))) {
			Player p = e.getPlayer();
			Block block = e.getPlayer().getTargetBlock((HashSet<Byte>) null, 100);
			Location bl = block.getLocation();
			if (!cooldown.contains(p.getName())) {
				p.getWorld().strikeLightning(bl);
				cooldown.add(p.getName());
				new BukkitRunnable() {
					public void run() {
						cooldown.remove(p.getName());
					}
				}.runTaskLater(Main.getInstance(), 400L);
			} else {
				p.sendMessage("§cVocê precisa esperar para utilizar este efeito novamente.");
				return;
			}
		}
	}
	
	@EventHandler
	public void rightDivina(PlayerInteractEvent e) {
		if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) && (e.getPlayer().getInventory().getItemInHand() != null) && (e.getPlayer().getInventory().getItemInHand().getType() == Material.DIAMOND_SWORD) && (e.getPlayer().getInventory().getItemInHand().hasItemMeta()) && (e.getPlayer().getInventory().getItemInHand().getItemMeta().hasLore()) && (e.getPlayer().getInventory().getItemInHand().getItemMeta().getDisplayName().contains("§cEspada Divina"))) {
			Player p = e.getPlayer();
			if (!cooldown.contains(p.getName())) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 10, 1));
				cooldown.add(p.getName());
				new BukkitRunnable() {
					public void run() {
						cooldown.remove(p.getName());
					}
				}.runTaskLater(Main.getInstance(), 400L);
			} else {
				p.sendMessage("§cVocê precisa esperar para utilizar este efeito novamente.");
				return;
			}
		}
	}

/*	@EventHandler
	public void onBater(EntityDamageByEntityEvent e) {
		Player c = (Player) e.getDamager();
//		Player d = (Player) e.getEntity();
		if ((!e.isCancelled()) && ((e.getEntity() instanceof Player))) {
			if ((e.getDamager() instanceof Player)) {
				if (c.getInventory().getItemInHand().getItemMeta().getLore().contains("§7Tardio I")) {
					d.removePotionEffect(PotionEffectType.SLOW);
					d.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 80, 2));
				}
			}
			if ((e.getDamager() instanceof Player)) {
				if (c.getInventory().getItemInHand().getItemMeta().getLore().contains("§7Entusiasmo I")) {
					if (percentChance(10)) {
						d.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 35, 2));
						d.sendMessage("§cVocê foi atordoado pelo entusiasmo de " + c.getName() + ".");
						c.sendMessage("§aVocê atordoou " + d.getName() + " com seu entusiasmo.");
					}
				}
			}
			if (((e.getDamager() instanceof Player)) && ((e.getEntity() instanceof Player))) {
				if (d.getInventory().getItemInHand().getItemMeta().getLore().contains("§7Snail I")) {
					if (e.isCancelled()) {
						return;
					}
					if (percentChance(67)) {
						c.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 80, 0));
					}
				}
			}
			if ((e.getDamager() instanceof Player)) {
				if (c.getInventory().getItemInHand().getItemMeta().getLore().contains("§7Sede de Sangue I")) {
					if (percentChance(10)) {
						if (c.getMaxHealth() == 26.0D) {
							c.setHealth(26.0D);
							c.sendMessage("§aSua vida foi regenerada.");
							return;
						}
						c.setHealth(20.0D);
						c.sendMessage("§aSua vida foi regenerada.");
					}
				}
			}
			if ((e.getDamager() instanceof Player)) {
				if (c.getInventory().getItemInHand().getItemMeta().getLore().contains("§7Divindade I")) {
					if (percentChance(10)) {
						c.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 10, 1));
					}
				}
			}
			if ((e.getDamager() instanceof Player)) {
				if (c.getInventory().getItemInHand().getItemMeta().getLore().contains("§7Estressado I")) {
					if (percentChance(43)) {
						if (c.getMaxHealth() == 26.0D) {
							c.setHealth(26.0D);
							return;
						}
						c.setHealth(20.0D);
						e.setDamage(e.getDamage() + 2);
						d.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 20, 2));
						c.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20, 2));
						c.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 20, 2));
						c.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20, 2));
						c.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, 2));
						c.sendMessage("§aSua fúria de estresse foi iniciada.");
					}
				}
			}
			if ((e.getDamager() instanceof Player)) {
				if (c.getInventory().getItemInHand().getItemMeta().getLore().contains("§7Sobre Carga I")) {
					if (percentChance(20)) {
						d.getPlayer().getWorld().strikeLightningEffect(d.getPlayer().getLocation());
					}
				}
			}
			if (e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
				Arrow flecha = (Arrow) e.getDamager();
				if (!(flecha.getShooter() instanceof Player))
					return;
				Player atirador = (Player) flecha.getShooter();
				if (((flecha.getShooter() instanceof Player)) && (atirador.getItemInHand().getType() == Material.BOW)) {
					if (atirador.getItemInHand().getItemMeta().getLore().contains("§7Dardo I")) {
						d.removePotionEffect(PotionEffectType.POISON);
						d.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 80, 2));
					}
				}
			}
		}
	}*/ 	
	
	protected Random random = new Random();
	protected final boolean percentChance(double percent) {
		if (percent < 0 || percent > 100)
			throw new IllegalArgumentException("A percentagem nao pode ser maior do que 100 nem menor do que 0");
		double result = random.nextDouble() * 100;
		return result <= percent;
	}
}
