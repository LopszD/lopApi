package com.wandy.api.especiais.listeners;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SlimeListener implements Listener {
	
	@EventHandler
	public void aoClicar(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getItem() == null) {
				return;
			}
			if (e.getItem().getType() == Material.AIR) {
				return;
			}
			if (e.getItem().getType() == Material.SLIME_BALL && e.getItem().getItemMeta().getDisplayName().contains("§aDetector de Slime Chunk")) {
				Location loc = e.getClickedBlock().getLocation();
				long worldSeed = loc.getWorld().getSeed();
				Chunk playerChunk;
				int xChunk;
				int zChunk;
				Random random = new Random(worldSeed + (xChunk = (playerChunk = loc.getWorld().getChunkAt(loc)).getX()) * xChunk * 4987142 + xChunk * 5947611 + (zChunk = playerChunk.getZ()) * zChunk * 4392871L + zChunk * 389711 ^ 0x3AD8025F);
				if (random.nextInt(10) == 0) {
					e.getPlayer().sendMessage("§aVocê encontrou uma chunk de slime!");
					e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.SLIME_WALK, 1.0F, 0.8F);
					return;
				} else {
					e.getPlayer().sendMessage("§cAqui não é uma chunk de slime.");
					return;
				}
			}
		}
	}
}
