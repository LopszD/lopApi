package com.wandy.api.especiais.listeners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
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

public class ResetKDRListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public static void aoColocar(PlayerInteractEvent e) {
		if (e.getItem() == null) {
			return;
		}
		if (e.getItem().getType() == Material.AIR) {
			return;
		}
		if (e.getItem().getType() == Material.FIREWORK_CHARGE) {
			if (e.getItem().getItemMeta().hasDisplayName()) {
				if (e.getItem().getItemMeta().getDisplayName().contains("§6Reset KDR")) {
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
	public static void aoClicar(InventoryClickEvent e) {
		if (e.getInventory().getTitle().equals("§8Confirmação - Reset KDR")) {
			Player p = (Player) e.getWhoClicked();
			MPlayer mp = MPlayer.get(p);
			if (e.getCurrentItem() == null) {
				return;
			}
			if (e.getCurrentItem().getType() == Material.AIR) {
				return;
			}
			e.setCancelled(true);
			if (eDup(p)) {
				p.closeInventory();
				p.sendMessage("§cOcorreu um erro ao concluir esta ação. Tente novamente.");
				return;
			}
			if (e.getSlot() == 24) {
				p.closeInventory();
			}
			if (e.getSlot() == 20) {
				if (p.getItemInHand().getAmount() >= 2) {
					p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
				} else if (p.getItemInHand().getAmount() == 1) {
					p.setItemInHand(new ItemStack(Material.AIR));
				}
				p.closeInventory();
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
				p.sendMessage("§eVocê teve seu KDR resetado com sucesso");
				mp.setKills(0);
				mp.setDeaths(0);
			}
		}
	}

	public static boolean eDup(Player p) {
		if (p.getItemInHand() == null) {
			return true;
		}
		if (p.getItemInHand().getType() == Material.AIR) {
			return true;
		}
		if (!(p.getItemInHand().getType() == Material.FIREWORK_CHARGE)) {
			return true;
		}
		if (p.getItemInHand().hasItemMeta()) {
			if (p.getItemInHand().getItemMeta().hasDisplayName()) {
				if (!p.getItemInHand().getItemMeta().getDisplayName().contains("§6Reset KDR")) {
					return true;
				}
			}
		}
		return false;
	}

	public static void abrirConfi(Player p) {
		Inventory inv = Bukkit.createInventory(null, 36, "§8Confirmação - Reset KDR");

		ItemStack a = new ItemStack(Material.FIREWORK_CHARGE);
		ItemMeta am = a.getItemMeta();
		am.setDisplayName("§6Reset KDR");
		am.setLore(Arrays.asList(new String[] { "§7Use este item para resetar seu KDR." }));
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
}
