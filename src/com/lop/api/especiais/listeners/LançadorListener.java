package com.wandy.api.especiais.listeners;

import java.util.HashMap;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.wandy.api.listeners.EntrarListener;

public class LançadorListener implements Listener {

	public static HashMap<Player, Long> usou;

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.hasItem() && p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().hasDisplayName() && p.getItemInHand().getItemMeta().getDisplayName().contains("§eLançador")) {
			e.setCancelled(true);
			if ((e.getAction().equals(Action.RIGHT_CLICK_AIR)) || (e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
				if (p.getWorld().getName().equals("world_arenas")) {
					e.setCancelled(true);
					return;
				}
				Location loc = p.getLocation();
				loc = new Location(p.getWorld(), loc.getX(), loc.getY(), loc.getZ() - 1.5);
				for (int i = 0; i < 180; i += 3) {
					Location flameloc = loc;
					flameloc.setZ(flameloc.getZ() + Math.cos(i) * 3);
					flameloc.setX(flameloc.getX() + Math.sin(i) * 3);
					loc.getWorld().playEffect(flameloc, Effect.FIREWORKS_SPARK, 50);
					loc.getWorld().playEffect(flameloc, Effect.FIREWORKS_SPARK, 50);
				}
				EntrarListener.mandarAction(p, "§aVocê foi lançado!");
				p.playSound(p.getLocation(), Sound.FIREWORK_LAUNCH, 2.0F, 2.0F);
				ItemStack item = p.getItemInHand();
				if (item.getAmount() > 1) {
					item.setAmount(item.getAmount() - 1);
				} else {
					p.setItemInHand(new ItemStack(Material.AIR));
				}
				Long value = System.currentTimeMillis() / 1000L;
				usou.put(p, value);
				if (!p.isSneaking()) {
					p.setFallDistance(-5.0F);
					Vector direction;
					direction = p.getEyeLocation().getDirection().multiply(1.0);
					direction.setY(3.3);
					p.setVelocity(direction);
					return;
				}
				p.setFallDistance(-5.0F);
				Vector direction;
				direction = p.getEyeLocation().getDirection().multiply(1.0);
				direction.setY(3.3);
				p.setVelocity(direction);
			}
		}
	}

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (e.isCancelled())
			return;
		if (e.getEntity() instanceof Player && e.getCause() == DamageCause.FALL) {
			Player p = (Player) e.getEntity();
			if (usou.containsKey(p)) {
				e.setCancelled(true);
				usou.remove(p);
			} else {
				return;
			}
		}
	}
}
