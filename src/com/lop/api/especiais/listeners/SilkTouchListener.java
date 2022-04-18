package com.wandy.api.especiais.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.MPlayer;
import com.wandy.api.utils.FactionUtil;

public class SilkTouchListener implements Listener {

	@EventHandler
	public static void aoQuebrar(PlayerInteractEvent e) {
		if (e.getClickedBlock() == null) {
			return;
		}
		if (e.getClickedBlock().getType().equals(Material.AIR)) {
			return;
		}
		if (e.getClickedBlock().getType().equals(Material.BEDROCK)) {
			if (e.getClickedBlock().getLocation().getBlockY() <= 1) {
				return;
			}
			if (e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
				if (e.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
					Player p = e.getPlayer();
					int dura = 25;
					if (p.getItemInHand() != null) {
						if (!p.getItemInHand().getType().equals(Material.AIR)) {
							if (p.getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
								if (p.getItemInHand().getEnchantmentLevel(Enchantment.SILK_TOUCH) >= 2) {
									if (p.getWorld().getName().equals("minas")) {
										return;
									}
									FactionUtil fu = new FactionUtil();
									if (p.getItemInHand().containsEnchantment(Enchantment.DURABILITY)) {
										int lvl = p.getItemInHand().getEnchantmentLevel(Enchantment.DURABILITY);
										if (lvl == 1) {
											dura = 23;
										}
										if (lvl == 2) {
											dura = 21;
										}
										if (lvl == 3) {
											dura = 19;
										}
										if (lvl == 4) {
											dura = 17;
										}
										if (lvl == 5) {
											dura = 15;
										}
									}
									if (!fu.getFaction(e.getClickedBlock().getLocation()).isNone()) {
										MPlayer mp = MPlayer.get(p);
										if (!mp.isOverriding()) {
											if (fu.getFaction(e.getClickedBlock().getLocation()).equals(mp.getFaction())) {
												if (mp.getRole().equals(Rel.RECRUIT)) {
													p.sendMessage("§cVocê não tem permissão para quebrar bedrocks.");
													return;
												}
												e.getClickedBlock().breakNaturally();
												int du = p.getItemInHand().getDurability() + dura;
												if (du >= p.getItemInHand().getType().getMaxDurability()) {
													p.setItemInHand(new ItemStack(Material.AIR));
													p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
													return;
												}
												p.getItemInHand().setDurability((short) du);
												return;
											}
											return;
										}
									}
									e.getClickedBlock().breakNaturally();
									int du = p.getItemInHand().getDurability() + dura;
									if (du >= p.getItemInHand().getType().getMaxDurability()) {
										p.setItemInHand(new ItemStack(Material.AIR));
										return;
									}
									p.getItemInHand().setDurability((short) du);
								}
							}
						}
					}
				}
			}
		}
	}
}