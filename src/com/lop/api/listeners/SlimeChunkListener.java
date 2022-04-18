package com.wandy.api.listeners;

import java.util.ArrayList;
import java.util.Random;
import org.bukkit.Chunk;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class SlimeChunkListener implements Listener {
	
	public static ArrayList<String> seeslime = new ArrayList<String>();
	public static ArrayList<String> pa = new ArrayList<String>();

	@EventHandler
	public static void aoMover(PlayerMoveEvent e) {
		if (seeslime.contains(e.getPlayer().getName())) {
			Player p = e.getPlayer();
			Chunk ch1 = e.getFrom().getChunk();
			Chunk ch2 = e.getTo().getChunk();
			if (ch1 != ch2) {
				return;
			}
			long worldSeed = e.getPlayer().getWorld().getSeed();
			Chunk playerChunk = e.getPlayer().getWorld().getChunkAt(p.getLocation());
			int xChunk = playerChunk.getX();
			int zChunk = playerChunk.getZ();
			Random random = new Random(worldSeed + xChunk * xChunk * 4987142 + xChunk * 5947611 + zChunk * zChunk * 4392871L + zChunk * 389711 ^ 0x3AD8025F);
			if (random.nextInt(10) == 0) {
				p.playSound(p.getLocation(), Sound.SLIME_WALK, 1.0F, 1.0F);
			}
		}
	}
}
