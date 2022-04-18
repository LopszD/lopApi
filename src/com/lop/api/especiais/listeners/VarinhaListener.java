package com.wandy.api.especiais.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.wandy.api.Main;
import com.wandy.api.commands.VarinhaCommand;

public class VarinhaListener implements Listener {
	
	@EventHandler
	public static void aoUsar(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getItem() == null) {
			return;
		}
		if (e.getItem().getType() == Material.AIR) {
			return;
		}
		if (e.getItem().getType() == Material.BLAZE_ROD) {
			if (VarinhaCommand.vari.contains(p.getName())) {
				e.setCancelled(true);
				p.playSound(p.getLocation(), Sound.FIREWORK_LAUNCH, 2.0F, 2.0F);
				new BukkitRunnable() {
					Vector dir;
					Location loc;
					double t;
					public void run() {
						this.t += 0.5D;
						double x = this.dir.getX() * this.t;
						double y = this.dir.getY() * this.t + 1.5D;
						double z = this.dir.getZ() * this.t;
						this.loc.add(x, y, z);
						this.loc.subtract(x, y, z);
						if (this.t > 30.0D) {
							cancel();
						}
					}
				}.runTaskTimer(Main.getInstance(), 0L, 1L);
			}
		}
	}

	public static void mandarPlayer(Player p) {
		p.playSound(p.getLocation(), Sound.FIREWORK_LAUNCH, 2.0F, 2.0F);
		new BukkitRunnable() {
			Vector dir;
			Location loc;
			double t;
			public void run() {
				this.t += 0.5D;
				double x = this.dir.getX() * this.t;
				double y = this.dir.getY() * this.t + 1.5D;
				double z = this.dir.getZ() * this.t;
				this.loc.add(x, y, z);
				this.loc.subtract(x, y, z);
				if (this.t > 30.0D) {
					cancel();
				}
			}
		}.runTaskTimer(Main.getInstance(), 0L, 1L);
	}

	public static void mandarPlayer1(Player p) {
		p.playSound(p.getLocation(), Sound.FIREWORK_LAUNCH, 2.0F, 2.0F);
		new BukkitRunnable() {
			Vector dir;
			Location loc;
			double t;
			public void run() {
				this.t += 0.5D;
				double x = this.dir.getX() * this.t;
				double y = this.dir.getY() * this.t + 1.5D;
				double z = this.dir.getZ() * this.t;
				this.loc.add(x, y, z);
				this.loc.subtract(x, y, z);
				if (this.t > 30.0D) {
					cancel();
				}
			}
		}.runTaskTimer(Main.getInstance(), 0L, 1L);
	}
}
