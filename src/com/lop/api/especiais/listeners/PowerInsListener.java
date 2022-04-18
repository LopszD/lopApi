package com.wandy.api.especiais.listeners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.massivecraft.factions.entity.MPlayer;
import com.wandy.api.utils.FireworkEffectPlayer;

public class PowerInsListener implements Listener {
	
	static FireworkEffectPlayer fplayer = new FireworkEffectPlayer();
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public static void aoColocar(PlayerInteractEvent e) {
		if (e.getItem() == null) {
			return;
		}
		if (e.getItem().getType() == Material.AIR) {
			return;
		}
		if (e.getItem().getType() == Material.PAPER) {
			if (e.getItem().getItemMeta().hasDisplayName()) {
				if (e.getItem().getItemMeta().getDisplayName().contains("§6+1 de Poder Instantâneo")) {
					Player p = e.getPlayer();
					if ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
						e.setCancelled(true);
						abrirConfi(p);
					}
				}
			}
		}
	}

	@EventHandler
	public static void aoClicar(InventoryClickEvent e) throws Exception {
		if (e.getInventory().getTitle().equals("§8Confirmação - Poder Ins")) {
			Player p = (Player) e.getWhoClicked();
			if (e.getCurrentItem() == null) {
				return;
			}
			if (e.getCurrentItem().getType() == Material.AIR) {
				return;
			}
			e.setCancelled(true);
			if (eDup(p)) {
				p.closeInventory();
				p.sendMessage("§cOcorreu um erro ao concluir está ação. Tente novamente.");
				return;
			}
			if (e.getSlot() == 24) {
				p.closeInventory();
			}
			if (e.getSlot() == 20) {
				MPlayer mp = MPlayer.get(p);
				int poderatual = mp.getPowerRounded();
				Color color = Color.ORANGE;
				Color color2 = Color.YELLOW;
				if (poderatual >= mp.getPowerMax()) {
					p.sendMessage("§cVocê está com seu poder atual no máximo. (" + mp.getPowerRounded() + "/" + mp.getPowerMaxRounded() + ")");
					return;
				}
				if (p.getItemInHand().getAmount() >= 2) {
					p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
				} else if (p.getItemInHand().getAmount() == 1) {
					p.setItemInHand(new ItemStack(Material.AIR));
				}
				p.updateInventory();
				p.closeInventory();
				mp.setPower(poderatual + 1.0);
				p.sendMessage("§aVocê aumentou seu poder para " + mp.getPowerRounded() + "!");
				p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 10.0F, 10.0F);
				fplayer.playFirework(p.getWorld(), p.getLocation(), getRandomEffect(color));
				fplayer.playFirework(p.getWorld(), p.getLocation(), getRandomEffect(color2));
			}
			return;
		}
	}

	public static boolean eDup(Player p) {
		if (p.getItemInHand() == null) {
			return true;
		}
		if (p.getItemInHand().getType() == Material.AIR) {
			return true;
		}
		if (!(p.getItemInHand().getType() == Material.PAPER)) {
			return true;
		}
		if (p.getItemInHand().hasItemMeta()) {
			if (p.getItemInHand().getItemMeta().hasDisplayName()) {
				if (!p.getItemInHand().getItemMeta().getDisplayName().contains("§6+1 de Poder Instantâneo")) {
					return true;
				}
			}
		}
		return false;
	}

	public static void abrirConfi(Player p) {
		Inventory inv = Bukkit.createInventory(null, 36, "§8Confirmação - Poder Ins");

		ItemStack a = new ItemStack(Material.PAPER);
		ItemMeta am = a.getItemMeta();
		am.setDisplayName("§6+1 de Poder Instantâneo");
		am.setLore(Arrays.asList(new String[] { "§7Ativando este item, você", "§7ganhará 1 ponto de poder." }));
		a.setItemMeta(am);

		ItemStack C = new ItemStack(Material.WOOL, 1, (short) 5);
		ItemMeta Cm = C.getItemMeta();
		Cm.setDisplayName("§aAceitar");
		C.setItemMeta(Cm);
		List<String> lore = new ArrayList<String>();
		lore.add("§7Tem certeza que deseja confirmar?");
		Cm.setLore(lore);
		C.setItemMeta(Cm);

		ItemStack C2 = new ItemStack(Material.WOOL, 1, (short) 14);
		ItemMeta Cm2 = C2.getItemMeta();
		Cm2.setDisplayName("§cNegar");
		C2.setItemMeta(Cm2);
		List<String> lore1 = new ArrayList<String>();
		lore1.add("§7Cancelar está operação.");
		Cm2.setLore(lore1);
		C2.setItemMeta(Cm2);

		inv.setItem(13, a);
		inv.setItem(20, C);
		inv.setItem(24, C2);

		p.openInventory(inv);
	}
	
	public static FireworkEffect getRandomEffect(Color color) {
		return FireworkEffect.builder().with(org.bukkit.FireworkEffect.Type.BALL).withColor(color).build();
	}
}
