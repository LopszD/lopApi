package com.wandy.api.especiais.listeners;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.wandy.api.Main;

public class DivinaListener implements Listener {
	
	public static ArrayList<String> delay = new ArrayList<String>();
	
	@EventHandler
	public void rightDivina(PlayerInteractEvent e) {
		if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) && (e.getPlayer().getInventory().getItemInHand() != null) && (e.getPlayer().getInventory().getItemInHand().getType() == Material.DIAMOND_SWORD) && (e.getPlayer().getInventory().getItemInHand().hasItemMeta()) && (e.getPlayer().getInventory().getItemInHand().getItemMeta().hasLore()) && (e.getPlayer().getInventory().getItemInHand().getItemMeta().getDisplayName().contains("§cEspada Divina"))) {
			Player p = e.getPlayer();
			if (!delay.contains(p.getName())) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 10, 1));
				delay.add(p.getName());
				new BukkitRunnable() {
					public void run() {
						delay.remove(p.getName());
					}
				}.runTaskLater(Main.getInstance(), 20 * 5);
			} else {
				p.sendMessage("§cVocê precisa esperar para utilizar este efeito novamente.");
				return;
			}
		}
	}
}
