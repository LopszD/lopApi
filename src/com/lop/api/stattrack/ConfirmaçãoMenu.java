package com.wandy.api.stattrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.wandy.api.utils.Magaiver;
import com.wandy.economy.API_Economy;

public class ConfirmaçãoMenu implements Listener {
	
	public static void abrirMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 36, "§8Confirmação§1§2§3");
		ItemStack a = new ItemStack(Material.ANVIL);
		ItemMeta am = a.getItemMeta();
		am.setDisplayName("§cMacumbar um novo item");
		am.setLore(Arrays.asList(new String[] { "§7Adicionar à um novo item", "§7uma contagem de §cmaldição§7." }));
		a.setItemMeta(am);

		ItemStack C = new ItemStack(Material.WOOL, 1, (short) 5);
		ItemMeta Cm = C.getItemMeta();
		Cm.setDisplayName("§aAceitar (Leia abaixo)");
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

	@EventHandler
	public static void aoClicar(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getTitle().equals("§8Confirmação§1§2§3")) {
			if (e.getCurrentItem() == null) {
				return;
			}
			if (e.getCurrentItem().getType().equals(Material.AIR)) {
				return;
			}
			e.setCancelled(true);
			if (e.getSlot() == 24) {
				Magaiver.abrirMenu(p);
			}
			if (e.getSlot() == 20) {
				if (p.getItemInHand() == null) {
					p.closeInventory();
					p.sendMessage("§cVocê precisa de um item na sua mão.");
					return;
				}
				if (p.getItemInHand().getType().equals(Material.AIR)) {
					p.closeInventory();
					p.sendMessage("§cVocê precisa de um item na sua mão.");
					return;
				}
				if (!MagaiverListener.checarItem(p.getItemInHand())) {
					p.closeInventory();
					p.sendMessage("§cTipo inválido de item.");
					return;
				}
				if (MagaiverListener.temStat(p.getItemInHand())) {
					p.closeInventory();
					p.sendMessage("§cEste item já possui um sistema de maldição.");
					return;
				}
				if (API_Economy.getMoney(p.getName()) >= 25000) {
					API_Economy.delMoney(p.getName(), 25000);
					MagaiverListener.adicionarItem(p.getItemInHand());
					p.closeInventory();
					p.sendMessage("§aVocê aplicou o sistema de maldições em seu item.");
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 0.5F, 1.0F);
				} else {
					p.sendMessage("§cVocê não tem coins suficiente para adquirir este sistema.");
				}
			}
		}
	}
}
