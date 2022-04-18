package com.wandy.api.especiais.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;
import com.wandy.api.utils.Proteção;
import com.wandy.api.utils.partículas.Particles;

import net.minecraft.server.v1_8_R3.EnumParticle;

public class DevatacaoListener implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		ItemStack handItem = p.getItemInHand();
		Block b = e.getBlock();
		MPlayer mp = MPlayer.get(p);
		if (((handItem.getType() == Material.DIAMOND_PICKAXE) || (handItem.getType() == Material.GOLD_PICKAXE) || (handItem.getType() == Material.IRON_PICKAXE) || (handItem.getType() == Material.STONE_PICKAXE) || (handItem.getType() == Material.WOOD_PICKAXE)) && (handItem.hasItemMeta()) && (handItem.getItemMeta().hasLore()) && (handItem.getItemMeta().getLore().contains("§7Devastação I"))) {
			if (BoardColl.get().getFactionAt(PS.valueOf(p.getLocation())).getId().equalsIgnoreCase("safezone")) {
				e.setCancelled(true);
				return;
			}
			if (BoardColl.get().getFactionAt(PS.valueOf(p.getLocation())).getId().equalsIgnoreCase("warzone")) {
				e.setCancelled(true);
				return;
			}
			if (Proteção.getArea(p).equals("spawn")) {
				e.setCancelled(true);
				return;
			}
			if (Proteção.getArea(p).equals("__global__")) {
				e.setCancelled(true);
				return;
			}
			if (p.getWorld().getName().equals("vip")) {
				e.setCancelled(true);
				return;
			}
			if (p.getWorld().getName().equals("minas")) {
				e.setCancelled(true);
				return;
			}
			if (p.getWorld().getName().equals("world_arenas")) {
				e.setCancelled(true);
				return;
			}
			if ((!BoardColl.get().getFactionAt(PS.valueOf(p.getLocation())).equals(mp.getFaction())) || (BoardColl.get().getFactionAt(PS.valueOf(e.getBlock())).isNone())) {
				p.sendMessage("§cVocê não pode quebrar este bloco pois está área não é dominada por sua facção.");
				e.setCancelled(true);
				return;
			}
			for (int xOff = -1; xOff <= 1; xOff++) {
				for (int yOff = -1; yOff <= 1; yOff++) {
					for (int zOff = -1; zOff <= 1; zOff++) {
						Block block = b.getRelative(xOff, yOff, zOff);
						if (!(block.getType() == Material.BEDROCK)) {
							block.breakNaturally();
						}
						Particles packet = new Particles(EnumParticle.EXPLOSION_NORMAL, block.getLocation(), 1.0F, 1.0F, 1.0F, 0.05F, 3);
						packet.sendToAll();
					}
				}
			}
		}
	}
}
