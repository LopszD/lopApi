package com.wandy.api.especiais.listeners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class VidaMListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public static void aoColocar(PlayerInteractEvent e) {
		if (e.getItem() == null) {
			return;
		}
		if (e.getItem().getType() == Material.AIR) {
			return;
		}
		if (e.getItem().getType() == Material.REDSTONE) {
			if (e.getItem().getItemMeta().hasDisplayName()) {
				if (e.getItem().getItemMeta().getDisplayName().contains("§eVida+")) {
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
		if (e.getInventory().getTitle().equals("§8Confirmação - Vida+")) {
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
				if (p.getMaxHealth() == 26.0D) {
					p.sendMessage("§cVocê já está com o máximo de corações.");
					return;
				}
				if (p.getItemInHand().getAmount() >= 2) {
					p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
				} else if (p.getItemInHand().getAmount() == 1) {
					p.setItemInHand(new ItemStack(Material.AIR));
				}
				p.updateInventory();
				p.closeInventory();
				p.sendMessage("§a+3 corações foram adicionados em conta.");
				p.setMaxHealth(26.0D);
				p.setHealth(26.0D);
				p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 10.0F, 10.0F);
				return;
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
		if (!(p.getItemInHand().getType() == Material.REDSTONE)) {
			return true;
		}
		if (p.getItemInHand().hasItemMeta()) {
			if (p.getItemInHand().getItemMeta().hasDisplayName()) {
				if (!p.getItemInHand().getItemMeta().getDisplayName().contains("§eVida+")) {
					return true;
				}
			}
		}
		return false;
	}

	public static void abrirConfi(Player p) {
		Inventory inv = Bukkit.createInventory(null, 36, "§8Confirmação - Vida+");

		ItemStack a = new ItemStack(Material.REDSTONE);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		ItemMeta am = a.getItemMeta();
		am.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		am.setDisplayName("§eVida+");
		am.setLore(Arrays.asList(new String[] { "§7Ao utilizar este item, você irá", "§7receber +3 corações." }));
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
